package com.planb.nopaper.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.planb.nopaper.R;

/**
 * Created by dsm2016 on 2017-07-27.
 */

public class Make extends Dialog {
    public Make(Context context) {
        super(context);
    }

    private EditText title;
    private ImageButton fileBtn;
    private EditText target;
    private Button submitBtn;
    private Button cancelBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_make);

        title = (EditText) findViewById(R.id.title);
        fileBtn = (ImageButton) findViewById(R.id.fileBtn);
        target = (EditText) findViewById(R.id.target);
        submitBtn = (Button) findViewById(R.id.submitBtn);
        cancelBtn = (Button) findViewById(R.id.cancelBtn);

        fileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
