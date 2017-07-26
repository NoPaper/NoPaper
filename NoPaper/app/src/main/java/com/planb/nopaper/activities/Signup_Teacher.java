package com.planb.nopaper.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

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

public class Signup_Teacher extends BaseActivity {
    private ImageButton backBtn;
    private EditText id;
    private EditText pw;
    private EditText secret;
    private Button signupBtn;
    private AQuery aq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_teacher);

        backBtn = (ImageButton) findViewById(R.id.backBtn);
        id = (EditText) findViewById(R.id.id);
        pw = (EditText) findViewById(R.id.pw);
        secret = (EditText) findViewById(R.id.secret);
        signupBtn = (Button) findViewById(R.id.signupBtn);
        aq = new AQuery(getApplicationContext());

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SignupGuide.class));
            }
        });

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(id.getText().toString().isEmpty() || pw.getText().toString().isEmpty() || secret.getText().toString().isEmpty()) {
                    SnackbarManager.make(v, "입력된 정보를 확인하세요.").show();
                } else {
                    HashMap<String, String> params = new HashMap<>();
                    params.put("id", id.getText().toString());
                    params.put("pw", pw.getText().toString());
                    params.put("secret", secret.getText().toString());

                    aq.ajax("http://52.79.134.200:3434/signup/teacher", params, String.class, new AjaxCallback<String>() {
                        @Override
                        public void callback(String url, String response, AjaxStatus status) {
                            System.out.println(status.getCode());

                            if(status.getCode() == 200) {
                                SweetAlertDialog dialog = new SweetAlertDialog(Signup_Teacher.this, SweetAlertDialog.WARNING_TYPE)
                                        .setTitleText("회원가입 실패")
                                        .setContentText("비밀 키가 올바르지 않습니다.");
                                dialog.show();
                            } else if(status.getCode() == 201) {
                                new SweetAlertDialog(Signup_Teacher.this, SweetAlertDialog.SUCCESS_TYPE)
                                        .setTitleText("회원가입 성공!")
                                        .setContentText("로그인 화면으로 이동합니다.")
                                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                            @Override
                                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                                startActivity(new Intent(getApplicationContext(), Login.class));
                                            }
                                        }).show();
                            } else {
                                SweetAlertDialog dialog = new SweetAlertDialog(Signup_Teacher.this, SweetAlertDialog.WARNING_TYPE)
                                        .setTitleText("회원가입 실패")
                                        .setContentText("이미 사용 중인 아이디입니다.");
                                dialog.show();
                            }
                        }
                    });
                }
            }
        });
    }
}
