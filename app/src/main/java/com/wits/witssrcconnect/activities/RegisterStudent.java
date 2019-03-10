package com.wits.witssrcconnect.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.text.TextUtils;
import android.widget.Toast;

import com.wits.witssrcconnect.R;

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
                Toast.makeText(RegisterStudent.this, "Register", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
