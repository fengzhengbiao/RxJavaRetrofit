package com.example.su.rxjavaretrofit.progress;

import android.os.Handler;
import android.os.Message;

import com.example.su.rxjavaretrofit.DemoApplication;
import com.example.su.rxjavaretrofit.DialogActivity;

/**
 * Created by su on 2017/5/26.
 */

public class ProgressDialogHandler extends Handler {
    public static final int SHOW_PROGRESS_DIALOG = 1;
    public static final int DISMISS_PROGRESS_DIALOG = 2;


    private boolean cancleable;


    public ProgressDialogHandler(boolean cancleable) {
        super();
        this.cancleable = cancleable;
    }

    @Override
    public void handleMessage(Message msg) {
        switch (msg.what) {
            case SHOW_PROGRESS_DIALOG:
                DialogActivity.show(DemoApplication.getContext(), cancleable);
                break;
            case DISMISS_PROGRESS_DIALOG:
                DialogActivity.dismiss(DemoApplication.getContext());
                break;
        }

        super.handleMessage(msg);
    }
}
