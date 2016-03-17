package com.simple.creact.library.framework.repository.impl;


import com.simple.creact.library.framework.IParameter;
import com.simple.creact.library.framework.SimplePlugins;
import com.simple.creact.library.framework.repository.DataCallback;
import com.simple.creact.library.framework.repository.Repository;
import com.simple.creact.library.framework.util.RepositoryHook;

/**
 * Repository的骨架实现，参数定义同Repository
 *
 * @author:YJJ
 * @date:2016/3/10
 * @email:yangjianjun@117go.com
 */
public abstract class BaseRepository<C, S> implements Repository<C, S> {

    protected DataCallback<C> callback;
    private RepositoryHook repositoryHook = SimplePlugins.getInstance().getRepositoryHook();

    public BaseRepository() {
        repositoryHook.onRepositoryConstruct(this);
    }

    @Override
    public void setCallback(DataCallback<C> callback) {
        this.callback = callback;
        repositoryHook.onRepositorySetCallback(this, this.callback);
    }

    @Override
    public void close() {
        repositoryHook.onRepositoryClose(this);
    }

    @Override
    public S getData(IParameter extra, String... values) {
        repositoryHook.onRepositoryGetData(this, extra, values);
        return null;
    }

}
