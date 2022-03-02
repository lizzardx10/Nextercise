package com.example.nextercise.ui.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.nextercise.Exercise;
import com.example.nextercise.R;
import com.parse.ParseQuery;

import java.io.File;

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
//    public String photoFileName =

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
        ParseQuery<Exercise> query = ParseQuery.getQuery(Exercise.class);
        // query.include(Exercise.KEY_EXERCISEID);
        query.getInBackground("5O6Dnkrp77", (exercise, e) -> {
            if (e == null) {
                Log.i(TAG, "Exercise: " + exercise.getExerciseName() + ", exercise desc: " + exercise.getExerciseDescription());
            } else {
                Log.e(TAG, "issue with retrieving exercise");
            }

        });
//        query.findInBackground(new FindCallback Exercise {
//            @Override
//            public void done(List<Exercise> exercises, ParseException e) {
//                if (e != null) {
//                    Log.e(TAG, "issue with retrieving exercise");
//                    return;
//                }
//                for (Exercise exercise : exercises) {
//                    Log.i(TAG, "Exercise: " + exercise.getExerciseName() + ", exercise desc: " + exercise.getExerciseDescription());
//                }
//            }
//        });
    }

}
