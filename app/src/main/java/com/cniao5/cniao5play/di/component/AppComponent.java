package com.cniao5.cniao5play.di.component;

import android.app.Application;

import com.cniao5.cniao5play.AppApplication;
import com.cniao5.cniao5play.common.rx.RxErrorHandler;
import com.cniao5.cniao5play.data.http.ApiService;
import com.cniao5.cniao5play.di.module.ApplicationModule;
import com.cniao5.cniao5play.di.module.HttpModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by lgz_e on 2017/5/1.
 */
@Singleton
@Component(modules = {ApplicationModule.class, HttpModule.class})
public interface AppComponent {

    ApiService getApiService();

    Application getApplication();

    RxErrorHandler getRxErrorHandler();

}
