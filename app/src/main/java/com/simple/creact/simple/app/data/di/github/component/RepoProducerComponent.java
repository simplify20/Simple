package com.simple.creact.simple.app.data.di.github.component;


import com.google.common.util.concurrent.ListenableFuture;
import com.simple.creact.simple.app.data.di.github.module.GitHubProducer;
import com.simple.creact.simple.app.data.entity.github.Repo;

import java.util.List;

import dagger.producers.ProductionComponent;

/**
 * @author:YJJ
 * @date:2016/3/14
 * @email:yangjianjun@117go.com
 */
@ProductionComponent(modules = GitHubProducer.class)
public interface RepoProducerComponent {

    ListenableFuture<List<Repo>> getRepo();
}
