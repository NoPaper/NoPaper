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

public class Signup_Student extends BaseActivity {
    private ImageButton backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_student);

        backBtn = (ImageButton) findViewById(R.id.backBtn);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SignupGuide.class));
            }
        });
    }
}
