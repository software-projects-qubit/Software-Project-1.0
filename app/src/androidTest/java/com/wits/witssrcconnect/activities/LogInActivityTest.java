package com.wits.witssrcconnect.activities;

import android.os.Bundle;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class LogInActivityTest {

    private static LogInActivity logInActivity;
    private static Bundle b = Mockito.mock(Bundle.class);

    @BeforeClass
    public static void init(){
        logInActivity = new LogInActivity();
    }

    @Test
    public void onCreate() {
        //logInActivity.onCreate(b);
        //assertNotNull(logInActivity.cc1);
    }

    @Test
    public void onBackPressed() {
    }
}