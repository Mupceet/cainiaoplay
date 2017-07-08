package com.cniao5.cniao5play.common.rx;

import android.content.Context;
import android.widget.Toast;

import com.cniao5.cniao5play.common.exception.ApiException;
import com.cniao5.cniao5play.common.exception.BaseException;
import com.cniao5.cniao5play.common.exception.ErrorMessageFactory;

import org.json.JSONException;

import java.net.SocketException;
import java.net.SocketTimeoutException;

import retrofit2.adapter.rxjava.HttpException;

/**
 * Created by lgz on 7/8/17.
 */

public class RxErrorHandler {

    private Context mContext;

    public RxErrorHandler(Context context) {
        mContext = context;
    }

    public BaseException handleError(Throwable e) {
        BaseException exception = new BaseException();
        if (e instanceof ApiException) {
            exception.setErrorCode(((ApiException)e).getErrorCode());
        } else if (e instanceof JSONException) {
            exception.setErrorCode(BaseException.JSON_ERROR);
        } else if (e instanceof SocketTimeoutException) {
            exception.setErrorCode(BaseException.SOCKET_TIMEOUT_ERROR);
        } else if (e instanceof HttpException) {
            exception.setErrorCode(((HttpException)e).code());
        } else {
            exception.setErrorCode(BaseException.UNKNOWN_ERROR);
        }

        exception.setErrorDisplayMessage(
                ErrorMessageFactory.create(mContext, exception.getErrorCode()));

        return exception;
    }

    public void showErrorDisplayMessage(BaseException exception) {
        Toast.makeText(mContext, exception.getErrorDisplayMessage(), Toast.LENGTH_SHORT).show();
    }
}
