package com.simple.creact.simple.app.data.datasource.github;

import android.support.annotation.NonNull;

import com.simple.creact.library.framework.IParameter;
import com.simple.creact.simple.app.data.datasource.RetrofitDataFetcher;
import com.simple.creact.simple.app.data.web.GitHubApi;


/**
 * @author:YJJ
 * @date:2016/3/10
 * @email:yangjianjun@117go.com
 */
public abstract class GitHubDataFetcher<R> extends RetrofitDataFetcher<R> {


    protected GitHubApi gitHubApi;

    public GitHubDataFetcher(GitHubApi gitHubApi) {
        this.gitHubApi = gitHubApi;
    }

    /**
     * default impl of putValues,do nothing
     *
     * @param parameter
     * @param values
     */
    @Override
    public IParameter putValues(@NonNull IParameter<String,String> parameter, @NonNull String... values) {
        return parameter;
    }
}