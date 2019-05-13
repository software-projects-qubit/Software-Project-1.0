package com.wits.witssrcconnect.views;

import android.content.Context;
import android.support.test.InstrumentationRegistry;

import org.junit.Test;

import static org.junit.Assert.*;

public class GradientProgressViewTest {

    private Context c = InstrumentationRegistry.getTargetContext();

    @Test
    public void testCons(){
        new GradientProgressView(c);
        new GradientProgressView(c, null, 1);
        new GradientProgressView(c, null, 1, 1);
    }
}