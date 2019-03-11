package com.wits.witssrcconnect.activities;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.wits.witssrcconnect.R;
import com.wits.witssrcconnect.services.ServerCommunicator;
import com.wits.witssrcconnect.utils.ServerUtils;

public class RegisterStudent extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_reg_student);

        TextInputEditText studNum = findViewById(R.id.stud_num);
        TextInputEditText name = findViewById(R.id.name);
        TextInputEditText last_name = findViewById(R.id.last_name);
        TextInputEditText password = findViewById(R.id.password);
        TextInputEditText conf_pass = findViewById(R.id.conf_pass);

        findViewById(R.id.reg_student).setOnClickListener(v -> {
            String stud_num = studNum.getText().toString().trim();
            String _name = name.getText().toString().trim();
            String _last_name = last_name.getText().toString().trim();
            String _password = password.getText().toString().trim();
            String _conf_pass = conf_pass.getText().toString().trim();

            boolean everythingOkay = true;

            if (TextUtils.isEmpty(stud_num)){
                studNum.setError("input required");
                everythingOkay = false;
            }

            if (TextUtils.isEmpty(_name)){
                name.setError("input required");
                everythingOkay = false;
            }

            if (TextUtils.isEmpty(_last_name)){
                last_name.setError("input required");
                everythingOkay = false;
            }

            if (TextUtils.isEmpty(_password)){
                password.setError("input required");
                everythingOkay = false;
            }

            if (TextUtils.isEmpty(_conf_pass)){
                conf_pass.setError("input required");
                everythingOkay = false;
            }

            if (!TextUtils.isEmpty(_password) && !TextUtils.isEmpty(_conf_pass) && !_password.equals(_conf_pass)){
                Toast.makeText(RegisterStudent.this, "Passwords mismatch", Toast.LENGTH_SHORT).show();
                everythingOkay = false;
            }

            if (everythingOkay){
                //Toast.makeText(RegisterStudent.this, "Register", Toast.LENGTH_SHORT).show();
                ContentValues cv = new ContentValues();
                cv.put(ServerUtils.ACTION, ServerUtils.CREATE);
                cv.put(ServerUtils.STUDENT_USERNAME, stud_num);
                cv.put(ServerUtils.STUDENT_FIRSTNAME, _name);
                cv.put(ServerUtils.STUDENT_LASTNAME, _last_name);
                cv.put(ServerUtils.STUDENT_PASSWORD, _password);
                register(cv, this);
            }
        });
    }

    private static void register(ContentValues cv, Context context){
        new ServerCommunicator(ServerUtils.STUDENT_LINK, cv) {
            @Override
            protected void onPreExecute() {

            }

            @Override
            protected void onPostExecute(String output) {
                Log.d("SERVER_COMMUN", output);
                if (output != null && output.equals("1")){
                    Toast.makeText(context, "Student Registration Success", Toast.LENGTH_SHORT).show();
                    ((Activity)context).finish();
                }
                else{
                    Toast.makeText(context, "Student Registration failed", Toast.LENGTH_SHORT).show();
                }
            }
        }.execute();
    }
}
