package com.cniao5.cniao5play.presenter.contract;

import com.cniao5.cniao5play.bean.AppInfo;
import com.cniao5.cniao5play.presenter.BasePresenter;
import com.cniao5.cniao5play.ui.BaseView;

import java.util.List;

/**
 * Created by Ivan on 2017/1/3.
 */

public interface RecommendContract {

    interface View extends BaseView {
        void showResult(List<AppInfo> data);

        void showNoData();

        void showError(String msg);
    }


    interface Presenter extends BasePresenter {
        void requestData();
    }

}
