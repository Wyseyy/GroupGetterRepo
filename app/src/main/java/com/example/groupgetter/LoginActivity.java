package com.example.groupgetter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class LoginActivity extends AppCompatActivity {
    private EditText mEmailEText;
    private EditText mUserEtext;
    private EditText mPasswordEText;
    private Button mLoginButton;
    private TextView mRegisterTView;
    private Pattern mEmailPattern;
    private SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayUseLogoEnabled(true);
        }

        //Find and store references to the email input field, password input field, and login button in the layout
        mEmailEText = findViewById(R.id.email_input);
        mPasswordEText = findViewById(R.id.password_input);
        mLoginButton = findViewById(R.id.login_button);
        mUserEtext = findViewById(R.id.username_input);

        //This is a regular expression pattern for validating email addresses
        mEmailPattern = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
        mSharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);

        //Set the username, email and password input fields to the previously entered values
        String username = mSharedPreferences.getString("username", "");
        String email = mSharedPreferences.getString("email", "");
        String password = mSharedPreferences.getString("password", "");
        mEmailEText.setText(email);
        mPasswordEText.setText(password);
        mUserEtext.setText(username);
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Retrieve the current values entered in the email and password input fields
                String email = mEmailEText.getText().toString().trim();
                String password = mPasswordEText.getText().toString().trim();
                String username = mUserEtext.getText().toString().trim();

                //Validate the email address format using the regular expression pattern
                Matcher matcher = mEmailPattern.matcher(email);
                //Retrieve the saved username, email and password from the shared preferences file
                if (matcher.matches()) {
                    String savedUsername = mSharedPreferences.getString("username", "");
                    String savedEmail = mSharedPreferences.getString("email", "");
                    String savedPassword = mSharedPreferences.getString("password", "");



                    if (email.equals(savedEmail) && password.equals(savedPassword)) {
                        //If the entered email and password match, start the MainActivity and finish the LoginActivity.
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        //If the entered email or password do not match the saved values, display an error message.
                        Toast.makeText(LoginActivity.this, "Email or password invalid, please try again.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    //If the entered email is not in a valid format, display an error toast message
                    Toast.makeText(LoginActivity.this, "Email not valid, please enter email in the format example@gmail.com.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mRegisterTView = findViewById(R.id.register_link);
        mRegisterTView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Start the RegisterActivity
                Intent registeruserIntent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(registeruserIntent);
            }
        });
    }
}
