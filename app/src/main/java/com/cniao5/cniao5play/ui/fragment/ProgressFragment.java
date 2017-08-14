package com.cniao5.cniao5play.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.cniao5.cniao5play.AppApplication;
import com.cniao5.cniao5play.R;
import com.cniao5.cniao5play.di.component.AppComponent;
import com.cniao5.cniao5play.presenter.BasePresenter;
import com.cniao5.cniao5play.ui.BaseView;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by lgz on 7/16/17.
 */

public abstract class ProgressFragment<T extends BasePresenter> extends Fragment implements BaseView {

    private FrameLayout mRootView;
    private View mProgressView;
    private FrameLayout mContentView;
    private View mEmptyView;
    private Unbinder mUnbinder;

    private TextView mErrorText;

    private AppApplication mAppApplication;
    @Inject
    T mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = (FrameLayout) inflater.inflate(R.layout.fragment_progress, container, false);
        mProgressView = mRootView.findViewById(R.id.view_progress);
        mContentView = (FrameLayout) mRootView.findViewById(R.id.view_content);
        mEmptyView = mRootView.findViewById(R.id.view_empty);
        mEmptyView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEmptyViewClick();
            }
        });

        mErrorText = (TextView) mRootView.findViewById(R.id.text_tip);
        return mRootView;
    }

    public void onEmptyViewClick() {

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.mAppApplication = (AppApplication) getActivity().getApplication();
        setupActivityComponent(mAppApplication.getAppComponent());
        setRealContent();
        init();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mUnbinder != Unbinder.EMPTY) {
            mUnbinder.unbind();
        }
    }

    private void setRealContent() {
        View realContentView = LayoutInflater.from(getActivity()).inflate(setLayout(), mContentView, true);
        mUnbinder = ButterKnife.bind(this, realContentView);

    }

    private void showProgressView() {
        showView(R.id.view_progress);
    }

    private void showContentView() {
        showView(R.id.view_content);
    }

    private void showEmptyView(String msg) {
        showView(R.id.view_empty);
        mErrorText.setText(msg);
    }

    public void showView(int viewId) {
        for (int i = 0; i < mRootView.getChildCount(); i++) {
            if (mRootView.getChildAt(i).getId() == viewId) {
                mRootView.getChildAt(i).setVisibility(View.VISIBLE);
            } else {
                mRootView.getChildAt(i).setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void showLoading() {
        showProgressView();
    }

    @Override
    public void showError(String msg) {
        showEmptyView(msg);
    }

    @Override
    public void dismissLoading() {
        showContentView();
    }

    public abstract int setLayout();
    public abstract void setupActivityComponent(AppComponent appComponent);
    public abstract void init();
}
