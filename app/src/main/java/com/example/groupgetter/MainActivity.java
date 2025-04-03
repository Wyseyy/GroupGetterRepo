package com.example.groupgetter;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import java.util.List;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import kotlinx.coroutines.*;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomMenu;
    private EditText searchEText;
    private Button searchBtn;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        //Create an Intent to launch the LoginActivity
        Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);

        //Start the LoginActivity
        startActivity(loginIntent);
        finish();

        bottomMenu = findViewById(R.id.navigation_gg);
        searchEText = findViewById(R.id.searchEText);
        searchBtn = findViewById(R.id.searchBtn);
        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //not allow the user to use search button until a valid input type is inputted.
        searchBtn.setEnabled(false);

        //validation method so users can only input letters and numbers.
        searchEText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charS, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charS, int i, int i1, int i2) {
                String query = charS.toString().trim();
                searchBtn.setEnabled(query.matches("^[a-zA-Z0-9]+$") && !query.isEmpty());
            }

            @Override
            public void afterTextChanged(Editable edit) {
            }
        });

        searchBtn.setOnClickListener(view -> {
            String q = searchEText.getText().toString().trim();
            if (q.matches("^[a-zA-Z0-9]+$")) {
                searchForSubreddits(q);
            } else {
                searchEText.setError("Only letters and numbers accepted, please try again");
            }
        });

        // Set the OnNavigationItemSelectedListener of the BottomNavigationView
        bottomMenu.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // Check which item in the BottomNavigationView was selected
                switch (item.getItemId()) {
                    // If the Home item was selected
                    case R.id.navigation_home:
                        // Create an Intent to launch the HomeActivity
                        Intent homeIntent = new Intent(MainActivity.this, HomePageActivity.class);
                        // Start the HomeActivity
                        startActivity(homeIntent);
                        return true;

                    // If the Community item was selected
                    case R.id.navigation_community:
                        // Create an Intent to launch the CommunityActivity
                        Intent communityIntent = new Intent(MainActivity.this, CommunityActivity.class);
                        // Start the CommunityActivity
                        startActivity(communityIntent);
                        return true;

                    // If the Customer Service item was selected
                    case R.id.navigation_cservice:
                        // Create an Intent to launch the CServiceActivity
                        Intent cserviceIntent = new Intent(MainActivity.this, CServiceActivity.class);
                        // Start the CServiceActivity
                        startActivity(cserviceIntent);
                        return true;

                    // If no item was selected, return false
                    default:
                        return false;
                }
            }
        });
    }

    private void searchForSubreddits(String query) {
        AuthorityRepo.getAccessToken(token -> {
            if (token != null) {
                Repo.searchSubredditLaunch(token, query).thenAccept(subreddits -> {
                    runOnUiThread(() -> {
                        if (subreddits != null && !subreddits.isEmpty()) {
                            recyclerView.setAdapter(new GGAdapter(subreddits));
                        } else {
                            Toast.makeText(MainActivity.this, "No Subreddit found", Toast.LENGTH_SHORT).show();
                        }
                    });
                }).exceptionally(e -> {
                    runOnUiThread(() -> Toast.makeText(MainActivity.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show());
                    return null;
                });
            } else {
                runOnUiThread(() -> Toast.makeText(MainActivity.this, "Access token could not be found", Toast.LENGTH_SHORT).show());
            }
            return null;
        });
    }
}
