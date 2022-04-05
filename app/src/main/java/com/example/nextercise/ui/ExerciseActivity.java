package com.example.nextercise.ui;

import static com.example.nextercise.R.layout.activity_exercise;
import static com.parse.Parse.getApplicationContext;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.example.nextercise.Exercise;
import com.example.nextercise.R;
import com.example.nextercise.SearchAdapter;
import com.example.nextercise.ui.Fragments.RecommendFragment;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class ExerciseActivity extends AppCompatActivity {
    public static final String TAG = "ExerciseActivity";
    private TextView etExerciseName;
    private ImageView ivExerciseImage;
    private TextView etExerciseDescription;
    private TextView etExerciseInstructions;
    private Switch sToggleAdd;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_exercise);

        Intent intent = getIntent();
        int temp = intent.getIntExtra("int_value", 0);
        Log.i("test", String.valueOf(temp));

        etExerciseName = findViewById(R.id.etExerciseName);
        ivExerciseImage = findViewById(R.id.ivExerciseImage);
        etExerciseDescription = findViewById(R.id.etExerciseDescription);
        etExerciseInstructions = findViewById(R.id.etExerciseInstructions);
        sToggleAdd = findViewById(R.id.sToggleAdd);
        loadExercise(temp);
    }

    private void loadExercise(int exerciseId) {
        ParseQuery<Exercise> query = ParseQuery.getQuery("Exercise");
        query.whereEqualTo("exerciseId", exerciseId);
        ParseUser currentUser = ParseUser.getCurrentUser();
        // ArrayList<String> SavedList = currentUser.getJSONArray("exerciseList");
//        String recExcID = recommended.getString("objectId");
        // Execute the find asynchronously
        try {
            Exercise recommended = query.getFirst();
            Log.i(TAG, recommended.getString("exerciseName"));
            etExerciseName.setText(recommended.getString("exerciseName"));
            Glide.with(getApplicationContext()).load(recommended.getExerciseImage().getUrl()).into(ivExerciseImage);
            etExerciseDescription.setText(recommended.getString("exerciseDescription"));
            etExerciseInstructions.setText(recommended.getString("exerciseInstructions"));

//            if(currentUser.containsKey("exerciseList") && )
        } catch (ParseException e) {
            e.printStackTrace();
            Log.d(TAG, "nothing found");
        }
    }
}
