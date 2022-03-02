package com.example.nextercise.ui.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.nextercise.R;

public class ProfileFragment extends Fragment {
    public static final String TAG = "ProfileFragment";

    private TextView etChangeUsername;
    private TextView etChangePassword;
    private TextView etChangeEmail;
    private Button btnChangeProfInfo;

    public ProfileFragment() {
        // requires empty public constructor
    }

    // onCreateView called when Fragment should create its View object hierarchy, dynamically or via XML layout inflation
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        inflate the layout for this fragment, defines xml file for fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }
    // onViewCreated triggered soon after onCreateView()
    // setup occurs here, E.g., view lookups & attaching view listeners
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // setup any handles to view objects here
        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);

        etChangeUsername = view.findViewById(R.id.etChangeUsername);
        etChangeEmail = view.findViewById(R.id.etChangeEmail);
        etChangePassword = view.findViewById(R.id.etChangePassword);
        btnChangeProfInfo = view.findViewById(R.id.btnChangeProfInfo);
    }

}