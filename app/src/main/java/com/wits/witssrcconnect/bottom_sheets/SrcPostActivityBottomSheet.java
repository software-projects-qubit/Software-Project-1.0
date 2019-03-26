package com.wits.witssrcconnect.bottom_sheets;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.View;

import com.wits.witssrcconnect.R;
import com.wits.witssrcconnect.managers.UiManager;

public class SrcPostActivityBottomSheet extends BottomSheetDialogFragment {

    @SuppressLint("RestrictedApi")
    @Override
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, style);

        View content = View.inflate(getContext(), R.layout.bottom_sheet_src_post_activity, null);

        dialog.setContentView(content);
        //force bottom sheet to fully expand
        dialog.setOnShowListener(UiManager::forceBottomSheetToFullyExpand);
    }
}
