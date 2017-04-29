package com.cniao5.cniao5play.data;

import com.cniao5.cniao5play.bean.AppInfo;
import com.cniao5.cniao5play.bean.PageBean;
import com.cniao5.cniao5play.http.ApiService;
import com.cniao5.cniao5play.http.HttpManager;

import retrofit2.Callback;

/**
 * Created by Ivan on 2017/1/3.
 */

public class RecommendModel {


    public void getApps(Callback<PageBean<AppInfo>> callback) {

        HttpManager manager = new HttpManager();

        ApiService apiService = manager.getRetrofit(manager.getOkHttpClient()).create(ApiService.class);

        apiService.getApps("{'page':0}").enqueue(callback);

    }


}
