package com.example.nextercise.ui;

import static com.example.nextercise.R.layout.activity_exercise;
import static com.parse.Parse.getApplicationContext;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.nextercise.Exercise;
import com.example.nextercise.R;
import com.example.nextercise.ui.Fragments.RecommendFragment;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.concurrent.ThreadLocalRandom;

public class ExerciseActivity extends AppCompatActivity {
    public static final String TAG = "ExerciseActivity";
    private TextView etExerciseName;
    private ImageView ivExerciseImage;
    private TextView etExerciseDescription;
    private TextView etExerciseInstructions;

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
        loadExercise(temp);

    }


    private void loadExercise(int exerciseId) {
        ParseQuery<Exercise> query = ParseQuery.getQuery("Exercise");
        query.whereEqualTo("exerciseId", exerciseId);

        // Execute the find asynchronously
        try {
            Exercise recommended = query.getFirst();
            Log.i(TAG, recommended.getString("exerciseName"));
            etExerciseName.setText(recommended.getString("exerciseName"));
            Glide.with(getApplicationContext()).load(recommended.getExerciseImage().getUrl()).into(ivExerciseImage);
            etExerciseDescription.setText(recommended.getString("exerciseDescription"));
            etExerciseInstructions.setText(recommended.getString("exerciseInstructions"));
        } catch (ParseException e) {
            e.printStackTrace();
            Log.d(TAG, "nothing found");
        }
    }



}
