package com.example.nextercise;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.nextercise.ui.Fragments.ProfileFragment;
import com.example.nextercise.ui.Fragments.RecommendFragment;
import com.example.nextercise.ui.Fragments.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    public static final String TAG = "HomeActivity";

    final FragmentManager fragmentManager = getSupportFragmentManager();
    private BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //handle navigation selection

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNav);
        bottomNavigationView.setOnNavigationItemSelectedListener(menuItem -> {
            Fragment fragment;
            switch (menuItem.getItemId()) {
                case R.id.navigation_search:
                    Toast.makeText(HomeActivity.this, "Search!", Toast.LENGTH_SHORT).show();
                    fragment = new SearchFragment();
                    break;
                case R.id.navigation_profile:
                    Toast.makeText(HomeActivity.this, "Profile!", Toast.LENGTH_SHORT).show();
                    fragment = new ProfileFragment();
                    break;

                case R.id.navigation_recommend:
                default:

                    Toast.makeText(HomeActivity.this, "Home!", Toast.LENGTH_SHORT).show();
                    fragment = new RecommendFragment();
                    break;
            }
            fragmentManager.beginTransaction().replace(R.id.flContainer, fragment).commit();
            return true;
        });
        // Set default selection
        bottomNavigationView.setSelectedItemId(R.id.navigation_recommend);

    }
}