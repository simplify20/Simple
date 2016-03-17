package com.simple.creact.simple.app.data.web;

import com.simple.creact.simple.app.data.entity.github.Contributor;
import com.simple.creact.simple.app.data.entity.github.Repo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @author:YJJ
 * @date:2016/3/9
 * @email:yangjianjun@117go.com
 */
public interface GitHubApi {
    @GET("/repos/{owner}/{repo}/contributors")
    Call<List<Contributor>> contributors(
            @Path("owner") String owner,
            @Path("repo") String repo);

    @GET("users/{user}/repos")
    Call<List<Repo>> listRepos(@Path("user") String user);
}
