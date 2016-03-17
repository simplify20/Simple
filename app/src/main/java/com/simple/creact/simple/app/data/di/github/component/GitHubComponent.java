package com.simple.creact.simple.app.data.di.github.component;


import com.simple.creact.simple.app.biz.github.ContributorsService;
import com.simple.creact.simple.app.biz.github.RepoService;
import com.simple.creact.simple.app.data.di.common.ActivityScope;
import com.simple.creact.simple.app.data.di.common.component.ApplicationComponent;
import com.simple.creact.simple.app.data.di.github.module.ContributorsModule;
import com.simple.creact.simple.app.data.di.github.module.RepoModule;

import dagger.Component;

/**
 * May be unnecessary
 *
 * @author:YJJ
 * @date:2016/3/9
 * @email:yangjianjun@117go.com
 */
@ActivityScope
@Component(dependencies = {ApplicationComponent.class}, modules = {ContributorsModule.class, RepoModule.class})
public interface GitHubComponent {
    /**
     * if use interface type,must have a provide method
     *
     * @return
     */
    ContributorsService getContributorsService();

    RepoService getRepoService();
}
