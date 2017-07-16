package com.cniao5.cniao5play.common.rx.suscriber;

import android.app.ProgressDialog;
import android.content.Context;

import com.cniao5.cniao5play.common.uitls.ProgressDialogHandler;

/**
 * Created by lgz on 7/16/17.
 */

public abstract class ProgressDialogSubscriber<T> extends ErrorHandlerSubscriber<T>
        implements ProgressDialogHandler.OnProgressDialogCancelListener {

    private ProgressDialogHandler mProgressDialogHandler;

    public ProgressDialogSubscriber(Context context) {
        super(context);
        mProgressDialogHandler = new ProgressDialogHandler(context);
    }

    protected boolean shouldShowDialog() {
        return true;
    }
    @Override
    public void onError(Throwable e) {
        if (shouldShowDialog()) {
            mProgressDialogHandler.dismissProgressDialog();
        }
        super.onError(e);
    }

    @Override
    public void onStart() {
        if (shouldShowDialog()) {
            mProgressDialogHandler.showProgressDialog();
        }
    }

    @Override
    public void onCompleted() {
        if (shouldShowDialog()) {
            mProgressDialogHandler.dismissProgressDialog();
        }
    }

    @Override
    public void onProgressDialogCancel() {
        unsubscribe();
    }
}
