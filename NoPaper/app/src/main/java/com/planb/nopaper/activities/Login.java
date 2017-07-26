package com.planb.nopaper.activities;

import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.planb.nopaper.R;
import com.planb.nopaper.activities.base.BaseActivity;
import com.planb.nopaper.support.android_view.SnackbarManager;

import java.util.HashMap;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by dsm2016 on 2017-07-26.
 */

public class Login extends BaseActivity {
    private EditText id;
    private EditText pw;
    private Button signinBtn;
    private TextView jumpToSignupView;
    private AQuery aq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        id = (EditText) findViewById(R.id.id);
        pw = (EditText) findViewById(R.id.pw);
        signinBtn = (Button) findViewById(R.id.signinBtn);
        jumpToSignupView = (TextView) findViewById(R.id.jumpToSignupView);
        aq = new AQuery(getApplicationContext());

        signinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(id.getText().toString().isEmpty() || pw.getText().toString().isEmpty()) {
                    SnackbarManager.make(v, "아이디 또는 비밀번호를 확인하세요").show();
                } else {
                    ConnectivityManager cm = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
                    NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
                    System.out.println(activeNetwork);

                    HashMap<String, String> params = new HashMap<String, String>();
                    params.put("id", id.getText().toString());
                    params.put("pw", pw.getText().toString());

                    aq.ajax("http://52.79.134.200:3434/login", params, String.class, new AjaxCallback<String>() {
                        @Override
                        public void callback(String url, String response, AjaxStatus status) {
                            System.out.println(status.getCode());
                            if(status.getCode() == 201) {
                                // 학생
                                SweetAlertDialog dialog = new SweetAlertDialog(Login.this, SweetAlertDialog.SUCCESS_TYPE)
                                        .setTitleText("로그인 성공")
                                        .setContentText("환영합니다, " + response + "님!");
                                dialog.getProgressHelper().setBarColor(Color.parseColor("#0b8c2b"));
                                dialog.show();
                            } else if(status.getCode() == 200) {
                                // 선생님
                                SweetAlertDialog dialog = new SweetAlertDialog(Login.this, SweetAlertDialog.SUCCESS_TYPE)
                                        .setTitleText("로그인 성공")
                                        .setContentText("안녕하세요, 선생님!");
                                dialog.getProgressHelper().setBarColor(Color.parseColor("#0b8c2b"));
                                dialog.show();
                            } else {
                                SweetAlertDialog dialog = new SweetAlertDialog(Login.this, SweetAlertDialog.WARNING_TYPE)
                                        .setTitleText("로그인 실패")
                                        .setContentText("계정 정보를 확인하세요");
                                dialog.show();
                            }
                        }
                    });
                }
            }
        });
    }
}
