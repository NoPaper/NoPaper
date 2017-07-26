package com.planb.nopaper.activities.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.planb.nopaper.R;
import com.planb.nopaper.activities.Login;
import com.planb.nopaper.activities.MainActivity_Student;
import com.planb.nopaper.activities.MainActivity_Teacher;
import com.planb.nopaper.activities.base.BaseActivity;
import com.planb.nopaper.support.account.AccountManager;

import java.util.HashMap;

/**
 * Created by dsm2016 on 2017-07-26.
 */

public class Splash extends BaseActivity {
    private AQuery aq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        aq = new AQuery(getApplicationContext());
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                judge();
            }
        }, 2250);
    }

    private void judge() {
        if(AccountManager.isLogined(getApplicationContext())) {
            HashMap<String, String> params = new HashMap<>();
            params.put("id", AccountManager.getId(getApplicationContext()));

            aq.ajax("http://52.79.134.200:3434/judge_user", params, String.class, new AjaxCallback<String>() {
                @Override
                public void callback(String url, String response, AjaxStatus status) {
                    if(status.getCode() == 201) {
                        startActivity(new Intent(getApplicationContext(), MainActivity_Student.class));
                    } else if(status.getCode() == 200) {
                        startActivity(new Intent(getApplicationContext(), MainActivity_Teacher.class));
                    }
                }
            });
        } else {
            startActivity(new Intent(this, Login.class));
        }
    }
}
