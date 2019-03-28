package com.wits.witssrcconnect.bottom_sheets;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.wits.witssrcconnect.R;
import com.wits.witssrcconnect.managers.UiManager;

public class ViewCommentsBottomSheet extends BottomSheetDialogFragment {

    private int activityId;
    private String title, desc;

    @SuppressLint("RestrictedApi")
    @Override
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, style);

        //UiManager.forceBottomSheetToFullyExpand(dialog);

        dialog.setOnShowListener(UiManager::forceBottomSheetToFullyExpand);
        View commentsView = View.inflate(getContext(), R.layout.bottom_sheet_view_comments, null);
        dialog.setContentView(commentsView);

        ((AppCompatTextView) commentsView.findViewById(R.id.comment_view_activity_title)).setText(title);
        ((AppCompatTextView) commentsView.findViewById(R.id.comment_view_activity_desc)).setText(desc);
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }

    public void setActivityTitle(String title) {
        this.title = title;
    }

    public void setActivityDesc(String desc) {
        this.desc = desc;
    }
}
