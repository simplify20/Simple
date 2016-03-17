package com.simple.creact.library.framework.repository.impl;


import com.simple.creact.library.framework.repository.Repository;
import com.simple.creact.library.framework.util.Logger;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Manage Repositories' lifeCycle
 *
 * @author:YJJ
 * @date:2016/3/15
 * @email:yangjianjun@117go.com
 */
public class RepositoryManager {
    private HashMap<Class, Set<Repository>> repositoriesEachView = new HashMap<>();
    private static String TAG = "RepositoryManager";
    private final static RepositoryManager INSTANCE = new RepositoryManager();

    public static RepositoryManager getInstance() {
        return INSTANCE;
    }

    public void register(Class viewClass, Repository repository) {
        Logger.i(TAG, ":register,viewClass:" + viewClass + " repository:" + repository.getClass().getName());
        if (viewClass == null)
            throw new NullPointerException("viewClass can not be null");
        if (repository == null)
            throw new NullPointerException("repository can not be null");
        Set<Repository> repositories = repositoriesEachView.get(viewClass);
        if (repositories == null) {
            repositories = new HashSet<>();
        }
        repositories.add(repository);
    }

    public void register(Class viewClass, Set<Repository> repositoryList) {
        Logger.i(TAG, ":register,viewClass:" + viewClass);
        if (viewClass == null)
            throw new NullPointerException("viewClass can not be null");
        if (repositoryList == null)
            throw new NullPointerException("repositoryList can not be null");
        Set<Repository> repositories = repositoriesEachView.get(viewClass);
        if (repositories == null) {
            repositories = new HashSet<>();
        }
        repositories.addAll(repositoryList);
    }


    public void unRegister(Class viewClass) {
        Logger.i(TAG, ":unRegister,viewClass:" + viewClass);
        if (viewClass == null)
            throw new NullPointerException("viewClass can not be null");
        Set<Repository> repositories = repositoriesEachView.get(viewClass);
        if (repositories != null) {
            for (Iterator<Repository> i = repositories.iterator(); i.hasNext(); ) {
                Repository repository = i.next();
                repository.close();
            }
            repositoriesEachView.remove(viewClass);
        }
    }
}
