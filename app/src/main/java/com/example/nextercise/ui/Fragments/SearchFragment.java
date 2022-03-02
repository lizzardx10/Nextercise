package com.example.nextercise.ui.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nextercise.R;

public class SearchFragment extends Fragment {
    public static final String TAG = "SearchFragment";
    private RecyclerView rvSearchResults;

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
        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);
        rvSearchResults = view.findViewById(R.id.rvSearchResults);

        // Steps to use the recycler view
        // 0. create layout for one row in the list
        // 1. create the adapter
        // 2. create the data source
        // 3. set the adapter on the recycler view
        // 4. set the layout manager on the recycler view
    }
}