package com.wits.witssrcconnect.managers;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public abstract class JSONDownloader extends AsyncTask<String, String, JSONObject> {

    @Override
    protected void onPreExecute() {
    }

    @Override
    protected JSONObject doInBackground(String... strings) {
        // Make HTTP request
        try {
            StringBuilder response = new StringBuilder();
            URL url = new URL(strings[0]);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            BufferedReader input = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()), 8192);
            String line;
            while ((line = input.readLine()) != null) {
                response.append(line);
            }
            input.close();


            return new JSONObject(response.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new JSONObject();
    }

    @Override
    protected abstract void onPostExecute(JSONObject jsonObject);
}
