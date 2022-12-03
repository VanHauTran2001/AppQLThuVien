package com.example.quanlythuvien.ui.thu_thu;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quanlythuvien.R;

public class CustomDialog extends Dialog {

    interface FullNameListener {
        void fullNameEntered(String fullName);
    }

    public Context context;
    private EditText editTextFullName;
    private Button buttonOK;
    private Button buttonCancel;

    private CustomDialog.FullNameListener listener;

    public CustomDialog(Context context, CustomDialog.FullNameListener listener) {
        super(context);
        this.context = context;
        this.listener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_change_password);

        this.editTextFullName = (EditText) findViewById(R.id.edtCurrentPass);
        this.buttonOK = (Button) findViewById(R.id.btnOKChange);
        this.buttonCancel  = (Button) findViewById(R.id.btnCancelChange);

        this.buttonOK .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonOKClick();
            }
        });
        this.buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonCancelClick();
            }
        });
    }

    // User click "OK" button.
    private void buttonOKClick()  {
        String fullName = this.editTextFullName.getText().toString();
        if(fullName== null || fullName.isEmpty())  {
            Toast.makeText(this.context, "Chưa nhập email", Toast.LENGTH_LONG).show();
            return;
        }
        this.dismiss(); // Close Dialog
        if(this.listener!= null)  {
            this.listener.fullNameEntered(fullName);
        }
    }

    // User click "Cancel" button.
    private void buttonCancelClick()  {
        this.dismiss();
    }
}
