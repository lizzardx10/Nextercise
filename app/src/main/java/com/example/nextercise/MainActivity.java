package com.example.nextercise;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nextercise.ui.login.LoginActivity;
import com.example.nextercise.ui.login.RegisterActivity;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // initialize views here
        Button btnLogin = findViewById(R.id.btnLogin);
        Button btnCreateAcc = findViewById(R.id.btnCreateAcc);
        // set onclicklistener for your button
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick login button");
                goLoginActivty();
            }
        });

        btnCreateAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick create account button");
                goRegisterActivty();
            }
        });

    }

    private void goLoginActivty() {
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
    }

    private void goRegisterActivty() {
        Intent i = new Intent(this, RegisterActivity.class);
        startActivity(i);
    }
}