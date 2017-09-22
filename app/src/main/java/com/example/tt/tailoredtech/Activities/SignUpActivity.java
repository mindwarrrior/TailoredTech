package com.example.tt.tailoredtech.Activities;

import android.app.DatePickerDialog;
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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.example.tt.tailoredtech.Constants;
import com.example.tt.tailoredtech.Adapters.SpinnerAdapter;
import com.example.tt.tailoredtech.Models.UserData;
import com.example.tt.tailoredtech.R;
import com.example.tt.tailoredtech.TTMgmtUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;

public class SignUpActivity extends AppCompatActivity {

    private EditText etName;
    private EditText etEmail;
    private EditText etDob;
    private EditText etPwd;
    private EditText etCPwd;
    private TextView tvTTName;
    private TextView tvSignInMsg;
    private Spinner spnExperiance;
    private Spinner spnPosition;
    private RadioButton rbtnMale;
    private RadioButton rbtnFemale;
    private Button btnSignUp;

    private TextInputLayout inputLayoutName;
    private TextInputLayout inputLayoutEmail;
    private TextInputLayout inputLayoutDob;
    private TextInputLayout inputLayoutPwd;
    private TextInputLayout inputLayoutCPwd;

    private boolean isValidEmail;
    private boolean isGender;
    private boolean isValidPwd;
    TextWatcher genericWatcher = new TextWatcher() {
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
    TextWatcher pwdWatcher = new TextWatcher() {
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            isValidPwd = etCPwd.getText().toString().equals(etPwd.getText().toString());
            if (!isValidPwd)
                etCPwd.setError(Constants.ERROR_PWD);
            else
                etCPwd.setError(null);
            validator();
        }
    };
    private UserData userData;
    private int year = 1990;
    private int month = 0;
    private int day = 1;
    private DatePickerDialog.OnDateSetListener dPickerListener =
            new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                    SignUpActivity.this.year = year;
                    SignUpActivity.this.month = month;
                    SignUpActivity.this.day = day;
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(SignUpActivity.this.year, SignUpActivity.this.month, SignUpActivity.this.day);
                    SimpleDateFormat format = new SimpleDateFormat(Constants.MY_DATE_FORMAT);
                    etDob.setText(format.format(calendar.getTime()));
                }
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        initialization();
        initFonts();
        setListeners();
    }

    private void initialization() {
        userData = new UserData();

        etName = (EditText) findViewById(R.id.et_name);
        etEmail = (EditText) findViewById(R.id.et_email);
        etDob = (EditText) findViewById(R.id.et_dob);
        etPwd = (EditText) findViewById(R.id.et_password);
        etCPwd = (EditText) findViewById(R.id.et_confirm_password);
        tvTTName = (TextView) findViewById(R.id.tv_tt_name);
        tvSignInMsg = (TextView) findViewById(R.id.tv_sign_in_message);
        spnExperiance = (Spinner) findViewById(R.id.spinner_experiance);
        spnPosition = (Spinner) findViewById(R.id.spinner_position);
        rbtnMale = (RadioButton) findViewById(R.id.rbtnMale);
        rbtnFemale = (RadioButton) findViewById(R.id.rbtnFemale);
        btnSignUp = (Button) findViewById(R.id.btnSignUp);
        inputLayoutName = (TextInputLayout) findViewById(R.id.input_layout_name);
        inputLayoutEmail = (TextInputLayout) findViewById(R.id.input_layout_email);
        inputLayoutDob = (TextInputLayout) findViewById(R.id.input_layout_dob);
        inputLayoutPwd = (TextInputLayout) findViewById(R.id.input_layout_password);
        inputLayoutCPwd = (TextInputLayout) findViewById(R.id.input_layout_confirm_password);

        SpinnerAdapter posAdapter = new SpinnerAdapter(SignUpActivity.this,
                R.layout.spinner_item,
                Arrays.asList(getResources().getStringArray(R.array.position_values))
        );
        spnPosition.setAdapter(posAdapter);
        SpinnerAdapter expAdapter = new SpinnerAdapter(SignUpActivity.this,
                R.layout.spinner_item,
                Arrays.asList(getResources().getStringArray(R.array.experiance_values))
        );
        spnExperiance.setAdapter(expAdapter);

    }

    private void initFonts() {
        TTMgmtUtils.setTypeface(SignUpActivity.this, new View[]{tvTTName}, Constants.AMERICAN_TYPEWRITER);
        TTMgmtUtils.setTypeface(SignUpActivity.this, new View[]{
                etName,
                etEmail,
                etDob,
                etPwd,
                etCPwd,
                tvSignInMsg,
                rbtnMale,
                rbtnFemale,
                inputLayoutName,
                inputLayoutEmail,
                inputLayoutDob,
                inputLayoutPwd,
                inputLayoutCPwd
        }, Constants.KARLA_REGULAR);
        TTMgmtUtils.setTypeface(SignUpActivity.this, new View[]{btnSignUp}, Constants.KARLA_BOLD);
    }

    private void setListeners() {
        etName.addTextChangedListener(genericWatcher);
        etEmail.addTextChangedListener(emailWatcher);
        etDob.addTextChangedListener(genericWatcher);
        etPwd.addTextChangedListener(pwdWatcher);
        etCPwd.addTextChangedListener(pwdWatcher);

        etDob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(SignUpActivity.this, dPickerListener, year, month, day).show();
            }
        });
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submit();
            }
        });
        spnExperiance.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                validator();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spnPosition.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                validator();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ClickableSpan signInClick = new ClickableSpan() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(false);
                ds.setTypeface(Typeface.createFromAsset(getAssets(), Constants.KARLA_BOLD));
            }
        };
        TTMgmtUtils.makeLinks(tvSignInMsg, new String[]{Constants.SIGN_IN_LABEL}, new ClickableSpan[]{signInClick});
    }

    public void onRadioClick(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.rbtnMale:
                if (checked)
                    userData.setGender(Constants.GENDER_MALE);
                break;
            case R.id.rbtnFemale:
                if (checked)
                    userData.setGender(Constants.GENDER_FEMALE);
        }
        isGender = true;
        validator();
    }

    public void validator() {
        if (!etName.getText().toString().isEmpty() && isValidEmail && !etDob.getText().toString().isEmpty() &&
                isGender && spnExperiance.getSelectedItemPosition() > 0 && spnPosition.getSelectedItemPosition() > 0 && isValidPwd)
            btnSignUp.setEnabled(true);
        else
            btnSignUp.setEnabled(false);
    }

    private void submit() {
        userData.setName(etName.getText().toString());
        userData.setEmail(etEmail.getText().toString());
        userData.setDob(etDob.getText().toString());
        userData.setExperiance(spnExperiance.getItemAtPosition(spnExperiance.getSelectedItemPosition()).toString());
        userData.setPosition(spnPosition.getItemAtPosition(spnPosition.getSelectedItemPosition()).toString());
        userData.setPwd(etPwd.getText().toString());
        Gson gson = new GsonBuilder().create();
        String u = gson.toJson(userData);
        Toast.makeText(SignUpActivity.this, u, Toast.LENGTH_LONG).show();
    }
}