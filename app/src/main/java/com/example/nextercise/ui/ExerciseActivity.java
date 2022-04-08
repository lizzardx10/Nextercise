package com.example.nextercise.ui;

import static com.example.nextercise.R.layout.activity_exercise;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.nextercise.Exercise;
import com.example.nextercise.R;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;

public class ExerciseActivity extends AppCompatActivity {
    public static final String TAG = "ExerciseActivity";
    private TextView etExerciseName;
    private ImageView ivExerciseImage;
    private TextView etExerciseDescription;
    private TextView etExerciseInstructions;
    private CheckBox cbFavorite;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_exercise);

        Intent intent = getIntent();
        int temp = intent.getIntExtra("int_value", -1);

        etExerciseName = findViewById(R.id.etExerciseName);
        ivExerciseImage = findViewById(R.id.ivExerciseImage);
        etExerciseDescription = findViewById(R.id.etExerciseDescription);
        etExerciseInstructions = findViewById(R.id.etExerciseInstructions);
        cbFavorite = findViewById(R.id.cbFavorite);
        loadExercise(temp);
        cbFavorite.setChecked(checkFavorite(temp));
        cbFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!cbFavorite.isChecked()) {
                    removeFavorite(temp);
                    Log.i(TAG, "Checkbox un-checked!" + ParseUser.getCurrentUser().get("exerciseList"));
                }
                else if(cbFavorite.isChecked()) {
                    addFavorite(temp);
                    Log.i(TAG, "Checkbox checked! " + ParseUser.getCurrentUser().get("exerciseList"));
                }
                else
                    Log.i(TAG, "Something went wrong");
            }
        });
    }

    private void loadExercise(int exerciseId) {
        ParseQuery<Exercise> query = ParseQuery.getQuery("Exercise");
        query.whereEqualTo("exerciseId", exerciseId);
        ParseUser currentUser = ParseUser.getCurrentUser();
        // Execute the find asynchronously
        try {
            Exercise recommended = query.getFirst();
            Log.i(TAG, recommended.getString("exerciseName"));
            etExerciseName.setText(recommended.getExerciseName());
            Glide.with(getApplicationContext()).load(recommended.getExerciseImage().getUrl()).into(ivExerciseImage);
            etExerciseDescription.setText(recommended.getExerciseDescription());
            etExerciseInstructions.setText(recommended.getExerciseInstructions());
        }
        catch (ParseException e) {
            e.printStackTrace();
            Log.d(TAG, "nothing found");
        }
    }
    private Boolean checkFavorite(Integer currentExerciseId){
        Boolean isSaved = false;
        ParseUser currentUser = ParseUser.getCurrentUser();
        ArrayList<Integer> savedExercises = (ArrayList<Integer>) currentUser.get("exerciseList");
        if(currentUser.get("exerciseList") == null) {
            currentUser.put("exerciseList", new ArrayList<Integer>());
            currentUser.saveInBackground();
        }
        else
            if(savedExercises.contains(currentExerciseId)) {
                Log.i(TAG, "successfully found favorited item" + currentExerciseId);
                isSaved = true;
            }
        return isSaved;
    }
    private void addFavorite(Integer exerciseId){
        ParseUser currentUser = ParseUser.getCurrentUser();
        ArrayList<Integer> favoritesList = (ArrayList<Integer>) currentUser.get("exerciseList");
        favoritesList.add(new Integer(exerciseId));
        currentUser.put("exerciseList", favoritesList);
        currentUser.saveInBackground();
    }
    private void removeFavorite(int exerciseId){
        ParseUser currentUser = ParseUser.getCurrentUser();
        ArrayList<Integer> favoritesList = (ArrayList<Integer>) currentUser.get("exerciseList");
        if (favoritesList != null){
            if(favoritesList.contains(exerciseId)) {
                favoritesList.remove(new Integer(exerciseId));
                currentUser.put("exerciseList", favoritesList);
                currentUser.saveInBackground();
            }
        }
    }
}
