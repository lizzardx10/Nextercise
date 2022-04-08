package com.example.nextercise.ui.Fragments;

import static com.parse.Parse.getApplicationContext;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.nextercise.Exercise;
import com.example.nextercise.R;
import com.example.nextercise.ui.ExerciseActivity;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.io.File;
import java.util.Random;

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
    private Exercise rExercise;
    private int qExcId;

    public class QueryCount{
        private int count;
    }
//    public static class RandomNum{
//        private static int num;
//    }

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
//        RandomNum rNum = new RandomNum();
//        rNum.num = queryExercise();
        queryExercise();

        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                queryExercise();
            }
        });
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goExerciseActivity();
            }
        });

    }

    private void goExerciseActivity() {
            Intent i = new Intent(getApplicationContext(), ExerciseActivity.class);
            Log.i(TAG, String.valueOf(qExcId));
            i.putExtra("int_value", qExcId);
            startActivity(i);
    }

    private void queryExercise() {
        ParseQuery<Exercise> query = ParseQuery.getQuery("Exercise");
        QueryCount qCount = new QueryCount();
        // call count asynchronously
        try {
            int count = query.count();
            qCount.count = count+1;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Random random = new Random();
        int randomNumb = random.nextInt(qCount.count - 0) + 0;
        query.whereEqualTo("exerciseId", randomNumb);
        // Execute the find asynchronously
        try {
            Exercise recommended = query.getFirst();
            etExcName.setText(recommended.getString("exerciseName"));
            Glide.with(getApplicationContext()).load(recommended.getExerciseImage().getUrl()).into(ivExcImage);
            etExcDescription.setText(recommended.getString("exerciseDescription"));
//            rExercise = recommended;
            qExcId = recommended.getExerciseId();
        } catch (ParseException e) {
            e.printStackTrace();
            Log.d(TAG, "nothing found");
        }
    }
}
