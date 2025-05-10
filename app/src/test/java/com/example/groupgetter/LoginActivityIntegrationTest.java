package com.example.groupgetter;

import android.widget.Button;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertTrue;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = 33)
public class LoginActivityIntegrationTest {

    private LoginActivity loginActivity;

    @Before
    public void setUp() {
        loginActivity = Robolectric.buildActivity(LoginActivity.class).create().get();
    }

    @Test
    public void testLoginButtonIsClickable() {
        Button loginButton = loginActivity.findViewById(R.id.login_button);

        assertTrue("Login button should be clickable", loginButton.isClickable());
    }
}

