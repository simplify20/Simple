package com.simple.creact.simple.app.data.di.github.module;


import com.simple.creact.library.framework.datasource.DataFetcher;
import com.simple.creact.library.framework.datasource.DataSource;
import com.simple.creact.library.framework.repository.impl.BaseRepository;
import com.simple.creact.simple.app.biz.github.ContributorsService;
import com.simple.creact.simple.app.biz.github.impl.ContributorsRepository;
import com.simple.creact.simple.app.biz.github.impl.ContributorsServiceImpl;
import com.simple.creact.simple.app.data.datasource.github.ContributorsDataSource;
import com.simple.creact.simple.app.data.di.common.ActivityScope;
import com.simple.creact.simple.app.data.di.common.QualifierConstants;
import com.simple.creact.simple.app.data.entity.github.Contributor;

import java.util.List;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * @author:YJJ
 * @date:2016/3/16
 * @email:yangjianjun@117go.com
 */
@Module(includes = {GitHubApiModule.class})
public class ContributorsModule {
    /**************************************
     * DataFetcher
     **************************************/
    @ActivityScope
    @Provides
    DataFetcher<List<Contributor>> contributionsDataFetcher(ContributorsDataSource.ContributorFetcher contributorFetcher) {
        return contributorFetcher;
    }

    /***************************************
     * DataSource
     ***************************************/
    @ActivityScope
    @Provides
    @Named(QualifierConstants.PROVIDE_CONTRIBUTOR_DATA_SOURCE_RX)
    DataSource contributorDataSource(DataFetcher<List<Contributor>> dataFetcher) {
        return new ContributorsDataSource(dataFetcher);
    }

    /**************************************
     * Repository
     **************************************/
    @ActivityScope
    @Provides
    @Named(QualifierConstants.PROVIDE_CONTRIBUTORS_REPOSITORY)
    BaseRepository contributorRepository(ContributorsRepository repository) {
        return repository;
    }


    /*************************************
     * Service
     *************************************/
    @ActivityScope
    @Provides
    ContributorsService contributorsService(ContributorsServiceImpl contributorsService) {
        return contributorsService;
    }

}
