package com.wits.witssrcconnect.activities.src_member;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.wits.witssrcconnect.R;
import com.wits.witssrcconnect.fragments.SrcMemberActivitiesFragment;
import com.wits.witssrcconnect.fragments.SrcMemberFragment;
import com.wits.witssrcconnect.managers.UiManager;

public class SrcMemberActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle bundle){
        super.onCreate(bundle);

        setContentView(R.layout.activity_src_member);

        toolbar = findViewById(R.id.toolbar_src);

        //load the default fragment
        loadFragment(new SrcMemberFragment(), getString(R.string.home));

        setSupportActionBar(toolbar);



        drawerLayout = findViewById(R.id.drawer_layout_src);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view_src);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.src_nav_home);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){

            case R.id.src_nav_activity_timeline:
                loadFragment(new SrcMemberActivitiesFragment(), getString(R.string.src_activity_time_line));
                break;

            case R.id.src_nav_log_out:
                UiManager.logOut(this);
                break;

        }
        return true;
    }

    private void loadFragment(Fragment fragment, String title){
        getSupportFragmentManager().beginTransaction().replace(R.id.parentLayout_src, fragment).commit();
        toolbar.setTitle(title);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

}
