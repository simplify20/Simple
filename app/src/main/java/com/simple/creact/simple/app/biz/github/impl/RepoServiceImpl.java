package com.simple.creact.simple.app.biz.github.impl;


import com.simple.creact.library.framework.datasource.impl.RequestParameter;
import com.simple.creact.library.framework.repository.DataCallback;
import com.simple.creact.library.framework.repository.impl.BaseRepository;
import com.simple.creact.simple.app.biz.github.RepoService;
import com.simple.creact.simple.app.data.di.common.QualifierConstants;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * @author:YJJ
 * @date:2016/3/10
 * @email:yangjianjun@117go.com
 */
public class RepoServiceImpl implements RepoService {
    private BaseRepository repository;

    @Inject
    public RepoServiceImpl(@Named(QualifierConstants.PROVIDE_REPO_REPOSITORY_DAGGER) BaseRepository repository) {
        this.repository = repository;
    }

    @Override
    public void getRepos(String owner, DataCallback repoListCallback) {

        RequestParameter extraParams = RequestParameter.newActionParameter(QualifierConstants.PROVIDE_REPO_REPOSITORY);
        repository.setCallback(repoListCallback);
        repository.getData(extraParams, owner);
    }
}
