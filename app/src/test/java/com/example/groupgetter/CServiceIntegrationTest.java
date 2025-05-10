package com.example.groupgetter;

import static org.junit.Assert.*;

import org.junit.Test;

public class CServiceIntegrationTest {

    @Test
    public void testCServiceRequestCreation() {
        String testName = "Eoin";
        String testEmail = "Eoin@example.com";
        String testMessage = "This is a test message.";

        CServiceRequest request = new CServiceRequest(testName, testEmail, testMessage);

        assertNotNull("Name should not be null", request.getName());
        assertEquals("Eoin", request.getName());

        assertNotNull("Email should not be null", request.getEmail());
        assertEquals("Eoin@example.com", request.getEmail());

        assertNotNull("Message should not be null", request.getMessage());
        assertEquals("This is a test message.", request.getMessage());
    }
}
