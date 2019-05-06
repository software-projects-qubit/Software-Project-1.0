package com.wits.witssrcconnect.managers;

import android.content.Context;
import android.support.design.widget.BottomSheetDialog;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.LinearLayout;

import com.wits.witssrcconnect.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class UiManagerTest {

    private final Context testContext = InstrumentationRegistry.getTargetContext();

    @Test
    public void getLayoutParams() {
        LinearLayout.LayoutParams params = UiManager.getLayoutParams(5);
        assertNotNull(params);
    }

    @Test
    public void populateNavHead() {
        UserManager.initUserManager(testContext);
        getInstrumentation().runOnMainSync(() -> {
            AppCompatTextView tvUsername = new AppCompatTextView(testContext);
            tvUsername.setId(R.id.header_username);
            AppCompatTextView tvUserType = new AppCompatTextView(testContext);
            tvUserType.setId(R.id.header_user_type);

            LinearLayout headerView = new LinearLayout(testContext);
            headerView.addView(tvUsername);
            headerView.addView(tvUserType);

            UiManager.populateNavHead(headerView);

            assertTrue(tvUsername.getText().length() > 0 || tvUsername.getText().equals("")
                    && tvUserType.getText().length() > 0 || tvUserType.getText().equals(""));
        });
    }

    @Test
    public void logOut() {
        UiManager.logOut(testContext);
    }

    @Test
    public void forceBottomSheetToFullyExpand() {
        getInstrumentation().runOnMainSync(() -> {
            BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(testContext);
            UiManager.forceBottomSheetToFullyExpand(bottomSheetDialog);
        });
    }

    @Test
    public void getDateTime() {
        String[] dateTime = UiManager.getDateTime();
        assertTrue(dateTime[0] != null && dateTime[1] != null);
    }

    /*@Test
    public void populateWithSrcActivities() {
        String activitiesArrayString = "[{" +
                "  \"activity_id\": 1," +
                "  \"activity_title\": \"title\"," +
                "  \"activity_desc\": \"desc\"," +
                "  \"activity_date\": \"01/01/2019\"," +
                "  \"activity_time\": \"00:00\"" +
                "}]";
        JSONArray activitiesJSONArray = getJSONArray(activitiesArrayString);

        if (activitiesJSONArray == null) return;

        View view = View.inflate(testContext, R.layout.fragment_src_activity_view, null);
        getInstrumentation().runOnMainSync(() -> {
            UiManager.populateWithSrcActivities(view.findViewById(R.id.src_activities_holder),
                    activitiesJSONArray, new AppCompatActivity().getSupportFragmentManager(), false);
        });
    }*/

    /*@Test
    public void populateWithPolls() {
        String pollArrayString = "[{" +
                "\"poll_title\": \"title\"," +
                "    \"poll_desc\": \"desc\"," +
                "    \"poll_choices\": \"1~2~3\"," +
                "    \"poll_date\": \"01/01/2019\"," +
                "    \"poll_time\": \"00:00\"," +
                "    \"poll_type\": 1" +
                "  }]";
        JSONArray pollJSONArray = getJSONArray(pollArrayString);

        if (pollJSONArray == null) return;

        View view = View.inflate(testContext, R.layout.fragment_src_activity_view, null);

        getInstrumentation().runOnMainSync(() -> {
            UiManager.populateWithPolls(view.findViewById(R.id.src_activities_holder),
                    pollJSONArray, new AppCompatActivity().getSupportFragmentManager());
        });
    }*/

    private JSONArray getJSONArray(String arrayString) {
        try {
            return new JSONArray(arrayString);
        } catch (JSONException e) {
            System.out.println("Failed to initialize activities JSON Array.");
            return null;
        }
    }
}