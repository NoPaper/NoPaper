package com.planb.nopaper.activities;

import android.os.Bundle;
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
    }
}