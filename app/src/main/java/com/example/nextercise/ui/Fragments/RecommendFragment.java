package com.example.nextercise.ui.Fragments;

import static com.parse.Parse.getApplicationContext;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.nextercise.Exercise;
import com.example.nextercise.R;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.io.File;
import java.util.concurrent.ThreadLocalRandom;

public class RecommendFragment extends Fragment {
    public static final String TAG = "RecommendFragment";
    public RecommendFragment() {
        // requires empty public constructor
    }
    private TextView etExcName;
    private ImageView ivExcImage;
    private TextView etExcDescription;

    private Button btnSkip;
    private Button btnView;

    private File imageFile;

    public class QueryCount{
        private int count;
    }

    // onCreateView called when Fragment should create its View object hierarchy, dynamically or via XML layout inflation
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    // inflate the layout for this fragment, defines xml file for fragment
        return inflater.inflate(R.layout.fragment_recommend, container, false);
    }

    // onViewCreated triggered soon after onCreateView()
    // setup occurs here, E.g., view lookups & attaching view listeners
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // setup any handles to view objects here
        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);

        etExcName = view.findViewById(R.id.etExcName);
        ivExcImage = view.findViewById(R.id.ivExcImage);
        etExcDescription = view.findViewById(R.id.etExcDescription);
        btnSkip = view.findViewById(R.id.btnSkip);
        btnView = view.findViewById(R.id.btnView);

        queryExercise();
    }
    private void queryExercise() {
        ParseQuery<Exercise> query = ParseQuery.getQuery("Exercise");
        QueryCount qCount = new QueryCount();
        // call count asynchronously
        try {
            int count  =  query.count();
            qCount.count = count;
            Toast.makeText(getApplicationContext(), "Count : "+count, Toast.LENGTH_SHORT).show();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int randomNum = ThreadLocalRandom.current().nextInt(0, qCount.count);
        query.whereEqualTo("exerciseId", randomNum);
        // Execute the find asynchronously
        query.getFirstInBackground(new GetCallback<Exercise>() {
            public void done(Exercise recommended, ParseException e) {
                // you want to do a check on the ParseException here as well.
                if (recommended == null) {
                    Log.d("nothing found", "let's go ahead and create a new object.");
                } else {
                    etExcName.setText(recommended.getString("exerciseName"));
                    Glide.with(getApplicationContext()).load(recommended.getExerciseImage().getUrl()).into(ivExcImage);
                    etExcDescription.setText(recommended.getString("exerciseDescription"));
                    //Log.d("points found", points + "");
                }
            }
        });
    }
}
