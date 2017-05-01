package com.cniao5.cniao5play.di.component;

import com.cniao5.cniao5play.data.http.ApiService;
import com.cniao5.cniao5play.di.module.AppModule;
import com.cniao5.cniao5play.di.module.HttpModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by lgz_e on 2017/5/1.
 */
@Singleton
@Component(modules = {AppModule.class, HttpModule.class})
public interface AppComponent {

    ApiService getApiService();

}
