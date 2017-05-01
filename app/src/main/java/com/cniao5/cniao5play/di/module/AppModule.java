package com.cniao5.cniao5play.di.module;

import android.app.Application;

import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by lgz_e on 2017/5/1.
 */

@Module
public class AppModule {

    private Application mApplication;
    public AppModule(Application application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    public Application privateApplication() {
        return mApplication;
    }

    @Provides
    @Singleton
    public Gson provideGson() {
        return new Gson();
    }
}
