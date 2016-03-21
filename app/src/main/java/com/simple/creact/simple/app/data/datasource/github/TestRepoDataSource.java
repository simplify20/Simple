package com.simple.creact.simple.app.data.datasource.github;


import com.google.common.util.concurrent.ListenableFuture;
import com.simple.creact.library.framework.IParameter;
import com.simple.creact.library.framework.annotation.NonNull;
import com.simple.creact.library.framework.datasource.DataFetcher;
import com.simple.creact.library.framework.datasource.impl.BaseDataFetcher;
import com.simple.creact.library.framework.datasource.impl.RequestParameter;
import com.simple.creact.simple.app.data.datasource.BaseDaggerMultiDataSource;
import com.simple.creact.simple.app.data.di.common.QualifierConstants;
import com.simple.creact.simple.app.data.entity.github.Repo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.inject.Inject;
import javax.inject.Named;

import dagger.producers.internal.Producers;

/**
 * @author:YJJ
 * @date:2016/3/16
 * @email:yangjianjun@117go.com
 */
public class TestRepoDataSource extends BaseDaggerMultiDataSource<List<Repo>, List<Repo>> {
    public TestRepoDataSource(DataFetcher<ListenableFuture<List<Repo>>>... dataFetchers) {

        super(dataFetchers);
    }

    @Override
    public List<Repo> convert(List<Repo> input) {
        return input;
    }

    public static class TestFileSystemFetcher extends BaseDataFetcher<Future<List<Repo>>> {
        private Executor executor;

        @Inject
        public TestFileSystemFetcher(@Named(QualifierConstants.PROVIDE_SINGLE_THREAD_EXECUTOR) Executor executor) {
            this.executor = executor;
        }

        @Override
        public IParameter putValues(@NonNull IParameter<String, String> extra, @NonNull String... values) {
            return extra;
        }

        @Override
        public Future<List<Repo>> fetchDataImpl(@NonNull RequestParameter requestParameter) throws Exception {
            return Producers.submitToExecutor(new Callable<List<Repo>>() {
                @Override
                public List<Repo> call() throws Exception {
                    Thread.sleep(1000);
                    List<Repo> result = new ArrayList();
                    for (int i = 0; i < 10; i++) {
                        Repo repo = new Repo();
                        repo.name = "file" + i;
                        repo.stars = i + 20;
                        result.add(repo);
                    }
                    int i = 10 / 0;//test exception
                    return result;
                }
            }, executor);
        }

        @Override
        public void close() {

        }
    }


    public static class TestDBFetcher extends BaseDataFetcher<Future<List<Repo>>> {

        @Inject
        public TestDBFetcher() {
        }

        @Override
        public IParameter putValues(@NonNull IParameter<String, String> extra, @NonNull String... values) {
            return extra;
        }

        @Override
        public Future<List<Repo>> fetchDataImpl(@NonNull RequestParameter requestParameter) throws Exception {
            return Producers.submitToExecutor(new Callable<List<Repo>>() {
                @Override
                public List<Repo> call() throws Exception {
                    Thread.sleep(1000);
                    List<Repo> result = new ArrayList();
                    for (int i = 0; i < 10; i++) {
                        Repo repo = new Repo();
                        repo.name = "db" + i;
                        repo.stars = i + 20;
                        result.add(repo);
                    }
                    return result;
                }
            }, Executors.newSingleThreadExecutor());
        }

        @Override
        public void close() {

        }
    }
}
