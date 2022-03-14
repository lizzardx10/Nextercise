package com.example.nextercise.ui.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import com.parse.ParseUser;
import com.parse.ParseException;
import com.parse.ParseQuery;

import com.example.nextercise.R;

public class ProfileFragment extends Fragment {
    public static final String TAG = "ProfileFragment";

    private TextView etChangeUsername;
    private TextView etChangePassword;
    private Button btnChangeProfInfo;

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

        btnChangeProfInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick Change Profile Info Button");
                updateProfileInfo();
            }
        });
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


}