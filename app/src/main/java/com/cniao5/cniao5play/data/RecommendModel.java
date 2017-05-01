package com.cniao5.cniao5play.data;

import com.cniao5.cniao5play.bean.AppInfo;
import com.cniao5.cniao5play.bean.PageBean;
import com.cniao5.cniao5play.data.http.ApiService;

import retrofit2.Callback;

/**
 * Created by Ivan on 2017/1/3.
 */

public class RecommendModel {

    private  ApiService mApiService;
    public RecommendModel(ApiService apiService) {
        mApiService = apiService;
    }
    public void getApps(Callback<PageBean<AppInfo>> callback) {

        mApiService.getApps("{'page':0}").enqueue(callback);

    }


}
