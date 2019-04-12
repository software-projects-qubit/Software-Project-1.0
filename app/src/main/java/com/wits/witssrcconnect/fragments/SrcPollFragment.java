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
import com.wits.witssrcconnect.activities.SrcPostPollActivity;
import com.wits.witssrcconnect.adapters.ViewPagerAdapter;
import com.wits.witssrcconnect.managers.SrcPollManager;

public class SrcPollFragment extends Fragment {

    private View v;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return v = inflater.inflate(R.layout.fragment_src_polls, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        init();
    }

    private void init() {

        //display the bottom sheet after user presses the floating action button
        v.findViewById(R.id.FAB_src_poll_add).setOnClickListener(v1 ->
                startActivity(new Intent(v.getContext(), SrcPostPollActivity.class)));

        ViewPager viewPager = v.findViewById(R.id.poll_viewpager);
        TabLayout tabLayout = v.findViewById(R.id.poll_tabs);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new AllSrcPollFragment(), "All Polls");
        adapter.addFragment(new MySrcPollFragment(), "My Polls");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);


        SwipeRefreshLayout pullToRefresh = v.findViewById(R.id.src_poll_swipe_refresh_layout);
        pullToRefresh.setOnRefreshListener(() -> {
            //to remove the spinning circle after success or failure
            //pullToRefresh.setRefreshing(false);
            SrcPollManager.fetchAllPolls(getContext(), pullToRefresh);
        });
    }
}
