package com.example.nextercise;

import static com.example.nextercise.R.layout.activity_exercise;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

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


        etExerciseName = findViewById(R.id.etExerciseName);
        ivExerciseImage = findViewById(R.id.ivExcImage);
        etExerciseDescription = findViewById(R.id.etExcDescription);
        etExerciseInstructions = findViewById(R.id.etExerciseInstructions);

    }






}
