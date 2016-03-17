package com.simple.creact.simple.app.data.di.github.module;


import com.simple.creact.library.framework.datasource.DataFetcher;
import com.simple.creact.library.framework.datasource.DataSource;
import com.simple.creact.library.framework.repository.impl.BaseRepository;
import com.simple.creact.simple.app.biz.github.RepoService;
import com.simple.creact.simple.app.biz.github.impl.DaggerRepoRepository;
import com.simple.creact.simple.app.biz.github.impl.RepoRepository;
import com.simple.creact.simple.app.biz.github.impl.RepoServiceImpl;
import com.simple.creact.simple.app.data.datasource.github.RepoDaggerDataSource;
import com.simple.creact.simple.app.data.datasource.github.RepoDataSource;
import com.simple.creact.simple.app.data.datasource.github.TestRepoDataSource;
import com.simple.creact.simple.app.data.di.common.ActivityScope;
import com.simple.creact.simple.app.data.di.common.QualifierConstants;
import com.simple.creact.simple.app.data.di.common.module.ExecutorModule;
import com.simple.creact.simple.app.data.entity.github.Repo;
import com.simple.creact.simple.app.data.web.GitHubApi;

import java.util.List;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * @author:YJJ
 * @date:2016/3/16
 * @email:yangjianjun@117go.com
 */
@Module(includes = {ExecutorModule.class, GitHubApiModule.class})
public class RepoModule {
    /**************************************
     * DataFetcher
     **************************************/
    @ActivityScope
    @Provides
    DataFetcher<List<Repo>> reposDataFetcher(RepoDataSource.RepoFetcher reposFetcher) {
        return reposFetcher;
    }

    /***************************************
     * DataSource
     ***************************************/
    @ActivityScope
    @Provides
    @Named(QualifierConstants.PROVIDE_REPO_DATA_SOURCE_RX)
    DataSource repoDataSource(DataFetcher<List<Repo>> dataFetcher) {
        return new RepoDataSource(dataFetcher);
    }

    /**************************************
     * Repository
     **************************************/
    @ActivityScope
    @Provides
    @Named(QualifierConstants.PROVIDE_REPO_REPOSITORY)
    BaseRepository reposRepository(RepoRepository repository) {
        return repository;
    }

    /**************************************
     * Dagger test
     **************************************/
    @ActivityScope
    @Provides
    GitHubProducer gitHubProducer(GitHubApi gitHubApi) {
        return new GitHubProducer(gitHubApi);
    }

    @ActivityScope
    @Named(QualifierConstants.PROVIDE_REPO_DATA_FETCHER_DAGGER)
    @Provides
    DataFetcher daggerRepoFetcher(RepoDaggerDataSource.DaggerRepoFetcher daggerRepoFetcher) {
        return daggerRepoFetcher;
    }

    @ActivityScope
    @Named(QualifierConstants.PROVIDE_REPO_DATA_FILE_FETCHER_DAGGER)
    @Provides
    DataFetcher daggerRepoFileFetcher(TestRepoDataSource.TestFileSystemFetcher daggerRepoFetcher) {
        return daggerRepoFetcher;
    }

    @ActivityScope
    @Named(QualifierConstants.PROVIDE_REPO_DATA_DB_FETCHER_DAGGER)
    @Provides
    DataFetcher daggerRepoDBFetcher(TestRepoDataSource.TestDBFetcher daggerRepoFetcher) {
        return daggerRepoFetcher;
    }

    @ActivityScope
    @Provides
    @Named(QualifierConstants.PROVIDE_REPO_DATA_SOURCE_GUAVA)
    DataSource daggerRepoDataSource(@Named(QualifierConstants.PROVIDE_REPO_DATA_FILE_FETCHER_DAGGER) DataFetcher fileDataFetcher,
                                    @Named(QualifierConstants.PROVIDE_REPO_DATA_FETCHER_DAGGER) DataFetcher netWorkFetcher,
                                    @Named(QualifierConstants.PROVIDE_REPO_DATA_DB_FETCHER_DAGGER) DataFetcher dbFetcher) {
        return new TestRepoDataSource(netWorkFetcher, fileDataFetcher, dbFetcher);
    }

    @ActivityScope
    @Provides
    @Named(QualifierConstants.PROVIDE_REPO_REPOSITORY_DAGGER)
    BaseRepository daggerRepository(DaggerRepoRepository daggerRepoRepository) {
        return daggerRepoRepository;
    }


    /*************************************
     * Service
     *************************************/
    @ActivityScope
    @Provides
    RepoService repoService(RepoServiceImpl repoService) {
        return repoService;
    }


}
