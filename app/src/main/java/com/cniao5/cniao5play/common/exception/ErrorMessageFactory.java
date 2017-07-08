package com.cniao5.cniao5play.common.exception;

import android.content.Context;

import com.cniao5.cniao5play.R;

/**
 * Created by lgz on 7/8/17.
 */

public class ErrorMessageFactory {
    
    public static String create(Context context, int errorCode) {
        String errorDisplayMessage;
        
        switch (errorCode) {
            case BaseException.HTTP_ERROR:
                errorDisplayMessage =  context.getResources().getString(R.string.error_http);
                break;
            case BaseException.SOCKET_TIMEOUT_ERROR:
                errorDisplayMessage =  context.getResources().getString(R.string.error_socket_timeout);
                break;
            case BaseException.SOCKET_ERROR:
                errorDisplayMessage =  context.getResources().getString(R.string.error_socket_unreachable);
                break;
            case BaseException.ERROR_HTTP_400:
                errorDisplayMessage =  context.getResources().getString(R.string.error_http_400);
                break;
            case BaseException.ERROR_HTTP_404:
                errorDisplayMessage =  context.getResources().getString(R.string.error_http_404);
                break;
            case BaseException.ERROR_HTTP_500:
                errorDisplayMessage =  context.getResources().getString(R.string.error_http_500);
                break;
            case ApiException.ERROR_API_SYSTEM:
                errorDisplayMessage = context.getResources().getString(R.string.error_system);
                break;
            case ApiException.ERROR_API_ACCOUNT_FREEZE:
                errorDisplayMessage = context.getResources().getString(R.string.error_account_freeze);
                break;
            case ApiException.ERROR_API_NO_PERMISSION:
                errorDisplayMessage = context.getResources().getString(R.string.error_api_no_permission);
                break;
            case ApiException.ERROR_API_LOGIN:
                errorDisplayMessage = context.getResources().getString(R.string.error_login);
                break;
            default:
                errorDisplayMessage=context.getResources().getString(R.string.error_unknown);
                break;
        }
        return errorDisplayMessage;        
    }
}
