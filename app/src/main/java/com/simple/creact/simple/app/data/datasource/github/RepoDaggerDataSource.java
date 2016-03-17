package com.simple.creact.simple.app.data.datasource.github;

import android.support.annotation.NonNull;

import com.simple.creact.library.framework.IParameter;
import com.simple.creact.library.framework.datasource.DataFetcher;
import com.simple.creact.library.framework.datasource.impl.BaseDataFetcher;
import com.simple.creact.library.framework.datasource.impl.RequestParameter;
import com.simple.creact.simple.app.data.datasource.BaseDaggerDataSource;
import com.simple.creact.simple.app.data.di.common.QualifierConstants;
import com.simple.creact.simple.app.data.di.github.component.DaggerRepoProducerComponent;
import com.simple.creact.simple.app.data.di.github.module.GitHubProducer;
import com.simple.creact.simple.app.data.entity.github.Repo;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * @author:YJJ
 * @date:2016/3/14
 * @email:yangjianjun@117go.com
 */
public class RepoDaggerDataSource extends BaseDaggerDataSource<List<Repo>, List<Repo>> {

    @Inject
    public RepoDaggerDataSource(@Named(QualifierConstants.PROVIDE_REPO_DATA_FETCHER_DAGGER) DataFetcher dataFetcher) {
        super(dataFetcher);
    }


    @Override
    public List<Repo> convert(List<Repo> input) {
        return input;
    }


    public static class DaggerRepoFetcher extends BaseDataFetcher<Future<List<Repo>>> {
        private Future<List<Repo>> cache;
        private GitHubProducer gitHubProducer;
        private Executor executor;

        @Inject
        public DaggerRepoFetcher(GitHubProducer gitHubProducer, @Named(QualifierConstants.PROVIDE_SINGLE_THREAD_EXECUTOR) Executor executor) {
            this.executor = executor;
            this.gitHubProducer = gitHubProducer;
        }

        @Override
        public IParameter putValues(@NonNull IParameter<String, String> extra, @NonNull String... values) {
            extra.put("user", values[0]);
            return extra;
        }

        @Override
        public Future<List<Repo>> fetchDataImpl(@NonNull RequestParameter requestParameter) throws Exception {
            gitHubProducer.setParameter(requestParameter);
            Future<List<Repo>> result = DaggerRepoProducerComponent
                    .builder()
                    .gitHubProducer(gitHubProducer)
                    .executor(executor)
                    .build()
                    .getRepo();
            cache = result;
            return cache;
        }

        @Override
        public void close() {
            if (cache != null) {
                cache.cancel(true);
            }
        }
    }
}
