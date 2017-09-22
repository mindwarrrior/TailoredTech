package com.example.tt.tailoredtech;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.tt.tailoredtech.Activities.LoginActivity;
import com.example.tt.tailoredtech.Activities.SignUpActivity;
import com.example.tt.tailoredtech.Adapters.PageAdapter;
import com.rd.PageIndicatorView;

public class SplashScreen extends AppCompatActivity {
    private ViewPager viewPager;
    private Button btnSignIn, btnSignUp;
    private TextView tvTTName, tvWelcome;
    private PageIndicatorView pageIndicatorView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        initializtion();
        initFonts();
        setListeners();
    }

    private void initializtion() {
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        btnSignIn = (Button) findViewById(R.id.btnSignIn);
        btnSignUp = (Button) findViewById(R.id.btnSignUp);
        tvTTName = (TextView) findViewById(R.id.tv_tt_name);
        tvWelcome = (TextView) findViewById(R.id.tv_welcome);
        pageIndicatorView = (PageIndicatorView) findViewById(R.id.pageIndicatorView);

        String[] messages = getResources().getStringArray(R.array.messages);
        PageAdapter adapter = new PageAdapter(getSupportFragmentManager(), messages);
        viewPager.setAdapter(adapter);
        pageIndicatorView.setViewPager(viewPager);
    }

    private void initFonts() {
        TTMgmtUtils.setTypeface(SplashScreen.this,new View[]{tvTTName},Constants.AMERICAN_TYPEWRITER);
        TTMgmtUtils.setTypeface(SplashScreen.this,new View[]{tvWelcome,btnSignIn,btnSignUp},Constants.KARLA_BOLD);
    }

    private void setListeners() {
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SplashScreen.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SplashScreen.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
    }
}
