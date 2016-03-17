package com.simple.creact.library.framework.util;

import com.simple.creact.library.framework.IParameter;
import com.simple.creact.library.framework.repository.DataCallback;
import com.simple.creact.library.framework.repository.Repository;
import com.simple.creact.library.framework.repository.impl.RepositoryManager;


/**
 * @author:YJJ
 * @date:2016/3/15
 * @email:yangjianjun@117go.com
 */
public abstract class RepositoryHook {

    public void onRepositoryConstruct(Repository repository) {
        Class viewClass = getTopActivity();
        if (viewClass == null) {
            viewClass = repository.getClass();
        }
        RepositoryManager.getInstance().register(viewClass, repository);
    }

    public void onRepositoryClose(Repository repository) {

    }

    public void onRepositoryGetData(Repository repository, IParameter parameter, String... values) {

    }

    public void onRepositorySetCallback(Repository repository, DataCallback callback) {

    }

    public abstract Class getTopActivity();


}
