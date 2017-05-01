package com.cniao5.cniao5play.di.component;

import com.cniao5.cniao5play.di.FragmentScope;
import com.cniao5.cniao5play.di.module.RecommendModule;
import com.cniao5.cniao5play.ui.fragment.RecommendFragment;

import dagger.Component;

/**
 * Created by lgz_e on 2017/5/1.
 */
@FragmentScope
@Component(modules = RecommendModule.class, dependencies = AppComponent.class)
public interface RecommendComponent {

    void inject(RecommendFragment fragment);
}
