package com.simple.creact.simple.app.data.di.github.module;


import com.simple.creact.simple.app.data.di.common.ActivityScope;
import com.simple.creact.simple.app.data.di.common.QualifierConstants;
import com.simple.creact.simple.app.data.web.GitHubApi;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author:YJJ
 * @date:2016/3/9
 * @email:yangjianjun@117go.com
 */
@Module
public class GitHubApiModule {
    public static final String API_URL = "https://api.github.com";
    private String baseUrl = API_URL;

    public GitHubApiModule(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public GitHubApiModule() {
    }

    /***************************
     * API
     ***************************/

    @ActivityScope
    @Provides
    @Named(QualifierConstants.PROVIDE_GIT_HUB_API)
    Retrofit retrofit() {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @ActivityScope
    @Provides
    GitHubApi gitHubApi(@Named(QualifierConstants.PROVIDE_GIT_HUB_API) Retrofit retrofit) {
        return retrofit.create(GitHubApi.class);
    }


}
