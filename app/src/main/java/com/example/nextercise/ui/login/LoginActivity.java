package com.example.nextercise.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nextercise.R;
import com.example.nextercise.ui.HomeActivity;
import com.parse.ParseUser;

public class LoginActivity extends AppCompatActivity {

    public static final String TAG = "LoginActivity";
    private EditText etUsername;
    private EditText etPassword;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        Button btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(v -> {
            Log.i(TAG, "onClick login button");
            String username = etUsername.getText().toString();
            String password = etPassword.getText().toString();
            loginUser(username, password);
        });
    }

    private void loginUser(String username, String password) {
        Log.i(TAG, "Attempting to login user: " + username);

        ParseUser.logInInBackground(username, password, (user, e) -> {
            if (e != null){
                Log.e(TAG, "Issue with login ", e);
                return;
            }
            goHomeActivity();
            Toast.makeText(LoginActivity.this, "Success!", Toast.LENGTH_SHORT).show();
        });
    }

    private void goHomeActivity() {
        Intent i = new Intent(this, HomeActivity.class);
        startActivity(i);
        finish();
    }
}