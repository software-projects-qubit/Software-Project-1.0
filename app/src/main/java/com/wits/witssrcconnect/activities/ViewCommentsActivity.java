package com.wits.witssrcconnect.activities;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.SwitchCompat;
import android.text.TextUtils;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.wits.witssrcconnect.R;
import com.wits.witssrcconnect.managers.SrcActivityManager;
import com.wits.witssrcconnect.managers.UserManager;
import com.wits.witssrcconnect.utils.ServerUtils;

import java.util.Objects;

import static com.wits.witssrcconnect.managers.UiManager.getDateTime;

public class ViewCommentsActivity extends AppCompatActivity {

    @SuppressLint("StaticFieldLeak")
    public static LinearLayout commentsHolder = null;
    public static int activityId = -1;
    public static String title = "", desc = "";

    public static void setActivityId(int activityId) {
        ViewCommentsActivity.activityId = activityId;
    }

    public static void setActivityTitle(String title) {
        ViewCommentsActivity.title = title;
    }

    public static void setActivityDesc(String desc) {
        ViewCommentsActivity.desc = desc;
    }

    @Override
    protected void onCreate(Bundle b){
        super.onCreate(b);

        setContentView(R.layout.activity_comments);

        Objects.requireNonNull(getSupportActionBar()).setElevation(0);

        //create reference to Linear layout that will hold all the comments
        commentsHolder = findViewById(R.id.comments_holder);
        //this function will fetch all the comments from the database
        //related to the selected activity and then display them on the linear layout
        SrcActivityManager.populateViewWithComments(commentsHolder, activityId);

        ((AppCompatTextView) findViewById(R.id.comment_view_activity_title)).setText(title);
        ((AppCompatTextView) findViewById(R.id.comment_view_activity_desc)).setText(desc);

        //create a reference to the anonymous comment switch
        SwitchCompat anonymitySwitch = findViewById(R.id.anonymity_switch_vc);

        //declare a variable to save the anonymity switch
        final int[] anonymityTracker = {ServerUtils.ANONYMOUS_COMMENT_OFF};

        //this allows the app to listen for anonymous comment states (on and off) and displays
        //a notification at the change of each state
        anonymitySwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            handleSwitch(anonymityTracker, isChecked, ViewCommentsActivity.this);
        });

        //does the same basic functions of commenting
        TextInputEditText comment = findViewById(R.id.input_comment_vc);
        findViewById(R.id.send_comment_vc).setOnClickListener(v -> handleSendComment(comment, anonymityTracker));
    }

    public static void handleSendComment(TextInputEditText comment, int[] anonymityTracker){
        String sComment = Objects.requireNonNull(comment.getText()).toString().trim();
        if (TextUtils.isEmpty(sComment)) {
            comment.setError("Comment required");
        } else {
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
    }

    public static void handleSwitch(int[] anonymityTracker, boolean isChecked, Context c){
        anonymityTracker[0] = isChecked ? ServerUtils.ANONYMOUS_COMMENT_ON : ServerUtils.ANONYMOUS_COMMENT_OFF;
        String message;
        if (isChecked) message = "Anonymous comment on";
        else message = "Anonymous comment off";
        Toast.makeText(c, message, Toast.LENGTH_SHORT).show();
    }

    public static void updateComments(){
        if (commentsHolder != null) SrcActivityManager.populateViewWithComments(commentsHolder, activityId);
    }
}
