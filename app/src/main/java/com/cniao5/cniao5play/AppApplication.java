package com.cniao5.cniao5play;

import android.app.Application;
import android.content.Context;

import com.cniao5.cniao5play.di.component.AppComponent;
import com.cniao5.cniao5play.di.component.DaggerAppComponent;
import com.cniao5.cniao5play.di.module.AppModule;
import com.cniao5.cniao5play.di.module.HttpModule;
import com.google.gson.Gson;

/**
 * Created by lgz_e on 2017/5/1.
 */

public class AppApplication extends Application {

    private AppComponent mAppComponent;

    public static Application getApplication(Context context) {
        return (Application)context.getApplicationContext();
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .httpModule(new HttpModule())
                .build();
    }
}
