package com.cniao5.cniao5play.presenter;

import com.cniao5.cniao5play.bean.AppInfo;
import com.cniao5.cniao5play.bean.BaseBean;
import com.cniao5.cniao5play.bean.PageBean;
import com.cniao5.cniao5play.common.rx.RxErrorHandler;
import com.cniao5.cniao5play.common.rx.RxHttpResponseCompat;
import com.cniao5.cniao5play.common.rx.suscriber.ErrorHandlerSubscriber;
import com.cniao5.cniao5play.common.rx.suscriber.ProgressDialogSubscriber;
import com.cniao5.cniao5play.data.RecommendModel;
import com.cniao5.cniao5play.presenter.contract.RecommendContract;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Ivan on 2017/1/3.
 */

public class RecommendPresenter extends BasePresenter<RecommendModel,RecommendContract.View> {

    @Inject
    public RecommendPresenter(RecommendModel model, RecommendContract.View view) {
        super(model, view);
    }

    public void requestData() {
        mModel.getApps()
                .compose(RxHttpResponseCompat.<PageBean<AppInfo>> compatResult())
                .subscribe(new ProgressDialogSubscriber<PageBean<AppInfo>>(mContext) {
                    @Override
                    public void onNext(PageBean<AppInfo> appInfoPageBean) {
                        if (appInfoPageBean != null) {
                            mView.showResult(appInfoPageBean.getDatas());
                        } else {
                            mView.showNoData();
                        }
                    }

                    @Override
                    protected boolean shouldShowDialog() {
                        return true;
                    }
                });
//                .subscribe(new ErrorHandlerSubscriber<PageBean<AppInfo>>(mContext) {
//                    @Override
//                    public void onStart() {
//                        mView.showLoading();
//                    }
//
//                    @Override
//                    public void onCompleted() {
//                        mView.dismissLoading();
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        mView.dismissLoading();
//                        super.onError(e);
//                    }
//
//                    @Override
//                    public void onNext(PageBean<AppInfo> appInfoPageBean) {
//                        if (appInfoPageBean != null) {
//                            mView.showResult(appInfoPageBean.getDatas());
//                        } else {
//                            mView.showNoData();
//                        }
//                        mView.dismissLoading();
//                    }
//                });
//        mModel.getApps(new Callback<PageBean<AppInfo>>() {
//            @Override
//            public void onResponse(Call<PageBean<AppInfo>> call, Response<PageBean<AppInfo>> response) {
//                if (response != null) {
//                    mView.showResult(response.body().getDatas());
//                } else {
//                    mView.showNoData();
//                }
//                mView.dismissLoading();
//            }
//
//            @Override
//            public void onFailure(Call<PageBean<AppInfo>> call, Throwable t) {
//                mView.dismissLoading();
//                mView.showError(t.getMessage());
//            }
//        });
    }
}
