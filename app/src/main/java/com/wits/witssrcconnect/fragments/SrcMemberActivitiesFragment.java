package com.wits.witssrcconnect.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wits.witssrcconnect.R;
import com.wits.witssrcconnect.bottom_sheets.SrcPostActivityBottomSheet;

public class SrcMemberActivitiesFragment extends Fragment {

    private View v;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return v = inflater.inflate(R.layout.fragment_src_activities, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        init(v);
    }

    private void init(View v) {

        //display the bottom sheet after user presses the floating action button
        v.findViewById(R.id.FAB_src_activity_add).setOnClickListener(v1 -> {
            SrcPostActivityBottomSheet srcPostActivityBottomSheet = new SrcPostActivityBottomSheet();
            srcPostActivityBottomSheet.show(getChildFragmentManager(), "");
        });
    }
}
