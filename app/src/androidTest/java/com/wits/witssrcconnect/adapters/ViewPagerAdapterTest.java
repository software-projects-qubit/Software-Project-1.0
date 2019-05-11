package com.wits.witssrcconnect.adapters;

import android.support.test.runner.AndroidJUnit4;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import java.util.Objects;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class ViewPagerAdapterTest {

    private static FragmentManager fm = Mockito.mock(FragmentManager.class);
    private static ViewPagerAdapter viewPagerAdapter;

    @BeforeClass
    public static void setUp(){
        viewPagerAdapter = new ViewPagerAdapter(fm);
    }

    @Test
    public void testAll(){
        Fragment f = new Fragment();
        viewPagerAdapter.addFragment(f, "test");
        Fragment ff = viewPagerAdapter.getItem(0);
        String t = (String) viewPagerAdapter.getPageTitle(0);
        int c = viewPagerAdapter.getCount();

        boolean test1 = f == ff;
        boolean test2 = Objects.requireNonNull(t).equals("test");
        boolean test3 = c == 1;
        assertTrue(test1 && test2 && test3);
    }
}