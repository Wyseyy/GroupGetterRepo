package com.example.groupgetter;

import android.widget.Button;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = 33)
public class CommunityIntegrationTest {

    private CommunityActivity communityActivity;

    @Before
    public void setUp() {
        communityActivity = Robolectric.buildActivity(CommunityActivity.class).create().get();
    }

    @Test
    public void testPostMessage() {
        Message testMessage = new Message("Test message", "User");

        assertNotNull("Message text should not be null", testMessage.getMessage());
        assertEquals("Test message", testMessage.getMessage());
    }
}




