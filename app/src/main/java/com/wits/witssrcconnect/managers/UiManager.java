package com.wits.witssrcconnect.managers;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.SwitchCompat;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.wits.witssrcconnect.R;
import com.wits.witssrcconnect.activities.LogInActivity;
import com.wits.witssrcconnect.activities.PollVoteActivity;
import com.wits.witssrcconnect.activities.SrcPostActivityActivity;
import com.wits.witssrcconnect.activities.ViewCommentsActivity;
import com.wits.witssrcconnect.services.ServerCommunicator;
import com.wits.witssrcconnect.utils.ServerUtils;
import com.wits.witssrcconnect.utils.UserUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class UiManager {

    /*public static SwitchCompat anonymitySwitch;
    public static AppCompatImageButton sendButton;
    public static TextInputEditText comment;
    public static AppCompatButton viewComments;
    public static AppCompatImageButton menu;
    public static PopupMenu popupMenu;*/
    //This function creates a layout Params for LinearLayout
    //it takes in a margin and return a layout Param, together with the margins applied
    //margins applied left, right, up, down
    public static LinearLayout.LayoutParams getLayoutParams(int margins) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(margins, margins, margins, margins);
        return params;
    }

    //Function that populates the navigation head with username and user type
    //of the currently logged in user
    public static void populateNavHead(AppCompatTextView headerUsername, AppCompatTextView headerUserType) {
        String name;
        //check which user logged in and choose correct name to be displayed
        //if it's a student who logged in, display their name and surname
        //if it's an src member who logged in, display their position
        if (UserManager.getLoggedInUserType() == UserUtils.STUDENT) {
            name = UserManager.getUserNameSurname();
        } else {
            name = UserManager.getCurrentlyLoggedInUsername();
        }

        headerUsername.setText(name);
        headerUserType.setText(UserManager.getLoggedInUserTypeName(headerUsername.getContext()));
    }

    //logs the user out and clears all the data in shared preferences
    public static void logOut(Context context) {
        Intent intent = new Intent(context, LogInActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
        UserManager.userLoggedOut(context);
    }

    //When used, forces any bottom sheet to fully expand
    public static void forceBottomSheetToFullyExpand(DialogInterface dialog1) {
        BottomSheetDialog d = (BottomSheetDialog) dialog1;
        FrameLayout bottomSheet = d.findViewById(android.support.design.R.id.design_bottom_sheet);
        try {
            BottomSheetBehavior.from(bottomSheet).setState(BottomSheetBehavior.STATE_EXPANDED);
        } catch (Exception ignored) {

        }
    }

    //this function gets the current date and time and provides a string array which has date and time
    public static String[] getDateTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy-HH:mm", Locale.getDefault());
        String sDateTime = simpleDateFormat.format(new Date());
        String[] dateTimeList = sDateTime.split("-");
        String date = dateTimeList[0].trim();
        String time = dateTimeList[1].trim();
        return new String[]{date, time};
    }

    //this function populates any given linear layout with src activities
    public static void populateWithSrcActivities(LinearLayout holder, JSONArray activities,
                                                 boolean mine) {
        holder.removeAllViews();

        try {
            for (int i = 0; i < activities.length(); i++) {
                //get JsonObject from jsonArray which contains data about each and every activity
                JSONObject activity = (JSONObject) activities.get(i);

                //get activity id
                int activityId = activity.getInt(ServerUtils.ACTIVITY_ID);

                //get activity title
                String title = activity.getString(ServerUtils.ACTIVITY_TITLE);

                //get activity desc
                String desc = activity.getString(ServerUtils.ACTIVITY_DESC).replace("\\n", "\n");

                //inflate a card view that will display all the date
                View activityItemView = View.inflate(holder.getContext(), R.layout.item_src_activity_card, null);

                //display title of the activity
                ((AppCompatTextView) activityItemView.findViewById(R.id.src_activity_card_title))
                        .setText(title);

                //display the src member who posted the activity
                ((AppCompatTextView) activityItemView.findViewById(R.id.src_activity_card_posted_by))
                        .setText(String.format("posted by: %s  ", activity.getString(ServerUtils.SRC_USERNAME)));

                //display the date and time of when the activity was posted
                ((AppCompatTextView) activityItemView.findViewById(R.id.src_activity_card_date_time))
                        .setText(String.format("on %s at %s  ", activity.getString(ServerUtils.ACTIVITY_DATE),
                                activity.getString(ServerUtils.ACTIVITY_TIME)));

                //display the activity description
                ((AppCompatTextView) activityItemView.findViewById(R.id.src_activity_card_activity))
                        .setText(desc);

                //TODO: work here
                /*AppCompatImageView likeIcon = activityItemView.findViewById(R.id.appCompatImageView7);
                AppCompatImageView disLikeIcon = activityItemView.findViewById(R.id.appCompatImageView8);

                AppCompatTextView numLikes = activityItemView.findViewById(R.id.src_activity_card_num_likes);
                AppCompatTextView numDisLikes = activityItemView.findViewById(R.id.src_activity_card_num_dislike);
                AppCompatTextView numComments = activityItemView.findViewById(R.id.src_activity_card_num_comments);
                //TODO: should get int from json which says user liked or dislike, 0 like, 1 dislike, else nothing
                //////////////////////////////////////////////////////////////////////////////////////////////////////////some magic here
                String likes = activity.getString("NUM_LIKES");
                String disLikes = activity.getString("NUM_DISLIKES");
                String comments = activity.getString("NUM_COMMENTS");

                numLikes.setText(likes);
                numDisLikes.setText(disLikes);
                numComments.setText(comments);
                switch (activity.getInt("LIKE_STATUS")){
                    case 0:
                        likeIcon.setImageResource(R.drawable.icon_like_pressed);
                        break;

                    case 1:
                        disLikeIcon.setImageResource(R.drawable.icon_dis_like_pressed);
                        break;
                }

                ConstraintLayout likeButton = activityItemView.findViewById(R.id.like_button);
                ConstraintLayout dislikeButton = activityItemView.findViewById(R.id.dislike_button);
                String userId = UserManager.getCurrentlyLoggedInUsername();

                likeButton.setOnClickListener(v -> {
                    handleLikeOrDislike(likeIcon, disLikeIcon, activityId, userId, ServerUtils.LIKE_ACTION);
                });

                dislikeButton.setOnClickListener(v -> {
                    handleLikeOrDislike(likeIcon, disLikeIcon, activityId, userId, ServerUtils.DISLIKE_ACTION);
                });*/
                //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                //create a reference to the anonymous comment switch
                SwitchCompat anonymitySwitch = activityItemView.findViewById(R.id.anonymity_switch);

                //declare a variable to save the anonymity switch
                final int[] anonymityTracker = {ServerUtils.ANONYMOUS_COMMENT_OFF};

                //this allows the app to listen for anonymous comment states (on and off) and displays
                //a notification at the change of each state
                anonymitySwitch.setOnCheckedChangeListener((buttonView, isChecked) ->
                        handleAnonymity(isChecked, anonymityTracker, holder.getContext()));

                //create a reference to the TextInputEditText used to input comments
                TextInputEditText comment = activityItemView.findViewById(R.id.input_comment);

                //set an onClickListener to send comment button, when pressed it will send the comment
                //to the database
                AppCompatImageButton sendButton = activityItemView.findViewById(R.id.send_comment);
                sendButton.setOnClickListener(v -> sendComment(comment, anonymityTracker, activityId));

                //set on click listener to view comments to show a bottom sheet which contains comments
                AppCompatButton viewComments = activityItemView.findViewById(R.id.view_comments);
                viewComments.setOnClickListener(v -> openComments(activityId, title, desc, v.getContext()));

                //create reference to the menu button
                AppCompatImageButton menu = activityItemView.findViewById(R.id.src_activity_menu);
                if (mine) {
                    PopupMenu popupMenu = new PopupMenu(menu.getContext(), menu);
                    popupMenu.inflate(R.menu.src_activity_menu);
                    popupMenu.setOnMenuItemClickListener(item -> {
                        switch (item.getItemId()) {
                            case R.id.src_activity_menu_update:
                                updateActivity(menu.getContext(), activityId, title, desc);
                                break;

                            case R.id.src_activity_menu_delete:
                                deleteActivity(activityId, activityItemView, holder);
                                break;
                        }
                        return false;
                    });
                    menu.setOnClickListener(v -> popupMenu.show());
                } else {
                    menu.setVisibility(View.GONE);
                }
                //add card to the linear layout that holds all of the activity cards
                holder.addView(activityItemView, UiManager.getLayoutParams(15));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void deleteActivity(int activityId, View activityItemView, LinearLayout holder) {
        ContentValues cv = new ContentValues();
        cv.put(ServerUtils.ACTION, ServerUtils.DELETE_ACTIVITY);
        cv.put(ServerUtils.ACTIVITY_ID, activityId);
        deleteItem(cv, holder, activityItemView, ServerUtils.SRC_MEMBER_LINK);
    }

    public static void openComments(int activityId, String title, String desc, Context context) {
        //ViewCommentsBottomSheet viewCommentsBottomSheet = new ViewCommentsBottomSheet();
        //passes the activity id, title, desc to the bottom sheet
        //the bottom sheet will then display
        ViewCommentsActivity.setActivityId(activityId);
        ViewCommentsActivity.setActivityTitle(title);
        ViewCommentsActivity.setActivityDesc(desc);
        context.startActivity(new Intent(context, ViewCommentsActivity.class));
        //viewCommentsBottomSheet.show(fragmentManager, "");
    }

    public static void sendComment(TextInputEditText comment, int[] anonymityTracker, int activityId) {
        //retrieves what the user entered
        String sComment = Objects.requireNonNull(comment.getText()).toString().trim();
        //check if the user actually entered something
        //if he or she didn't enter anything, show error, else post the comment to the database
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

    public static void handleAnonymity(boolean isChecked, int[] anonymityTracker, Context c) {
        anonymityTracker[0] = isChecked ? ServerUtils.ANONYMOUS_COMMENT_ON : ServerUtils.ANONYMOUS_COMMENT_OFF;
        String message;
        if (isChecked) message = "Anonymous comment on";
        else message = "Anonymous comment off";
        Toast.makeText(c, message, Toast.LENGTH_SHORT).show();
    }

    static void updateActivity(Context context, int activityId, String title, String desc) {
        context.startActivity(new Intent(context, SrcPostActivityActivity.class)
                .putExtra(ServerUtils.ACTIVITY_ID, activityId)
                .putExtra(ServerUtils.ACTIVITY_TITLE, title)
                .putExtra(ServerUtils.ACTIVITY_DESC, desc));
    }

    /*private static void handleLikeOrDislike(AppCompatImageView likeIcon, AppCompatImageView disLikeIcon, int activityId, String userId, int action) {
        ContentValues cv = new ContentValues();
        //TODO: add params
        new ServerCommunicator(cv) {
            @Override
            protected void onPreExecute() {

            }

            @Override
            protected void onPostExecute(String output) {
                if ( output.equals(ServerUtils.SUCCESS)){
                   likeIcon.setImageResource(R.drawable.icon_like_unpressed);
                   disLikeIcon.setImageResource(R.drawable.icon_dis_like_unpressed);
                   if (action == ServerUtils.LIKE_ACTION){
                       likeIcon.setImageResource(R.drawable.icon_like_pressed);
                   }
                   else{
                       disLikeIcon.setImageResource(R.drawable.icon_dis_like_pressed);
                   }
                }
                else{
                    Toast.makeText(likeIcon.getContext(), "Action Failed", Toast.LENGTH_SHORT).show();
                }
            }
        }.execute(); //TODO: add link
    }*/
    //this function deletes item from database and removes from linear layout
    public static void deleteItem(ContentValues cv, LinearLayout holder, View view, String link) {
        new AlertDialog.Builder(holder.getContext())
                .setTitle("Delete")
                .setMessage("Are you sure you want to delete this item?")
                .setPositiveButton("Yes", (dialog, which) -> {
                    performDeleteAction(cv, holder, view, link);

                })
                .setNegativeButton("No", (dialog, which) -> dialog.dismiss())
                .create().show();
    }

    public static void performDeleteAction(ContentValues cv, LinearLayout holder, View view, String link) {
        new ServerCommunicator(cv) {
            @Override
            protected void onPreExecute() {
                Toast.makeText(view.getContext(), "Deleting item...", Toast.LENGTH_SHORT).show();
            }

            @Override
            protected void onPostExecute(String output) {
                handleDeleteItemFeedback(output, holder, view);
            }
        }.execute(link);
    }

    public static void handleDeleteItemFeedback(String output, LinearLayout holder, View view) {
        if (output.equals(ServerUtils.SUCCESS)) {
            holder.removeView(view);
            Toast.makeText(view.getContext(), "Item deleted", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(view.getContext(), "Failed to delete item", Toast.LENGTH_SHORT).show();
        }
    }

    // this function populates any given linear layout with src polls
    public static void populateWithPolls(LinearLayout holder, JSONArray polls) {
        try {
            for (int i = 0; i < polls.length(); i++) {
                JSONObject poll = (JSONObject) polls.get(i);
                View pollItem = View.inflate(holder.getContext(), R.layout.item_poll_card, null);

                ((AppCompatTextView) pollItem.findViewById(R.id.poll_title))
                        .setText(poll.getString(ServerUtils.POLL_TITLE));
                ((AppCompatTextView) pollItem.findViewById(R.id.poll_poster))
                        .setText(String.format("posted by: %s",
                                poll.getString(ServerUtils.SRC_USERNAME)));
                ((AppCompatTextView) pollItem.findViewById(R.id.poll_date_time))
                        .setText(String.format("on %s at %s",
                                poll.getString(ServerUtils.POLL_DATE),
                                poll.getString(ServerUtils.POLL_TIME)));
                ((AppCompatTextView) pollItem.findViewById(R.id.poll_desc))
                        .setText(poll.getString(ServerUtils.POLL_DESC).replace("\\n", "\n"));

                String[] pollChoices = poll.getString(ServerUtils.POLL_CHOICE).split("~");
                StringBuilder builder = new StringBuilder();

                for (int k = 0; k < pollChoices.length; k++) {
                    builder.append(String.format(Locale.getDefault(), "%s: %d", pollChoices[k], 0//for now
                    ));
                    if (k + 1 != pollChoices.length) builder.append("\n\n");
                }

                ((AppCompatTextView) pollItem.findViewById(R.id.poll_choice_stats))
                        .setText(builder.toString());

                pollItem.findViewById(R.id.poll_vote).setOnClickListener(v -> {
                    openPollVoteBottomSheet(poll, pollChoices, v.getContext());


                });

                holder.addView(pollItem, UiManager.getLayoutParams(15));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public static void openPollVoteBottomSheet(JSONObject poll, String[] pollChoices, Context c) {
        try {
            PollVoteActivity.setPollId(poll.getInt(ServerUtils.POLL_ID));
            PollVoteActivity.setTitle(poll.getString(ServerUtils.POLL_TITLE));
            PollVoteActivity.setDesc(poll.getString(ServerUtils.POLL_DESC).replace("\\n", "\n"));
            PollVoteActivity.setPollType(poll.getInt(ServerUtils.POLL_TYPE));
            PollVoteActivity.setPollChoices(pollChoices);
            c.startActivity(new Intent(c, PollVoteActivity.class));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
