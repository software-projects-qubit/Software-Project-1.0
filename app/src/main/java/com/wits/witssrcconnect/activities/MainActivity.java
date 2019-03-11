package com.wits.witssrcconnect.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.wits.witssrcconnect.R;
import com.wits.witssrcconnect.managers.JSONDownloader;
import com.wits.witssrcconnect.utils.ServerUtils;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        LinearLayout homePageItemsHolder = findViewById(R.id.home_page_items_holder);
        new JSONDownloader(homePageItemsHolder).execute(ServerUtils.HOME_PAGE_JSON_LINK);
    }
}
