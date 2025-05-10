package com.example.groupgetter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    public EditText mEmailEText;
    public EditText mUserEtext;
    public EditText mPasswordEText;
    public Button mLoginButton;
    public TextView mRegisterTView;
    public Pattern mEmailPattern;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayUseLogoEnabled(true);
        }

        mEmailEText = findViewById(R.id.email_input);
        mPasswordEText = findViewById(R.id.password_input);
        mLoginButton = findViewById(R.id.login_button);
        mUserEtext = findViewById(R.id.username_input);
        mRegisterTView = findViewById(R.id.register_link);

        mEmailPattern = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmailEText.getText().toString().trim();
                String username = mUserEtext.getText().toString().trim();
                String password = mPasswordEText.getText().toString().trim();

                // Allow login via email or username
                String emailOrUsername = !email.isEmpty() ? email : username;

                if (emailOrUsername.isEmpty() || password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Optional email format check if user enters email
                if (!username.isEmpty() && email.isEmpty()) {
                    // Username login, skip email check
                } else {
                    Matcher matcher = mEmailPattern.matcher(emailOrUsername);
                    if (!matcher.matches()) {
                        Toast.makeText(LoginActivity.this, "Enter a valid email", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }

                LoginRequest request = new LoginRequest(emailOrUsername, password);
                Service service = Client.getService();
                service.login(request).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            //store the username in a SharedPreferences after login to allow for the username to be used in CommunityActivity
                            SharedPreferences shared = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                            SharedPreferences.Editor editor = shared.edit();
                            editor.putString("username", username);
                            editor.apply();

                            //Dialog box for terms and conditions
                            AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                            builder.setTitle("Welcome to GroupGetter!");
                            builder.setMessage("By accepting this agreement, " +
                                    "you understand that this application has no control of anything outside of the application itself, " +
                                    "any people who may prove malicious on other applications and groups are not something our team can do much about outside of reporting them. " +
                                    "Please exercise caution with anyone you interact with outside of this application through our integrated social medias," +
                                    " be safe and be responsible. " +
                                    "Click Accept to say you understand this and wish to continue, " +
                                    "or Decline to return to the login screen and not continue.");
                            builder.setCancelable(false);

                            builder.setPositiveButton("Accept", (dialog, which) -> {
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            });

                            builder.setNegativeButton("Decline", (dialog, which) -> {
                                Toast.makeText(LoginActivity.this, "Login cancelled", Toast.LENGTH_SHORT).show();
                                // Optionally clear fields
                                mEmailEText.setText("");
                                mUserEtext.setText("");
                                mPasswordEText.setText("");
                            });

                            AlertDialog dialog = builder.create();
                            dialog.show();
                        } else {
                            Toast.makeText(LoginActivity.this, "Login failed. Check credentials.", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(LoginActivity.this, "Network error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        mRegisterTView.setOnClickListener(view -> {
            Intent registerIntent = new Intent(LoginActivity.this, SignUpActivity.class);
            startActivity(registerIntent);
        });
    }
}

