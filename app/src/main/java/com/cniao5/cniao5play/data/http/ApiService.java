package com.cniao5.cniao5play.data.http;

import com.cniao5.cniao5play.bean.AppInfo;
import com.cniao5.cniao5play.bean.BaseBean;
import com.cniao5.cniao5play.bean.LoginRequestBean;
import com.cniao5.cniao5play.bean.PageBean;

import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Ivan on 2016/12/30.
 */

public interface ApiService {
    String BASE_URL = "http://112.124.22.238:8081/course_api/cniaoplay/";

//    @GET("featured")
//    Observable<PageBean<AppInfo>> getApps(@Query("p") String jsonParam);

    @GET("featured2")
    Observable<BaseBean<PageBean<AppInfo>>> getApps(@Query("p") String jsonParam);

//    @GET("index")
//    public Observable<BaseBean<AppInfo>> index();
//
//    @GET("toplist")
//    public Observable<BaseBean<AppInfo>> topList(@Query("page") int page); //


    //{"phone":"","password":""}
    @POST("login")
    public Observable<BaseBean> login(@Body LoginRequestBean bean);


//    public static final MediaType JSON
//            = MediaType.parse("application/json; charset=utf-8");
//
//    OkHttpClient client = new OkHttpClient();
//
//    String post(String url, String json) throws IOException {
//        RequestBody body = RequestBody.create(JSON, json);
//        Request request = new Request.Builder()
//                .url(url)
//                .post(body)
//                .build();
//        Response response = client.newCall(request).execute();
//        return response.body().string();
//    }


    @FormUrlEncoded // FormBody
    @POST("login")
    public void login2(@Field("phone") String phone);

//
//    FormBody.Builder builder = new FormBody.Builder();
//
//    builder.addEncoded("phone","");
//
//    body = builde.build();

//    Request request = new Request.Builder()
//                .url(url)
//                .post(body)
//                .build();
//        Response response = client.newCall(request).execute();
//        return response.body().string();
//    }
}
