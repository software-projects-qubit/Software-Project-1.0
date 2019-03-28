package com.wits.witssrcconnect.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wits.witssrcconnect.R;
import com.wits.witssrcconnect.managers.SrcActivityManager;
import com.wits.witssrcconnect.managers.UiManager;

import org.json.JSONArray;


public class StudentViewSrcActivitiesFragment extends Fragment {

    private static View v;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_student_view_src_activities, container, false);
        init();
        return v;
    }

    private void init() {
        if (v == null) return;
        SrcActivityManager.fetchAllActivities(getContext(), null);
        SwipeRefreshLayout pullToRefresh = v.findViewById(R.id.pullToRefreshSrcActivitiesStudent);
        pullToRefresh.setOnRefreshListener(() -> SrcActivityManager.fetchAllActivities(getContext(), pullToRefresh));
    }

    public static void init(JSONArray activities) {
        if (v == null) return;
        UiManager.populateWithSrcActivities(v.findViewById(R.id.student_src_activities_view), activities);
    }
}
