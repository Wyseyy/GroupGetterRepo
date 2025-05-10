package com.example.groupgetter;

import static org.junit.Assert.*;
import static org.robolectric.Shadows.shadowOf;

import android.content.Intent;
import android.widget.TextView;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.android.controller.ActivityController;
import org.robolectric.annotation.Config;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = 33)
public class SignUpIntegrationTest {

    @Test
    public void clickingAlreadyHaveAccount_shouldStartLoginActivity() {
        ActivityController<SignUpActivity> controller = Robolectric.buildActivity(SignUpActivity.class).create().start().resume();
        SignUpActivity activity = controller.get();

        TextView alreadyHaveAccountText = activity.findViewById(R.id.register_login_text);
        alreadyHaveAccountText.performClick();

        Intent expectedIntent = new Intent(activity, LoginActivity.class);
        Intent actualIntent = shadowOf(activity).getNextStartedActivity();

        assertEquals(expectedIntent.getComponent(), actualIntent.getComponent());
    }
}
