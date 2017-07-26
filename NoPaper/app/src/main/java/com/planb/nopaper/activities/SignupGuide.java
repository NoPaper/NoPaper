package com.planb.nopaper.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.planb.nopaper.R;
import com.planb.nopaper.activities.base.BaseActivity;

/**
 * Created by dsm2016 on 2017-07-26.
 */

public class SignupGuide extends BaseActivity {
    private ImageButton backBtn;
    private ImageButton studentBtn;
    private ImageButton teacherBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_guide);

        backBtn = (ImageButton) findViewById(R.id.backBtn);
        studentBtn = (ImageButton) findViewById(R.id.studentBtn);
        teacherBtn = (ImageButton) findViewById(R.id.teacherBtn);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Login.class));
            }
        });

        studentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Signup_Student.class));
            }
        });

        teacherBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Signup_Teacher.class));
            }
        });
    }
}
