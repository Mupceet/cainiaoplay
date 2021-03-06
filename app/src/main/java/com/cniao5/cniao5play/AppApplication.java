package com.cniao5.cniao5play;

import android.app.Application;
import android.content.Context;

import com.cniao5.cniao5play.di.component.AppComponent;
import com.cniao5.cniao5play.di.component.DaggerAppComponent;
import com.cniao5.cniao5play.di.module.ApplicationModule;
import com.cniao5.cniao5play.di.module.HttpModule;
import com.google.gson.Gson;

/**
 * Created by lgz_e on 2017/5/1.
 */

public class AppApplication extends Application {

    private AppComponent mAppComponent;

    public static AppApplication getApplication(Context context) {
        return (AppApplication)context.getApplicationContext();
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mAppComponent = DaggerAppComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .httpModule(new HttpModule())
                .build();
    }
}
