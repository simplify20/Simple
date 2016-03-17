package com.simple.creact.simple.app.data.di.github.component;


import com.simple.creact.simple.app.biz.github.RepoService;
import com.simple.creact.simple.app.data.di.common.ActivityScope;
import com.simple.creact.simple.app.data.di.common.component.ApplicationComponent;
import com.simple.creact.simple.app.data.di.github.module.RepoModule;
import com.simple.creact.simple.app.presentation.view.github.SearchRepoActivity;

import dagger.Component;

/**
 * @author:YJJ
 * @date:2016/3/16
 * @email:yangjianjun@117go.com
 */
@ActivityScope
@Component(dependencies = ApplicationComponent.class, modules = RepoModule.class)
public interface RepoComponent {
    void inject(SearchRepoActivity searchRepoActivity);

    RepoService getRepoService();
}
