package com.simple.creact.simple.app.data.di.github.component;


import com.simple.creact.simple.app.biz.github.ContributorsService;
import com.simple.creact.simple.app.data.di.common.ActivityScope;
import com.simple.creact.simple.app.data.di.common.component.ApplicationComponent;
import com.simple.creact.simple.app.data.di.github.module.ContributorsModule;

import dagger.Component;

/**
 * @author:YJJ
 * @date:2016/3/16
 * @email:yangjianjun@117go.com
 */
@ActivityScope
@Component(dependencies = ApplicationComponent.class,modules = ContributorsModule.class)
public interface ContributorComponent {
    ContributorsService getContributorsService();
}
