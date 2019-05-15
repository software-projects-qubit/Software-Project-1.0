package com.wits.witssrcconnect.bottom_sheets;


import android.app.Dialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.View;

import com.wits.witssrcconnect.R;
import com.wits.witssrcconnect.managers.UiManager;

public class PollVoteBottomSheet extends BottomSheetDialogFragment {

    private int pollId;
    private String title, desc;
    private int pollType;
    private String[] pollChoices;

    @Override
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, style);

        dialog.setOnShowListener(UiManager::forceBottomSheetToFullyExpand);
        View voteView = View.inflate(getContext(), R.layout.bottom_sheet_view_votes, null);
        dialog.setContentView(voteView);
    }

    public void setPollId(int pollId){
        this.pollId = pollId;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setDesc(String desc){
        this.desc = desc;
    }

    public void setPollType(int pollType){
        this.pollType = pollType;
    }

    public void setPollChoices(String[] pollChoices){
        this.pollChoices = pollChoices;
    }
}
