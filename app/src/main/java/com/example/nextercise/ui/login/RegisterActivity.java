package com.example.nextercise.ui.login;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nextercise.R;
import com.example.nextercise.ui.HomeActivity;
import com.example.nextercise.ui.MainActivity;
import com.parse.ParseUser;

public class RegisterActivity extends AppCompatActivity {

    public static final String TAG = "RegisterActivity";

    private EditText etUsername;
    private EditText etPassword;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        EditText etConfirmPassword = findViewById(R.id.etConfirmPassword);
        Button btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(v -> {
            Log.i(TAG, "onClick register button");
            if(etPassword.getText().toString().equals(etConfirmPassword.getText().toString())) {
                ParseUser user = new ParseUser();
                // Set the user's username and password, which can be obtained by a forms
                user.setUsername( etUsername.getText().toString());
                user.setPassword(etPassword.getText().toString());
                user.signUpInBackground(e -> {
                    if (e == null) {
                        showAlert("Welcome " + etUsername.getText().toString() + "!");
                    } else {
//                            ParseUser.logOut();
                        Toast.makeText(RegisterActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }
            else {
                Toast.makeText(RegisterActivity.this, "Passwords do not match!!!", Toast.LENGTH_LONG).show();
            }
        });
        }

    private void showAlert(String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this)
                .setTitle("Registration Successful!")
                .setMessage(message)
                .setPositiveButton("OK", (dialog, which) -> {
                    dialog.cancel();
                    // don't forget to change the line below with the names of your Activities
                    Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                });
        AlertDialog ok = builder.create();
        ok.show();
    }
}