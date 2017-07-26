package com.planb.nopaper.activities.base;

import android.app.Activity;

/**
 * Created by dsm2016 on 2017-07-26.
 */

public class BaseActivity extends Activity {
    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
