package com.simple.creact.simple.app.biz.github.impl;


import com.simple.creact.library.framework.datasource.DataSource;
import com.simple.creact.simple.app.biz.BaseDaggerRepository;
import com.simple.creact.simple.app.data.di.common.QualifierConstants;
import com.simple.creact.simple.app.data.entity.github.Repo;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * @author:YJJ
 * @date:2016/3/15
 * @email:yangjianjun@117go.com
 */
public class DaggerRepoRepository extends BaseDaggerRepository<List<Repo>,List<Repo>> {
    @Inject
    public DaggerRepoRepository(@Named(QualifierConstants.PROVIDE_REPO_DATA_SOURCE_GUAVA)DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public List<Repo> convert(List<Repo> repos) {
        return repos;
    }
}
