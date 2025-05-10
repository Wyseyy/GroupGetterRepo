package com.example.groupgetter;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Pattern;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {

    private EditText mEmailInput;
    private EditText mUsernameInput;
    private EditText mPasswordInput;
    private Button mRegisterButton;
    private TextView mAlreadyHaveAccount;
    private ProgressBar mProgressBar;

    private final Pattern emailPattern = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mUsernameInput = findViewById(R.id.register_username);
        mEmailInput = findViewById(R.id.register_email);
        mPasswordInput = findViewById(R.id.register_password);
        mRegisterButton = findViewById(R.id.register_button);
        mAlreadyHaveAccount = findViewById(R.id.register_login_text);
        mProgressBar = findViewById(R.id.register_progressbar);


        mRegisterButton.setOnClickListener(v -> registerUser());

        mAlreadyHaveAccount.setOnClickListener(v -> {
            startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
            finish();
        });
    }

    private void registerUser() {
        String email = mEmailInput.getText().toString().trim();
        String username = mUsernameInput.getText().toString().trim();
        String password = mPasswordInput.getText().toString().trim();

        if (!ValidationTest.isValidEmail(email)) {
            Toast.makeText(this, "Invalid email address", Toast.LENGTH_SHORT).show();
            return;
        }

        if (username.isEmpty()) {
            Toast.makeText(this, "Username cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        if (password.isEmpty()) {
            Toast.makeText(this, "Password cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        if (password.length() < 6) {
            Toast.makeText(this, "Password must be at least 6 characters", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!isInternetAvailable()) {
            Toast.makeText(this, "No internet connection", Toast.LENGTH_SHORT).show();
            return;
        }

        mRegisterButton.setEnabled(false);
        mProgressBar.setVisibility(View.VISIBLE);

        RegRequest regRequest = new RegRequest(email, username, password);
        Call<ResponseBody> call = Client.getService().register(regRequest);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                mRegisterButton.setEnabled(true);
                mProgressBar.setVisibility(View.GONE);

                if (response.isSuccessful()) {
                    Toast.makeText(SignUpActivity.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                    finish();
                } else {
                    Toast.makeText(SignUpActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                mRegisterButton.setEnabled(true);
                mProgressBar.setVisibility(View.GONE);
                Toast.makeText(SignUpActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean isInternetAvailable() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            return activeNetwork != null && activeNetwork.isConnected();
        }
        return false;
    }
}

