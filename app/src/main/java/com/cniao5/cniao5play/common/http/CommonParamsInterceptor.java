package com.cniao5.cniao5play.common.http;

import android.content.Context;

import com.cniao5.cniao5play.common.Constant;
import com.cniao5.cniao5play.common.uitls.DensityUtil;
import com.cniao5.cniao5play.common.uitls.DeviceUtils;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

/**
 * Created by lgz on 8/15/17.
 */

public class CommonParamsInterceptor implements Interceptor {

    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private Gson mGson;
    private Context mContext;

    public CommonParamsInterceptor(Context context, Gson gson) {

        this.mContext = context;
        this.mGson = gson;

    }


    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();// 112.124.22.238:8081/course_api/cniaoplay/featured?p={'page':0}


        // TODO: 8/16/17 try catch JsonSyntaxException 
        String method = request.method();
        HashMap<String, Object> commonParamsMap = new HashMap<>();

//        commonParamsMap.put(Constant.IMEI, DeviceUtils.getIMEI(mContext));
        commonParamsMap.put(Constant.MODEL, DeviceUtils.getModel());
        commonParamsMap.put(Constant.LANGUAGE, DeviceUtils.getLanguage());
        commonParamsMap.put(Constant.os, DeviceUtils.getBuildVersionIncremental());
        commonParamsMap.put(Constant.RESOLUTION, DensityUtil.getScreenW(mContext) + "*" + DensityUtil.getScreenH(mContext));
        commonParamsMap.put(Constant.SDK, DeviceUtils.getBuildVersionSDK() + "");
        commonParamsMap.put(Constant.DENSITY_SCALE_FACTOR, mContext.getResources().getDisplayMetrics().density + "");


        if (method.equals("GET")) {
            HttpUrl httpUrl = request.url();
            HashMap<String, Object> rootMap = new HashMap<>();

            Set<String> paramNames = httpUrl.queryParameterNames();

            for (String key : paramNames) {

                if (Constant.PARAM.equals(key)) {

                    String oldParamJson = httpUrl.queryParameter(Constant.PARAM);
                    if (oldParamJson != null) {
                        HashMap<String, Object> p = mGson.fromJson(oldParamJson, HashMap.class); // 原始参数

                        if (p != null) {
                            for (Map.Entry<String, Object> entry : p.entrySet()) {

                                rootMap.put(entry.getKey(), entry.getValue());
                            }
                        }
                    }
                } else {
                    rootMap.put(key, httpUrl.queryParameter(key));
                }
            }

            rootMap.put("commonParams", commonParamsMap);// 重新组装
            String newParams = mGson.toJson(rootMap);// {"page":0,"publicParams":{"imei":'xxxxx',"sdk":14,.....}}
            String url = httpUrl.toString();
            //  http://112.124.22.238:8081/course_api/cniaoplay/featured?p= {"page":0,"publicParams":{"imei":'xxxxx',"sdk":14,.....}}
            int index = url.indexOf("?");
            if (index > 0) {
                url = url.substring(0, url.indexOf("?"));
            }
            url = url + "?" + Constant.PARAM + "=" + newParams;
            request = request.newBuilder().url(url).build();

        } else if (method.equals("POST")) {
            RequestBody body = request.body();
            
            HashMap<String, Object> rootMap = new HashMap<>();
            
            if (body instanceof FormBody) {
                for (int i = 0; i < ((FormBody) body).size(); i++) {
                    rootMap.put(((FormBody) body).encodedName(i), ((FormBody) body).encodedValue(i));
                }
            } else {

                Buffer buffer = new Buffer();
                body.writeTo(buffer);
                
                String oldJsonParams = buffer.readUtf8();
                rootMap = mGson.fromJson(oldJsonParams, HashMap.class); // 原始参数
            }
            rootMap.put("commonParams", commonParamsMap);// 重新组装
            String newParams = mGson.toJson(rootMap);// {"page":0,"publicParams":{"imei":'xxxxx',"sdk":14,.....}}
            request = request.newBuilder().post(RequestBody.create(JSON, newParams)).build();
        }

        return chain.proceed(request);
    }
}
