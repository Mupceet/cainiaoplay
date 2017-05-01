package com.cniao5.cniao5play.di.module;

import android.app.ProgressDialog;

import com.cniao5.cniao5play.data.RecommendModel;
import com.cniao5.cniao5play.data.http.ApiService;
import com.cniao5.cniao5play.presenter.RecommendPresenter;
import com.cniao5.cniao5play.presenter.contract.RecommendContract;
import com.cniao5.cniao5play.ui.adapter.RecommendAppAdapter;
import com.cniao5.cniao5play.ui.fragment.RecommendFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by lgz_e on 2017/5/1.
 */

@Module
public class RecommendModule {

    private RecommendContract.View mView;
    public RecommendModule(RecommendContract.View view) {
        this.mView = view;
    }

    @Provides
    public RecommendContract.Presenter providePresenter(RecommendContract.View view, RecommendModel model) {
        return new RecommendPresenter(view, model);
    }

    @Provides
    public RecommendContract.View provideView() {
        return mView;
    }

    @Provides
    public RecommendModel provideModel(ApiService apiService) {
        return new RecommendModel(apiService);
    }

    @Provides
    public ProgressDialog provideProgressDialog(RecommendContract.View view) {
        return new ProgressDialog(((RecommendFragment)view).getActivity());
    }

}

