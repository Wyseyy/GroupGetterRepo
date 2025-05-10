package com.example.groupgetter;
import android.content.Context;
import android.content.SharedPreferences;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;
public class MainActivityTest {
    private Context mockContext;
    private SharedPreferences mockPrefs;
    private SharedPreferences.Editor mockEditor;

    @Before
    public void setUp() {
        mockContext = mock(Context.class);
        mockPrefs = mock(SharedPreferences.class);
        mockEditor = mock(SharedPreferences.Editor.class);

        when(mockContext.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)).thenReturn(mockPrefs);
        when(mockPrefs.edit()).thenReturn(mockEditor);
        when(mockEditor.clear()).thenReturn(mockEditor);
    }

    @Test
    public void logoutUser_clearsSharedPreferences() {
        Session.logoutUser(mockContext);

        verify(mockPrefs).edit();
        verify(mockEditor).clear();
        verify(mockEditor).apply();
    }
}
