package com.cniao5.cniao5play.ui.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.cniao5.cniao5play.AppApplication;
import com.cniao5.cniao5play.R;
import com.cniao5.cniao5play.bean.AppInfo;
import com.cniao5.cniao5play.di.component.AppComponent;
import com.cniao5.cniao5play.di.component.DaggerRecommendComponent;
import com.cniao5.cniao5play.di.module.RecommendModule;
import com.cniao5.cniao5play.presenter.RecommendPresenter;
import com.cniao5.cniao5play.presenter.contract.RecommendContract;
import com.cniao5.cniao5play.ui.adapter.RecommendAppAdapter;
import com.cniao5.cniao5play.ui.decoration.DividerItemDecoration;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Ivan on 16/9/26.
 */

public class RecommendFragment extends ProgressFragment<RecommendPresenter> implements RecommendContract.View {

    @BindView(R.id.recycle_view)
    RecyclerView mRecyclerView;

    private RecommendAppAdapter mAdapter;

    @Inject
    ProgressDialog mProgressDialog;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

    @Override
    public int setLayout() {
        return R.layout.fragment_recomend;
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerRecommendComponent.builder()
                .appComponent(appComponent)
                .recommendModule(new RecommendModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void init() {
        mPresenter.requestData();
    }

    @Override
    public void onEmptyViewClick() {
        mPresenter.requestData();
    }

    private void initRecycleView(List<AppInfo> datas) {
        //为RecyclerView设置布局管理器
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //为RecyclerView设置分割线(这个可以对DividerItemDecoration进行修改，自定义)
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.HORIZONTAL_LIST));
        //动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new RecommendAppAdapter(getActivity(), datas);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void showResult(List<AppInfo> datas) {
        initRecycleView(datas);
    }

    @Override
    public void showNoData() {
        Toast.makeText(getActivity(), "暂时无数据，请吃完饭再来", Toast.LENGTH_LONG).show();
    }

}
