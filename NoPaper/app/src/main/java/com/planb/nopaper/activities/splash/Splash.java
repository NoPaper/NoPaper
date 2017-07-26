package com.planb.nopaper.activities.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.planb.nopaper.R;
import com.planb.nopaper.activities.MainActivity;
import com.planb.nopaper.activities.base.BaseActivity;
import com.planb.nopaper.support.account.AccountManager;

/**
 * Created by dsm2016 on 2017-07-26.
 */

public class Splash extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                judge();
            }
        }, 2000);
    }

    private void judge() {
        if(AccountManager.isLogined(getApplicationContext())) {
            startActivity(new Intent(this, MainActivity.class));
        }
    }
}
