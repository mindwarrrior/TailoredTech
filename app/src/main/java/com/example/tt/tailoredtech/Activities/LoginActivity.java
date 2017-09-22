package com.example.tt.tailoredtech.Activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextPaint;
import android.text.TextWatcher;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.tt.tailoredtech.Constants;
import com.example.tt.tailoredtech.R;
import com.example.tt.tailoredtech.TTMgmtUtils;


public class LoginActivity extends AppCompatActivity {
    private Button btnSignIn;
    private Button btnFbConnect;
    private EditText etEmail;
    private EditText etPwd;
    private TextView tvTTName;
    private TextView tvSignUpmsg;
    private TextView tvSignUpTop;
    private boolean isValidEmail;
    TextWatcher watcher = new TextWatcher() {
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            validator();
        }
    };
    TextWatcher emailWatcher = new TextWatcher() {
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            isValidEmail = TTMgmtUtils.isValidEmail(s.toString());
            if (!isValidEmail)
                etEmail.setError(Constants.ERROR_EMAIL);
            validator();
        }
    };
    private TextInputLayout inputLayoutEmail;
    private TextInputLayout inputLayoutPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initialize();
        initFonts();
        setListeners();
    }

    private void initialize() {
        btnSignIn = (Button) findViewById(R.id.btnSignIn);
        btnFbConnect = (Button) findViewById(R.id.btnFb);
        etEmail = (EditText) findViewById(R.id.et_email);
        etPwd = (EditText) findViewById(R.id.et_password);
        tvTTName = (TextView) findViewById(R.id.tv_tt_name);
        tvSignUpmsg = (TextView) findViewById(R.id.tv_sign_in_message);
        tvSignUpTop = (TextView) findViewById(R.id.tv_signup_top);
        inputLayoutEmail = (TextInputLayout) findViewById(R.id.input_layout_email);
        inputLayoutPwd = (TextInputLayout) findViewById(R.id.input_layout_password);
    }

    private void initFonts() {
        TTMgmtUtils.setTypeface(LoginActivity.this, new View[]{tvTTName}, Constants.AMERICAN_TYPEWRITER);
        TTMgmtUtils.setTypeface(LoginActivity.this, new View[]{tvSignUpmsg, etEmail, etPwd,inputLayoutEmail,inputLayoutPwd}, Constants.KARLA_REGULAR);
        TTMgmtUtils.setTypeface(LoginActivity.this, new View[]{btnFbConnect, tvSignUpTop}, Constants.KARLA_BOLD);
    }

    private void setListeners() {
        etEmail.addTextChangedListener(emailWatcher);
        etPwd.addTextChangedListener(watcher);
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        ClickableSpan signInClick = new ClickableSpan() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(false);
                ds.setTypeface(Typeface.createFromAsset(getAssets(), Constants.KARLA_BOLD));
                //ds.setColor(mIsPressed ? ContextCompat.getColor(LoginActivity.this,R.color.pinkishGray) : ContextCompat.getColor(LoginActivity.this,R.color.lemon));
            }
        };
        TTMgmtUtils.makeLinks(tvSignUpmsg, new String[]{Constants.SIGN_UP_LABEL}, new ClickableSpan[]{signInClick});
    }

    public void validator() {


        if (isValidEmail && !(etPwd.getText().toString().isEmpty()))
            btnSignIn.setEnabled(true);
        else
            btnSignIn.setEnabled(false);
    }

    public void onSignUpClick(View view) {
        Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
        startActivity(intent);
    }
}
