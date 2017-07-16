package com.cniao5.cniao5play.common.rx.suscriber;

import android.content.Context;

import com.cniao5.cniao5play.common.exception.ApiException;
import com.cniao5.cniao5play.common.exception.BaseException;
import com.cniao5.cniao5play.common.exception.ErrorMessageFactory;
import com.cniao5.cniao5play.common.rx.RxErrorHandler;

import org.json.JSONException;

import java.net.SocketException;
import java.net.SocketTimeoutException;

import retrofit2.adapter.rxjava.HttpException;

/**
 * Created by lgz on 7/8/17.
 */

public abstract class ErrorHandlerSubscriber<T> extends DefaultSubscriber<T> {

    private RxErrorHandler mRxErrorHandler;

    public ErrorHandlerSubscriber(Context context) {
        mRxErrorHandler = new RxErrorHandler(context);
    }

    @Override
    public void onError(Throwable e) {
        BaseException baseException = mRxErrorHandler.handleError(e);
        mRxErrorHandler.showErrorDisplayMessage(baseException);
    }
}
