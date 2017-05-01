package com.cniao5.cniao5play.presenter;

import com.cniao5.cniao5play.ui.BaseView;

/**
 * Created by Ivan on 2017/1/3.
 */

public class BasePresenter<M, V extends BaseView> {

    protected M mModel;
    protected V mView;

    public BasePresenter(M m, V v) {
        this.mModel = m;
        this.mView = v;
    }
}
