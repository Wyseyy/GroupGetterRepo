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
public class SignUpActivity extends AppCompatActivity {
    private EditText mEmailEText;
    private EditText mUserEText;
    private EditText mPasswordEText;
    private Button mRegisterButton;
    private Pattern mEmailPattern;
    private TextView mLoginTextView;
    private SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //getSupportActionBar().setLogo(R.drawable.ic;
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        mEmailEText = findViewById(R.id.register_email);
        mPasswordEText = findViewById(R.id.register_password);
        mRegisterButton = findViewById(R.id.register_button);
        mUserEText = findViewById(R.id.register_username);

        mEmailPattern = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
        mSharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);

        //This is the onclick for register button
        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //This gets the email and password from the input fields & the .trim() removes the whitespace after the email address/password
                String email = mEmailEText.getText().toString().trim();
                String password = mPasswordEText.getText().toString().trim();
                String username = mUserEText.getText().toString().trim();

                //This checks if the email address matches the regex pattern
                Matcher matcher = mEmailPattern.matcher(email);
                if (matcher.matches()) {
                    //This saves the username, email address and password the the shared preferences file/
                    SharedPreferences.Editor editor = mSharedPreferences.edit();
                    editor.putString("username", username);
                    editor.putString("email", email);
                    editor.putString("password", password);
                    editor.apply();

                    //If the email address and password are valid, this will then bring the user to the main activity which brings the user to the login page.
                    Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }else if(password == null){
                    //Validation if the user has a correct email and password is empty
                    Toast.makeText(SignUpActivity.this, "Invalid password. Please try again.", Toast.LENGTH_SHORT).show();

                } else {
                    //If the email address is invalid
                    Toast.makeText(SignUpActivity.this, "Invalid email. Please enter a valid email address.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        View mloginTextView = findViewById(R.id.register_login_text);
        //This is an onclick listener for the Click Here to log in text.

        mloginTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //This redirects the user back to the login activity
                Intent loginIntent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(loginIntent);
            }
        });
    }
}
