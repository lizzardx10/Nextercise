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
import com.example.nextercise.ListAdapter;
import com.example.nextercise.R;
import com.example.nextercise.ui.MainActivity;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class ProfileFragment extends Fragment
{
    public static final String TAG = "ProfileFragment";

    private TextView etChangeUsername;
    private TextView etChangePassword;
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
        etChangeUsername = view.findViewById(R.id.etChangeUsername);
        etChangePassword = view.findViewById(R.id.etChangePassword);
        Button btnChangeProfInfo = view.findViewById(R.id.btnChangeProfInfo);
        Button btnLogout = view.findViewById(R.id.btnLogout);

        btnChangeProfInfo.setOnClickListener(v -> {
            Log.i(TAG, "onClick Change Profile Info Button");
            updateProfileInfo();
        });

        btnLogout.setOnClickListener(v -> {
            ParseUser.logOutInBackground(e -> {
                if (e!=null){
                    Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
            goMainActivity();
        });

        // Steps to use the recycler view
        // 0. create layout for one row in the list - done in example_list_item.xml file
        RecyclerView rvSavedExerciseList = view.findViewById(R.id.rvSavedExerciseList);
        rvSavedExerciseList.setHasFixedSize(true);
        // 1. create the adapter
        savedExercises = new ArrayList<>();
        adapter = new ListAdapter(getContext(),savedExercises);
        // 2. create the data source
        // 3. set the adapter on the recycler view
        rvSavedExerciseList.setAdapter(adapter);
        // 4. set the layout manager on the recycler view
        rvSavedExerciseList.setLayoutManager(new LinearLayoutManager(getContext()));
        queryExerciseIds();
    }

    private void updateProfileInfo() {
    ParseUser currentUser = ParseUser.getCurrentUser();
    Log.i(TAG, String.valueOf(currentUser.getUsername()));
        currentUser.setUsername(etChangeUsername.getText().toString());
        currentUser.setPassword(etChangePassword.getText().toString());
        Log.i(TAG, "user credentials updated!" + currentUser.getUsername());
        currentUser.saveInBackground();
    }
    
    private void queryExerciseIds() {
        ParseUser currentUser = ParseUser.getCurrentUser();
        // List<Integer> exerciseIdList = new ArrayList<>();
        ParseQuery<ParseUser> query = ParseQuery.getQuery(ParseUser.class);
        query.whereEqualTo("objectId", currentUser.getObjectId());
        query.findInBackground((users, e) -> {
            if (e != null) {
                Log.e(TAG, "Issue with getting saved exercises", e);
                return;
            }
            for (ParseUser user : users) {
                List<Integer> tempList;
//                Lines to test if saved exercises for user are being loaded properly
//                    Log.i(TAG, "Saved List: " + user.get("exerciseList").toString());
//                    Log.i(TAG, String.valueOf(user.get("exerciseList").getClass()));
                // exerciseList is stored as a list of Integers in the database
                tempList = (List<Integer>) user.get("exerciseList");
                if (tempList != null) {
                    for (int i = 0; i < tempList.size(); i++) {
                        Log.i(TAG, String.valueOf(tempList.get(i).getClass()));
                        queryExercise(tempList.get(i));
                        adapter.notifyItemInserted(i);
                    }
                }
                //adapter.notifyItemRangeChanged(0, tempList.size());
                //adapter.notifyDataSetChanged();
            }
        });
    }

    private void queryExercise(int exerciseId) {
        ParseQuery<Exercise> query = ParseQuery.getQuery("Exercise");
        query.whereEqualTo("exerciseId", exerciseId);
        // Execute the find asynchronously
        try {
            Exercise recommended = query.getFirst();
            savedExercises.add(recommended);
        } catch (ParseException e) {
            e.printStackTrace();
            Log.d(TAG, "nothing found");
        }
    }
    private void goMainActivity() {
        Intent i = new Intent(getContext(), MainActivity.class);
        startActivity(i);
    }
}