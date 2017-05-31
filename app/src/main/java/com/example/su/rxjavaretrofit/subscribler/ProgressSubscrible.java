package com.example.su.rxjavaretrofit.subscribler;

import com.example.su.rxjavaretrofit.progress.ProgressDialogHandler;
import com.example.su.rxjavaretrofit.util.ToastUtil;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import rx.Subscriber;

/**
 * Created by su on 2017/5/26.
 */

public class ProgressSubscrible<T> extends Subscriber<T>  {

    private SubscriberOnNextListener mSubListener;
    private ProgressDialogHandler handler;

    public ProgressSubscrible(SubscriberOnNextListener mSubListener) {
        this.mSubListener = mSubListener;
        this.handler = new ProgressDialogHandler(false);
    }

    @Override
    public void onStart() {
        showProgressDialog();
    }

    @Override
    public void onCompleted() {
        ToastUtil.showText("网络请求完成");
        dissmissDialog();
    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof SocketTimeoutException) {
            ToastUtil.showText("网络中断，请检查您的网络状态");
        } else if (e instanceof ConnectException) {
            ToastUtil.showText("网络中断，请检查您的网络状态");
        } else {
            ToastUtil.showText("网络请求出错");
        }
        dissmissDialog();
    }

    @Override
    public void onNext(T t) {
        if (mSubListener != null) {
            mSubListener.onNext(t);
        }
    }

    private void showProgressDialog() {
        if (handler != null) {
            handler.obtainMessage(ProgressDialogHandler.SHOW_PROGRESS_DIALOG).sendToTarget();
        }
    }

    private void dissmissDialog() {
        if (handler != null) {
            handler.obtainMessage(ProgressDialogHandler.DISMISS_PROGRESS_DIALOG).sendToTarget();
        }
        handler = null;
    }


//    @Override
//    public void onDismiss() {
//        if (!this.isUnsubscribed()) {
//            this.unsubscribe();
//        }
//    }

}
