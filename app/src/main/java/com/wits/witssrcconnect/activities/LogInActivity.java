package com.wits.witssrcconnect.activities;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.design.widget.TextInputEditText;
import android.text.TextUtils;
import android.transition.ChangeBounds;
import android.transition.TransitionManager;
import android.view.animation.AnticipateInterpolator;

import com.wits.witssrcconnect.R;
import com.wits.witssrcconnect.managers.UserManager;
import com.wits.witssrcconnect.utils.Ldap;
import com.wits.witssrcconnect.utils.Person;
import com.wits.witssrcconnect.utils.ServerUtils;
import com.wits.witssrcconnect.utils.UserUtils;

import java.util.Objects;

public class LogInActivity extends Activity {

    private ConstraintLayout cc1;
    private ConstraintLayout cc2;
    private int user = UserUtils.DEFAULT_USER;
    TextInputEditText username;
    TextInputEditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        UserManager.initUserManager(this);

        //check if user has already logged in
        if (UserManager.getCurrentlyLoggedInStatus()){
            //open next activity based on which user is already logged in
            Class nextClass = UserManager.getLoggedInUserType() == UserUtils.STUDENT ?
                    StudentActivity.class : SrcMemberActivity.class;
            startActivity(new Intent(this, nextClass));
            finish();
            return;
        }

        setContentView(R.layout.activity_log_in_splash);
        cc1 = findViewById(R.id.cc1);
        new Handler().postDelayed(this::showAnimation, 3000);
    }

    @Override
    public void onBackPressed(){
        if (user == UserUtils.DEFAULT_USER) super.onBackPressed();
        else {
            user = UserUtils.DEFAULT_USER;
            revertAnimation();
        }
    }

    private void showAnimation() {
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(this, R.layout.activity_log_in_user_selector);

        ChangeBounds transition = new ChangeBounds();
        transition.setInterpolator(new AnticipateInterpolator(1.0f));
        transition.setDuration(1200);

        TransitionManager.beginDelayedTransition(cc1, transition);
        constraintSet.applyTo(cc1);

        new Handler().postDelayed(this::initUserSelector, 1200);
    }

    private void animateView(int id){
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(this, id);

        ChangeBounds transition = new ChangeBounds();
        transition.setInterpolator(new AnticipateInterpolator(1.0f));
        transition.setDuration(1200);

        TransitionManager.beginDelayedTransition(cc2, transition);
        constraintSet.applyTo(cc2);
    }

    private void resetInputs(){
        username.setText("");
        password.setText("");
        username.clearFocus();
        password.clearFocus();
    }

    private void initUserSelector() {
        setContentView(R.layout.activity_log_in_user_selector);
        cc2 = findViewById(R.id.cc2);

        username = findViewById(R.id.username);
        password = findViewById(R.id.pass);

        findViewById(R.id.appCompatImageView2).setOnClickListener(v -> {
            user = UserUtils.STUDENT;
            resetInputs();
            animateView(R.layout.activity_log_in_student);
        });

        findViewById(R.id.member_icon_holder).setOnClickListener(v -> {
            user = UserUtils.SRC_MEMBER;
            resetInputs();
            animateView(R.layout.activity_log_in_src_member);
        });

        findViewById(R.id.log_in).setOnClickListener(v -> {
            String sUsername = Objects.requireNonNull(username.getText()).toString().trim().toLowerCase();
            String sPassword = Objects.requireNonNull(password.getText()).toString().trim();

            boolean allIsOkay = true;

            if (TextUtils.isEmpty(sUsername)){
                username.setError("Enter username");
                allIsOkay = false;
            }

            if (TextUtils.isEmpty(sPassword)){
                password.setError("Enter password");
                allIsOkay = false;
            }

            if (allIsOkay){
                if (user == UserUtils.STUDENT){
                    //Ldap ldap = Ldap.connect("student", sUsername, sPassword);
                    //Person u = ldap.getUser(sUsername);
                    //UserManager.setUserLoggedIn(user, sUsername);
                    //startActivity(new Intent(this, StudentActivity.class));
                    //finish();
                    //link = ServerUtils.STUDENT_LINK;
                    //cv.put(ServerUtils.STUDENT_USERNAME, sUsername);
                    //cv.put(ServerUtils.STUDENT_PASSWORD, sPassword);
                    //STILL NOT WORKING
                    //Ldap ldap = Ldap.connect("student", sUsername, sPassword);
                    //Person person = ldap.getUser(sUsername);
                    //if (person == null){
                       //Toast.makeText(this, "Failed to login", Toast.LENGTH_SHORT).show();
                    //}
                    //else{
                        //Toast.makeText(this, "works", Toast.LENGTH_SHORT).show();
                    //}
                    //ldap.close();
                }
                else {
                    ContentValues cv = new ContentValues();
                    cv.put(ServerUtils.ACTION, ServerUtils.LOG_IN);
                    cv.put(ServerUtils.SRC_USERNAME, sUsername);
                    cv.put(ServerUtils.SRC_PASSWORD, sPassword);
                    UserManager.logIn(user, cv, ServerUtils.SRC_MEMBER_LINK, LogInActivity.this);
                }
            }
        });
    }



    private void revertAnimation() {
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(this, R.layout.activity_log_in_user_selector);

        ChangeBounds transition = new ChangeBounds();
        transition.setInterpolator(new AnticipateInterpolator(1.0f));
        transition.setDuration(1200);

        TransitionManager.beginDelayedTransition(cc2, transition);
        constraintSet.applyTo(cc2);
    }

}
