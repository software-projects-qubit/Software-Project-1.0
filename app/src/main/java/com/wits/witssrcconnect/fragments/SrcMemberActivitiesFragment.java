package com.wits.witssrcconnect.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wits.witssrcconnect.R;
import com.wits.witssrcconnect.activities.SrcPostActivityActivity;
import com.wits.witssrcconnect.adapters.ViewPagerAdapter;
import com.wits.witssrcconnect.managers.SrcActivityManager;

public class SrcMemberActivitiesFragment extends Fragment {

    public static View v;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return v = inflater.inflate(R.layout.fragment_src_activities, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        init();
    }

    private void init() {

        //display the bottom sheet after user presses the floating action button

        ViewPager viewPager = v.findViewById(R.id.viewpager);
        TabLayout tabLayout = v.findViewById(R.id.tabs);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new AllSrcActivitiesFragment(), "All Activities");
        adapter.addFragment(new MySrcActivitiesFragment(), "My Activities");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        SrcActivityManager.fetchAllActivities(getContext(), null);




    }
}
