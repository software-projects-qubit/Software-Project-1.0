package com.wits.witssrcconnect.activities;


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
import com.wits.witssrcconnect.fragments.HomeFragment;
import com.wits.witssrcconnect.managers.UiManager;

public class StudentActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_student);

        toolbar = findViewById(R.id.toolbar);

        //load the default fragment
        loadFragment(new HomeFragment(), getString(R.string.home));

        setSupportActionBar(toolbar);



        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.student_nav_home);
    }

    private void loadFragment(Fragment fragment, String title){
        getSupportFragmentManager().beginTransaction().replace(R.id.parentLayout, fragment).commit();
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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.student_nav_log_out:
                UiManager.logOut(this);
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
