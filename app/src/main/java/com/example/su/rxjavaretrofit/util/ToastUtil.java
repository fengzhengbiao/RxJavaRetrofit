package com.example.su.rxjavaretrofit.util;

import android.view.Gravity;
import android.widget.Toast;

import com.example.su.rxjavaretrofit.DemoApplication;

/**
 * Created by su on 2017/5/26.
 */

public class ToastUtil {
    private static Toast sToast;
    //    private void


    public static void showText(String text) {
        if (sToast == null) {
            sToast = Toast.makeText(DemoApplication.getContext(), text, Toast.LENGTH_SHORT);
        }
        sToast.setText(text);
        sToast.setGravity(Gravity.CENTER, 0, 0);
        sToast.show();
    }
}
