package com.example.groupgetter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {
    private EditText mEmailEText;
    private EditText mUserEText;
    private EditText mPasswordEText;
    private Button mRegisterButton;
    private Pattern mEmailPattern;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayUseLogoEnabled(true);
        }

        mEmailEText = findViewById(R.id.register_email);
        mPasswordEText = findViewById(R.id.register_password);
        mRegisterButton = findViewById(R.id.register_button);
        mUserEText = findViewById(R.id.register_username);

        mEmailPattern = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");

        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmailEText.getText().toString().trim();
                String password = mPasswordEText.getText().toString().trim();
                String username = mUserEText.getText().toString().trim();

                Matcher matcher = mEmailPattern.matcher(email);

                if (!matcher.matches()) {
                    Toast.makeText(SignUpActivity.this, "Invalid email address.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.isEmpty()) {
                    Toast.makeText(SignUpActivity.this, "Password cannot be empty.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (username.isEmpty()) {
                    Toast.makeText(SignUpActivity.this, "Username cannot be empty.", Toast.LENGTH_SHORT).show();
                    return;
                }

                registerUser(email, username, password);
            }
        });

        TextView mLoginTextView = findViewById(R.id.register_login_text);
        mLoginTextView.setOnClickListener(view -> {
            Intent loginIntent = new Intent(SignUpActivity.this, LoginActivity.class);
            startActivity(loginIntent);
        });
    }

    private void registerUser(String email, String username, String password) {
        Service service = Client.getService(); // Use shared Retrofit instance
        RegRequest request = new RegRequest(email, username, password);
        Call<ResponseBody> call = service.register(request);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(SignUpActivity.this, "Registered successfully!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SignUpActivity.this, MainActivity.class));
                    finish();
                } else {
                    Toast.makeText(SignUpActivity.this, "Registration failed. Try different credentials.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(SignUpActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}

