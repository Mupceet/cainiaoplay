package com.cniao5.cniao5play.common.rx.suscriber;

import android.content.Context;

import com.cniao5.cniao5play.common.exception.BaseException;
import com.cniao5.cniao5play.common.uitls.ProgressDialogHandler;
import com.cniao5.cniao5play.ui.BaseView;

/**
 * Created by lgz on 7/16/17.
 */

public abstract class ProgressSubscriber<T> extends ErrorHandlerSubscriber<T>
        implements ProgressDialogHandler.OnProgressDialogCancelListener {


    private BaseView mBaseView;

    public ProgressSubscriber(Context context, BaseView baseview) {
        super(context);
        mBaseView = baseview;
    }

    protected boolean shouldShowDialog() {
        return true;
    }
    @Override
    public void onError(Throwable e) {
        BaseException baseException = mRxErrorHandler.handleError(e);
        mBaseView.showError(baseException.getErrorDisplayMessage());
    }

    @Override
    public void onStart() {
        if (shouldShowDialog()) {
            mBaseView.showLoading();
        }
    }

    @Override
    public void onCompleted() {
        mBaseView.dismissLoading();
    }

    @Override
    public void onProgressDialogCancel() {
        unsubscribe();
    }
}
