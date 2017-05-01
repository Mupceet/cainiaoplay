package com.cniao5.cniao5play.presenter;

import com.cniao5.cniao5play.bean.AppInfo;
import com.cniao5.cniao5play.bean.PageBean;
import com.cniao5.cniao5play.data.RecommendModel;
import com.cniao5.cniao5play.presenter.contract.RecommendContract;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Ivan on 2017/1/3.
 */

public class RecommendPresenter implements RecommendContract.Presenter {

    private RecommendModel mModel;

    private RecommendContract.View mView;

    public RecommendPresenter(RecommendContract.View view, RecommendModel model) {
        this.mView = view;
        mModel = model;
    }

    @Override
    public void requestData() {
        mView.showLoading();
        mModel.getApps(new Callback<PageBean<AppInfo>>() {
            @Override
            public void onResponse(Call<PageBean<AppInfo>> call, Response<PageBean<AppInfo>> response) {
                if (response != null) {
                    mView.showResult(response.body().getDatas());
                } else {
                    mView.showNoData();
                }
                mView.dismissLoading();
            }

            @Override
            public void onFailure(Call<PageBean<AppInfo>> call, Throwable t) {
                mView.dismissLoading();
                mView.showError(t.getMessage());
            }
        });
    }
}
