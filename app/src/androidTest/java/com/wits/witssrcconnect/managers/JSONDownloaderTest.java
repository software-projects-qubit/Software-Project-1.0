package com.wits.witssrcconnect.managers;

import com.wits.witssrcconnect.utils.ServerUtils;

import org.json.JSONObject;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class JSONDownloaderTest {

    @Test
    public void JSONDownloader(){
        new JSONDownloader() {
            @Override
            protected void onPostExecute(JSONObject jsonObject) {
                assertNull(jsonObject);
            }
        }.execute("wrong address");
    }

    @Test
    public void JSONDownloaderCorrectLink(){
        new JSONDownloader(){

            @Override
            protected void onPostExecute(JSONObject jsonObject) {
                assertNotNull(jsonObject);
            }
        }.execute(ServerUtils.HOME_PAGE_JSON_LINK);
    }
}