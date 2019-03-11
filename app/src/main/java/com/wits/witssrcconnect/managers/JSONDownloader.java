package com.wits.witssrcconnect.managers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.wits.witssrcconnect.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

@SuppressLint("StaticFieldLeak")
public class JSONDownloader extends AsyncTask<String, String, JSONObject> {


    private final LinearLayout holder;

    public JSONDownloader(LinearLayout holder){
        this.holder = holder;
    }

    @Override
    protected void onPreExecute() {
    }

    @Override
    protected JSONObject doInBackground(String... strings) {
        // make HTTP request
        try {
            StringBuilder response = new StringBuilder();
            URL url = new URL(strings[0]);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            BufferedReader input = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()),8192);
            String line;
            while ((line = input.readLine()) != null) response.append(line);
            input.close();


            return new JSONObject(response.toString());
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected  void onPostExecute(JSONObject jsonObject){
        Context context = holder.getContext();
        holder.removeAllViews();


        try {
            View card1 = View.inflate(context, R.layout.title_desc_card_item, null);
            View card2 = View.inflate(context, R.layout.title_desc_card_item, null);
            View card3 = View.inflate(context, R.layout.title_desc_card_item, null);

            ((AppCompatTextView) card1.findViewById(R.id.td_card_item_title)).setText("Mission");
            ((AppCompatTextView) card1.findViewById(R.id.td_card_item_desc)).setText(jsonObject.getString("Mission"));
            ((AppCompatTextView) card2.findViewById(R.id.td_card_item_title)).setText("Vision");
            ((AppCompatTextView) card2.findViewById(R.id.td_card_item_desc)).setText(
                    //jsonObject.getString("Vision")
                    "The vision of the src is to be at the forefront of the communication between the students and the institution"
            );
            ((AppCompatTextView) card3.findViewById(R.id.td_card_item_title)).setText("Contact Us");
            ((AppCompatTextView) card3.findViewById(R.id.td_card_item_desc)).setText(jsonObject.getString("ContactUs"));

            LinearLayout.LayoutParams params = UiManager.getLayoutParams(15);
            holder.addView(card1, params);
            holder.addView(card2, params);
            holder.addView(card3, params);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
