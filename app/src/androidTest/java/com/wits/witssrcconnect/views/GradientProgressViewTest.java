package com.wits.witssrcconnect.views;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread;
import static org.mockito.ArgumentMatchers.anyInt;

@RunWith(AndroidJUnit4.class)
public class GradientProgressViewTest {

    private Context c = InstrumentationRegistry.getTargetContext();

    @Test
    public void testConstructor(){
        try {
            runOnUiThread(()->{
                new GradientProgressView(c);
                new GradientProgressView(c, null);
                new GradientProgressView(c, null, anyInt());
                new GradientProgressView(c, null, anyInt(), anyInt());
            });
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}