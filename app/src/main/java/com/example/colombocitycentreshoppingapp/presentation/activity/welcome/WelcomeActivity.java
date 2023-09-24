package com.example.colombocitycentreshoppingapp.presentation.activity.welcome;

import static com.example.colombocitycentreshoppingapp.data.helpers.CommonUtils.isValidPhoneNumber;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.colombocitycentreshoppingapp.R;
import com.example.colombocitycentreshoppingapp.data.helpers.ApplicationConstant;
import com.example.colombocitycentreshoppingapp.data.helpers.CommonUtils;
import com.example.colombocitycentreshoppingapp.presentation.activity.main.MainActivity;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        TextView tvSkip = findViewById(R.id.tv_skip);

        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Button btGetStartedButton = findViewById(R.id.bt_get_started);
        btGetStartedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showEnterMobileNumberBottomSheetDialog();
            }
        });

        tvSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }

    private void showEnterMobileNumberBottomSheetDialog() {

        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(R.layout.bottom_sheet_enter_mobile_number);

        ConstraintLayout clNext = bottomSheetDialog.findViewById(R.id.cl_next);
        ConstraintLayout clRoot = bottomSheetDialog.findViewById(R.id.cl_root);
        EditText etMobileNumber = bottomSheetDialog.findViewById(R.id.et_mobile_no);
        TextView tvErrorEnterValidNo = bottomSheetDialog.findViewById(R.id.tv_error_enter_mobile_no);

        clNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etMobileNumber.getText().toString().trim().compareTo(ApplicationConstant.EMPTY_STRING) == 0) {
                    tvErrorEnterValidNo.setVisibility(View.VISIBLE);
                    tvErrorEnterValidNo.setText(getString(R.string.enter_your_mobile_no));
                } else if(!isValidPhoneNumber(etMobileNumber.getText().toString().trim())){
                    tvErrorEnterValidNo.setVisibility(View.VISIBLE);
                    tvErrorEnterValidNo.setText(getString(R.string.enter_valid_mobile_no));
                }else {
                    showVerifyMobileNumberBottomSheetDialog();
                    tvErrorEnterValidNo.setVisibility(View.GONE);
                    bottomSheetDialog.cancel();
                }
            }
        });

        clRoot.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                hideKeyboardDialog(v);
                return false;
            }
        });
        bottomSheetDialog.show();
    }

    private void showVerifyMobileNumberBottomSheetDialog() {

        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(R.layout.bottom_sheet_verify_mobile_number);

        ConstraintLayout clNext = bottomSheetDialog.findViewById(R.id.cl_next);
        ConstraintLayout clRoot = bottomSheetDialog.findViewById(R.id.cl_root);
        EditText etOtp = bottomSheetDialog.findViewById(R.id.et_otp);
        TextView tvErrorEnterOtp = bottomSheetDialog.findViewById(R.id.tv_error_enter_otp);

        clNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etOtp.getText().toString().trim().compareTo(ApplicationConstant.EMPTY_STRING) == 0) {
                    tvErrorEnterOtp.setVisibility(View.VISIBLE);
                } else {
                    Intent i = new Intent(WelcomeActivity.this, MainActivity.class);
                    startActivity(i);
                    tvErrorEnterOtp.setVisibility(View.GONE);
                    bottomSheetDialog.cancel();
                }
            }
        });

        clRoot.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                hideKeyboardDialog(v);
                return false;
            }
        });

        bottomSheetDialog.show();
    }

    public void hideKeyboardDialog(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}