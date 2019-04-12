package com.wits.witssrcconnect.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wits.witssrcconnect.R;

import org.json.JSONArray;

public class AllSrcPollFragment extends Fragment {

    @SuppressLint("StaticFieldLeak")
    private static View v = null;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_src_activity_view, container, false);
        return v;
    }


    public static void init(JSONArray polls) {
        if (v == null) return;
    }
}
