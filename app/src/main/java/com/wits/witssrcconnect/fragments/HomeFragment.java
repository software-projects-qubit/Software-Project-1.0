package com.wits.witssrcconnect.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.wits.witssrcconnect.R;
import com.wits.witssrcconnect.managers.JSONDownloader;
import com.wits.witssrcconnect.managers.UiManager;
import com.wits.witssrcconnect.utils.ServerUtils;

import org.json.JSONException;
import org.json.JSONObject;

@SuppressLint("StaticFieldLeak")
public class HomeFragment extends Fragment {

    private View v;
    private static SwipeRefreshLayout refresh = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_home, container, false);
        refresh = v.findViewById(R.id.home_refresh_layout);
        refresh.setOnRefreshListener(() -> populateHomePage(v.findViewById(R.id.home_page_items_holder), refresh));
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        populateHomePage(v.findViewById(R.id.home_page_items_holder), refresh);
    }

    //connect to server and retrieve data from Homepage.json
    public static void populateHomePage(LinearLayout holder, SwipeRefreshLayout refresh) {
        new JSONDownloader() {
            @Override
            protected void onPostExecute(JSONObject jsonObject) {
                addViewsToLayout(jsonObject, holder, refresh);
            }
        }.execute(ServerUtils.HOME_PAGE_JSON_LINK);
    }

    public static void addViewsToLayout(JSONObject jsonObject, LinearLayout holder, SwipeRefreshLayout sRefresh) {
        if (sRefresh != null) sRefresh.setRefreshing(false);

        Context context = holder.getContext();
        holder.removeAllViews();

        //the server is gonna return a json object which contains mission, vision, contact details
        try {
            //the data will be displayed in cardViews, each data on its own cardView
            View card1 = View.inflate(context, R.layout.item_title_desc_card, null);
            View card2 = View.inflate(context, R.layout.item_title_desc_card, null);
            View card3 = View.inflate(context, R.layout.item_title_desc_card, null);
            LinearLayout.LayoutParams params = UiManager.getLayoutParams(15);

            //populate the cardViews with the data
            ((AppCompatTextView) card1.findViewById(R.id.td_card_item_title)).setText(ServerUtils.MISSION);
            ((AppCompatTextView) card1.findViewById(R.id.td_card_item_desc)).setText(jsonObject.getString(ServerUtils.MISSION));
            ((AppCompatTextView) card2.findViewById(R.id.td_card_item_title)).setText(ServerUtils.VISION);
            ((AppCompatTextView) card2.findViewById(R.id.td_card_item_desc)).setText(jsonObject.getString(ServerUtils.VISION));
            ((AppCompatTextView) card3.findViewById(R.id.td_card_item_title)).setText(ServerUtils.CONTACT_US);
            ((AppCompatTextView) card3.findViewById(R.id.td_card_item_desc)).setText(jsonObject.getString(ServerUtils.CONTACT_US));

            //add cardViews to linear layout
            holder.addView(card1, params);
            holder.addView(card2, params);
            holder.addView(card3, params);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
