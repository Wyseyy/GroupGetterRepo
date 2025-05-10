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
public class MainActivityIntegrationTest {

    private MainActivity mainActivity;

    @Before
    public void setUp() {
        mainActivity = Robolectric.buildActivity(MainActivity.class).create().get();
    }

    @Test
    public void testConnectButtonsAreClickable() {
        Button connectBtn = mainActivity.findViewById(R.id.connectBtn);
        Button connectBtn2 = mainActivity.findViewById(R.id.connectBtn2);

        assertTrue("Reddit connect button should be clickable", connectBtn.isClickable());
        assertTrue("Tumblr connect button should be clickable", connectBtn2.isClickable());
    }
}



