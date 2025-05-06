package com.example.groupgetter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private EditText mEmailEText;
    private EditText mUserEtext;
    private EditText mPasswordEText;
    private Button mLoginButton;
    private TextView mRegisterTView;
    private Pattern mEmailPattern;

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
                            Toast.makeText(LoginActivity.this, "Login successful!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            finish();
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

