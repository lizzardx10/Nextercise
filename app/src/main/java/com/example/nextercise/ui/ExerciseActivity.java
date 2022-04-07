package com.example.nextercise.ui;

import static com.example.nextercise.R.layout.activity_exercise;
import static com.parse.Parse.getApplicationContext;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
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

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

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
                if(cbFavorite.isChecked()) {
                    Log.i(TAG, "Checkbox is checked!" + ParseUser.getCurrentUser().get("exerciseList"));
                    removeFavorite(temp);
                }
                else
                    addFavorite(temp);
            }
        });

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
            etExerciseName.setText(recommended.getExerciseName());
            Glide.with(getApplicationContext()).load(recommended.getExerciseImage().getUrl()).into(ivExerciseImage);
            etExerciseDescription.setText(recommended.getExerciseDescription());
            etExerciseInstructions.setText(recommended.getExerciseInstructions());

//            if(currentUser.containsKey("exerciseList") && )
        } catch (ParseException e) {
            e.printStackTrace();
            Log.d(TAG, "nothing found");
        }
    }
    private Boolean checkFavorite(int currentExerciseId){
        Boolean isSaved = false;
        ParseUser currentUser = ParseUser.getCurrentUser();
        ArrayList<Integer> savedExercises = (ArrayList<Integer>) currentUser.get("exerciseList");
        if(savedExercises.contains("currentExerciseId")) {
            Log.i(TAG, "successfully found favorited item" + currentExerciseId);
            isSaved = true;
        }
        return isSaved;
    }
    private void addFavorite(int exerciseId){
        ParseUser currentUser = ParseUser.getCurrentUser();
        ArrayList<Integer> favoritesList = new ArrayList<Integer>();
        JSONArray jFavoritesList = currentUser.getJSONArray("exerciseList");
        if (jFavoritesList != null){
            for (int i =0; i < jFavoritesList.length(); i++){
                try {
                    favoritesList.add(jFavoritesList.getInt(i));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        favoritesList.add(exerciseId);
    }
    private void removeFavorite(int exerciseId){
        ParseUser currentUser = ParseUser.getCurrentUser();
        ArrayList<Integer> favoritesList = (ArrayList<Integer>) currentUser.get("exerciseList");
        if (favoritesList != null){
            if(favoritesList.contains("exerciseId")) {
                favoritesList.remove("exerciseId");
                currentUser.remove("exerciseList");
                currentUser.put("exerciseList", favoritesList);
            }
        }
    }
}
