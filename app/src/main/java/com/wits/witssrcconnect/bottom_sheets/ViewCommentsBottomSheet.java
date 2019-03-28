package com.wits.witssrcconnect.bottom_sheets;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.SwitchCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.wits.witssrcconnect.R;
import com.wits.witssrcconnect.managers.SrcActivityManager;
import com.wits.witssrcconnect.managers.UiManager;
import com.wits.witssrcconnect.managers.UserManager;
import com.wits.witssrcconnect.utils.ServerUtils;

import java.util.Objects;

import static com.wits.witssrcconnect.managers.UiManager.getDateTime;

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

        //create reference to Linear layout that will hold all the comments
        LinearLayout commentsHolder = commentsView.findViewById(R.id.comments_holder);
        SrcActivityManager.populateViewWithComments(commentsHolder, activityId);

        ((AppCompatTextView) commentsView.findViewById(R.id.comment_view_activity_title)).setText(title);
        ((AppCompatTextView) commentsView.findViewById(R.id.comment_view_activity_desc)).setText(desc);

        //create a reference to the anonymous comment switch
        SwitchCompat anonymitySwitch = commentsView.findViewById(R.id.anonymity_switch_vc);

        //declare a variable to save the anonymity switch
        final int[] anonymityTracker = {ServerUtils.ANONYMOUS_COMMENT_OFF};

        //this allows the app to listen for anonymous comment states (on and off) and displays
        //a notification at the change of each state
        anonymitySwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            anonymityTracker[0] = isChecked ? ServerUtils.ANONYMOUS_COMMENT_ON : ServerUtils.ANONYMOUS_COMMENT_OFF;
            String message;
            if (isChecked) message = "Anonymous comment on";
            else message = "Anonymous comment off";
            Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
        });

        //does the same basic functions of commenting
        TextInputEditText comment = commentsView.findViewById(R.id.input_comment_vc);
        commentsView.findViewById(R.id.send_comment_vc).setOnClickListener(v -> {
            String sComment = Objects.requireNonNull(comment.getText()).toString().trim();
            if (TextUtils.isEmpty(sComment)){
                comment.setError("Comment required");
            }
            else{
                sComment = sComment.replace("\n", "\\n");
                ContentValues cv = new ContentValues();

                String[] dateTime = getDateTime();

                cv.put(ServerUtils.ACTION, ServerUtils.POST_COMMENT);
                cv.put(ServerUtils.ACTIVITY_ID, String.valueOf(activityId));
                cv.put(ServerUtils.STUDENT_USERNAME, UserManager.getCurrentlyLoggedInUsername());
                cv.put(ServerUtils.STUDENT_COMMENT, sComment);
                cv.put(ServerUtils.STUDENT_ANONYMITY, String.valueOf(anonymityTracker[0]));
                cv.put(ServerUtils.STUDENT_DATE, dateTime[0]);
                cv.put(ServerUtils.STUDENT_TIME, dateTime[1]);

                SrcActivityManager.postComment(cv, comment);
            }
        });
    }

    //sets the id, title, desc of the activity the user pressed
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
