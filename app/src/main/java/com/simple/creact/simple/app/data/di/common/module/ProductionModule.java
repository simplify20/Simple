package com.simple.creact.simple.app.data.di.common.module;


import com.simple.creact.simple.app.data.di.common.ActivityScope;
import com.simple.creact.simple.app.presentation.bindingadapter.ImageAdapter;

import dagger.Module;
import dagger.Provides;

/**
 * @author:YJJ
 * @date:2016/3/14
 * @email:yangjianjun@117go.com
 */
@Module
public class ProductionModule {
    @ActivityScope
    @Provides
    ImageAdapter imageAdapter() {
        return new ImageAdapter();
    }
}
