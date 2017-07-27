package com.planb.nopaper.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.RemoteException;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.planb.nopaper.R;
import com.planb.nopaper.activities.Login;
import com.planb.nopaper.support.account.AccountManager;

/**
 * Created by dsm2016 on 2017-07-27.
 */

public class Make extends Dialog {
    private Context context;

    public Make(Context context) {
        super(context);
        this.context = context;
    }

    private EditText title;
    private ImageButton fileBtn;
    private ImageButton imageBtn;
    private EditText target;
    private Button submitBtn;
    private Button cancelBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_make);

        title = (EditText) findViewById(R.id.title);
        fileBtn = (ImageButton) findViewById(R.id.fileBtn);
        imageBtn = (ImageButton) findViewById(R.id.imageBtn);
        target = (EditText) findViewById(R.id.target);
        submitBtn = (Button) findViewById(R.id.submitBtn);
        cancelBtn = (Button) findViewById(R.id.cancelBtn);

        fileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AccountManager.setId(context, null);
                context.startActivity(new Intent(context, Login.class));
            }
        });

        imageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AccountManager.setId(context, null);
                context.startActivity(new Intent(context, Login.class));
            }
        });

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }
}
