package com.example.colombocitycentreshoppingapp.data.helpers;

import static android.content.Context.INPUT_METHOD_SERVICE;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class CommonUtils {

    final String TAG = this.getClass().getSimpleName();

    private static CommonUtils instance = null;

    private CommonUtils() {
    }

    public static CommonUtils getInstance() {
        if (instance == null) {
            instance = new CommonUtils();

        }
        return instance;
    }

    public void hideKeyBoard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(INPUT_METHOD_SERVICE);
        View focusedView = activity.getCurrentFocus();

        if (focusedView != null) {
            imm.hideSoftInputFromWindow(focusedView.getWindowToken(), 0);
        }
    }

    public void hideKeyboardDialog(View view, Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public final static boolean isValidPhoneNumber(CharSequence target) {
        if (target == null || target.length() < 8 || target.length() > 15) {
            return false;
        } else {
            return android.util.Patterns.PHONE.matcher(target).matches();
        }
    }
}
