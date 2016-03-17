package com.simple.creact.simple.app.data.di.common.component;

import android.databinding.DataBindingComponent;

import com.simple.creact.simple.app.data.di.common.ActivityScope;
import com.simple.creact.simple.app.data.di.common.module.ProductionModule;

import dagger.Component;

/**
 * @author:YJJ
 * @date:2016/3/14
 * @email:yangjianjun@117go.com
 */
@ActivityScope
@Component(modules = ProductionModule.class)
public interface ProductionComponent extends DataBindingComponent {
}
