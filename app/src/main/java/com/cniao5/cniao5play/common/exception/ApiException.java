package com.cniao5.cniao5play.common.exception;

/**
 * Created by lgz on 7/8/17.
 */

public class ApiException extends BaseException {

    private int mErrorCode;
    private String mErrorMessage;

    public ApiException(int errorCode, String errorMessage) {
        super(errorCode, errorMessage);
    }
}
