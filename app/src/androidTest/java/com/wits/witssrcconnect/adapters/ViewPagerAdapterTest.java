package com.wits.witssrcconnect.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;

public class ViewPagerAdapterTest {

    private static ViewPagerAdapter v = null;
    private static Fragment testFrag;

    @BeforeClass
    public static void setUp(){
        FragmentManager fm = Mockito.mock(FragmentManager.class);
        testFrag = new Fragment();

        v = new ViewPagerAdapter(fm);
        v.addFragment(new Fragment(), "Test1");
        v.addFragment(testFrag, "");
        v.addFragment(new Fragment(), "");
        v.addFragment(new Fragment(), "");
    }

    @Test
    public void getItem() {
        Fragment f = v.getItem(1);
        assertEquals(testFrag, f);
    }

    @Test
    public void addFragment() {
        v.addFragment(new Fragment(), "");
        v.addFragment(new Fragment(), "");
        v.addFragment(new Fragment(), "");
        assertEquals(7, v.getCount());
    }

    @Test
    public void getCount() {
        v.addFragment(new Fragment(), "");
        assertEquals(8, v.getCount());
    }

    @Test
    public void getPageTitle() {
        assertEquals("Test1", v.getPageTitle(0));
    }
}