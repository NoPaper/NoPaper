package com.planb.nopaper.activities;

import android.content.Context;
import android.content.Intent;
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
import com.planb.nopaper.support.account.AccountManager;
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

                    HashMap<String, String> params = new HashMap<>();
                    params.put("id", id.getText().toString());
                    params.put("pw", pw.getText().toString());

                    aq.ajax("http://52.79.134.200:3434/login", params, String.class, new AjaxCallback<String>() {
                        @Override
                        public void callback(String url, String response, AjaxStatus status) {
                            SweetAlertDialog successDialog = new SweetAlertDialog(Login.this, SweetAlertDialog.SUCCESS_TYPE)
                                    .setTitleText("로그인 성공");
                            successDialog.getProgressHelper().setBarColor(Color.parseColor("#0b8c2b"));


                            if(status.getCode() == 200) {
                                // 학생
                                successDialog.setContentText("환영합니다, " + response + "님!");
                                successDialog.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                                        startActivity(new Intent(getApplicationContext(), MainActivity_Student.class));
                                    }
                                });
                                successDialog.show();
                                AccountManager.setId(getApplicationContext(), id.getText().toString());
                            } else if(status.getCode() == 201) {
                                // 선생님
                                successDialog.setContentText("안녕하세요, 선생님!");
                                successDialog.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                                        startActivity(new Intent(getApplicationContext(), MainActivity_Teacher.class));
                                    }
                                });
                                successDialog.show();
                                AccountManager.setId(getApplicationContext(), id.getText().toString());
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

        jumpToSignupView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SignupGuide.class));
            }
        });
    }
}
