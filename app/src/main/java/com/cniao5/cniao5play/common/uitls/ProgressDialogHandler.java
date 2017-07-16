package com.cniao5.cniao5play.common.uitls;

import android.content.Context;
import android.support.annotation.NonNull;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.cniao5.cniao5play.R;

/**
 * Created by lgz on 7/16/17.
 */

public class ProgressDialogHandler {

    private Context mContext;
    private boolean isCancelable;
    private MaterialDialog mProgressDialog;

    private OnProgressDialogCancelListener mCallBack;


    public ProgressDialogHandler(Context context, boolean isCancelable,
                                 OnProgressDialogCancelListener callBack) {
        mContext = context;
        this.isCancelable = isCancelable;
        mCallBack = callBack;

        initProgressDialog();
    }

    private void initProgressDialog() {
        if (mProgressDialog == null) {
            MaterialDialog.Builder builder = new MaterialDialog.Builder(mContext)
                    .content(R.string.loading)
                    .cancelable(isCancelable)
                    .progress(true, 0);
            if (isCancelable) {
                builder.positiveText(android.R.string.cancel)
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                mCallBack.onProgressDialogCancel();
                            }
                        });
            }
            mProgressDialog = builder.build();
        }
    }

    public ProgressDialogHandler(Context context) {
        this(context, false, null);
    }


    public void showProgressDialog() {
        if (mProgressDialog != null && !mProgressDialog.isShowing()) {
            mProgressDialog.show();
        }

    }

    public void dismissProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    public interface OnProgressDialogCancelListener {
        void onProgressDialogCancel();
    }

}
