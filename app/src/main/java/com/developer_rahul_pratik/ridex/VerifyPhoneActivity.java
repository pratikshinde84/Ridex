package com.developer_rahul_pratik.ridex;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class VerifyPhoneActivity extends AppCompatActivity {

    private EditText otp1, otp2, otp3, otp4;
    private Button btnVerify;
    private TextView tvResend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_verify_phone);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initViews();
        setupOtpInputs();
        setupResendText();
        setupListeners();
    }

    private void initViews() {
        otp1 = findViewById(R.id.otp1);
        otp2 = findViewById(R.id.otp2);
        otp3 = findViewById(R.id.otp3);
        otp4 = findViewById(R.id.otp4);
        btnVerify = findViewById(R.id.btnVerify);
        tvResend = findViewById(R.id.tvResend);
    }

    private void setupOtpInputs() {
        EditText[] otps = {otp1, otp2, otp3, otp4};
        for (int i = 0; i < otps.length; i++) {
            final int index = i;
            otps[i].addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (s.length() == 1 && index < otps.length - 1) {
                        otps[index + 1].requestFocus();
                    }
                    checkOtpComplete();
                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (s.length() == 0 && index > 0) {
                        otps[index - 1].requestFocus();
                    }
                }
            });
        }
    }

    private void checkOtpComplete() {
        boolean complete = otp1.getText().length() == 1 &&
                otp2.getText().length() == 1 &&
                otp3.getText().length() == 1 &&
                otp4.getText().length() == 1;
        
        if (complete) {
            btnVerify.setBackgroundTintList(android.content.res.ColorStateList.valueOf(Color.BLACK));
            btnVerify.setEnabled(true);
        } else {
            btnVerify.setBackgroundTintList(android.content.res.ColorStateList.valueOf(Color.LTGRAY));
            btnVerify.setEnabled(false);
        }
    }

    private void setupListeners() {
        findViewById(R.id.btnBack).setOnClickListener(v -> finish());
        
        btnVerify.setOnClickListener(v -> {
            Toast.makeText(this, "Phone verified successfully", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });
    }

    private void setupResendText() {
        String resend = getString(R.string.resend_code);
        String baseText = getString(R.string.didnt_receive_code, resend);
        SpannableString ss = new SpannableString(baseText);

        int start = baseText.indexOf(resend);
        int end = start + resend.length();

        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                Toast.makeText(VerifyPhoneActivity.this, "OTP Resent", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(false);
                ds.setColor(Color.BLACK);
                ds.setFakeBoldText(true);
            }
        };

        ss.setSpan(clickableSpan, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvResend.setText(ss);
        tvResend.setMovementMethod(LinkMovementMethod.getInstance());
    }
}