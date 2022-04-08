package com.example.nextercise.ui.Fragments;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nextercise.Exercise;
import com.example.nextercise.R;
import com.example.nextercise.SearchAdapter;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {
    public static final String TAG = "SearchFragment";
    private SearchAdapter adapter;
    private List<Exercise> searchResultExercises;
    //private RecyclerView.LayoutManager layoutManager;

    public SearchFragment() {
        // requires empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    // inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }
    // onViewCreated triggered soon after onCreateView()
    // setup occurs here, E.g., view lookups & attaching view listeners
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // setup any handles to view objects here

        // Steps to use the recycler view
        // 0. create layout for one row in the list - done in example_search_result.xml file
        RecyclerView rvSearchResults = view.findViewById(R.id.rvSearchResults);
        rvSearchResults.setHasFixedSize(true);
        // 1. create the adapter
        searchResultExercises = new ArrayList<>();
        adapter = new SearchAdapter(getContext(), searchResultExercises);
        // 2. create the data source
        // 3. set the adapter on the recycler view
        rvSearchResults.setAdapter(adapter);
        // 4. set the layout manager on the recycler view
        rvSearchResults.setLayoutManager(new LinearLayoutManager(getContext()));
        queryExercises();

        EditText etSearchExercise = view.findViewById(R.id.etExerciseSearch);
        etSearchExercise.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });
    }

    private void queryExercises() {
        ParseQuery<Exercise> query = ParseQuery.getQuery(Exercise.class);
        query.setLimit(20);
        query.findInBackground((exercises, e) -> {
            if (e != null) {
                Log.e(TAG, "Issue with getting exercises", e);
                return;
            }
//                Lines to test if search fragment is pulling exercises correctly
//                for (Exercise exercise : exercises) {
//                    Log.i(TAG, "Exercise: " + exercise.getExerciseName());
//                }
            searchResultExercises.addAll(exercises);
            adapter.notifyDataSetChanged();
        });
    }

    private void filter(String searchText) {
        ArrayList<Exercise> filteredList = new ArrayList<>();

        for (Exercise exercise : searchResultExercises) {
            if(exercise.getExerciseName().toLowerCase().contains(searchText.toLowerCase())) {
                filteredList.add(exercise);
            }
        }
        adapter.filterList(filteredList);
    }
}