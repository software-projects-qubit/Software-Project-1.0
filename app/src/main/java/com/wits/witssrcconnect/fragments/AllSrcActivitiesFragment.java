package com.wits.witssrcconnect.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wits.witssrcconnect.R;
import com.wits.witssrcconnect.managers.UiManager;

import org.json.JSONArray;

public class AllSrcActivitiesFragment extends Fragment {

    @SuppressLint("StaticFieldLeak")
    private static View v = null;
    private static FragmentManager fragmentManager = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        fragmentManager = getChildFragmentManager();
        v = inflater.inflate(R.layout.fragment_src_activity_view, container, false);

        return v;
    }

    public static void init(JSONArray activities) {
        if (v == null) return;
        UiManager.populateWithSrcActivities(v.findViewById(R.id.src_activities_holder), activities, fragmentManager, false);
    }
}