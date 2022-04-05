package com.example.nextercise.ui.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nextercise.Exercise;
import com.example.nextercise.ui.HomeActivity;
import com.example.nextercise.ui.ListAdapter;
import com.example.nextercise.ui.MainActivity;
import com.parse.FindCallback;
import com.parse.ParseUser;
import com.parse.ParseException;
import com.parse.ParseQuery;

import com.example.nextercise.R;

import java.util.ArrayList;
import java.util.List;

public class ProfileFragment extends Fragment {
    public static final String TAG = "ProfileFragment";

    private TextView etChangeUsername;
    private TextView etChangePassword;
    private Button btnChangeProfInfo;
    private Button btnLogout;
    private RecyclerView rvSavedExerciseList;
    private List<Exercise> savedExercises;
    private ListAdapter adapter;

    public ProfileFragment() {
        // requires empty public constructor
    }

    // onCreateView called when Fragment should create its View object hierarchy, dynamically or via XML layout inflation
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // inflate the layout for this fragment, defines xml file for fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);

    }

    // onViewCreated triggered soon after onCreateView()
    // setup occurs here, E.g., view lookups & attaching view listeners
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // setup any handles to view objects here
        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);
        ParseUser currentUser = ParseUser.getCurrentUser();
        Log.i("tester", String.valueOf(currentUser.getUsername()));

        etChangeUsername = view.findViewById(R.id.etChangeUsername);
        etChangePassword = view.findViewById(R.id.etChangePassword);
        btnChangeProfInfo = view.findViewById(R.id.btnChangeProfInfo);
        btnLogout = view.findViewById(R.id.btnLogout);

        btnChangeProfInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick Change Profile Info Button");
                updateProfileInfo();
            }
        });
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser.logOutInBackground(e -> {
                    if (e==null){
                        Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        // Steps to use the recycler view
        // 0. create layout for one row in the list - done in example_list_item.xml file
        rvSavedExerciseList = view.findViewById(R.id.rvSavedExerciseList);
        rvSavedExerciseList.setHasFixedSize(true);
        // 1. create the adapter
        savedExercises = new ArrayList<>();
        adapter = new ListAdapter(getContext(),savedExercises);
        // 2. create the data source
        // 3. set the adapter on the recycler view
        rvSavedExerciseList.setAdapter(adapter);
        // 4. set the layout manager on the recycler view
        rvSavedExerciseList.setLayoutManager(new LinearLayoutManager(getContext()));
        queryExercises();
    }

    private void updateProfileInfo() {
    ParseUser currentUser = ParseUser.getCurrentUser();
    Log.i(TAG, String.valueOf(currentUser.getUsername()));
        if(currentUser != null) {
            currentUser.setUsername(etChangeUsername.getText().toString());
            currentUser.setPassword(etChangePassword.getText().toString());
            Log.i(TAG, "user credentials updated!" + String.valueOf(currentUser.getUsername()));
            currentUser.saveInBackground();
         }else{
             Log.e(TAG, "Issue with changing user credentials");
        }
    }
    
    private void queryExercises() {
        ParseQuery<Exercise> query = ParseQuery.getQuery(Exercise.class);
        query.whereExists("exerciseList");
        query.findInBackground(new FindCallback<Exercise>() {
            @Override
            public void done(List<Exercise> exercises, ParseException e) {
                if (e != null) {
                    Log.e(TAG, "Issue with getting exercises", e);
                    return;
                }
                for (Exercise exercise : exercises) {
                    Log.i(TAG, "Exercise: " + exercise.getExerciseName());
                }
                savedExercises.addAll(exercises);
                adapter.notifyDataSetChanged();
            }
        });
    }
}