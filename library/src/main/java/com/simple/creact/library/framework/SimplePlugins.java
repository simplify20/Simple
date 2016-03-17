package com.simple.creact.library.framework;


import com.simple.creact.library.framework.util.RepositoryHook;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author:YJJ
 * @date:2016/3/16
 * @email:yangjianjun@117go.com
 */
public class SimplePlugins {

    private static SimplePlugins INSTANCE = new SimplePlugins();
    private static AtomicReference<RepositoryHook> repositoryHookAtomicReference = new AtomicReference<>();
    static final RepositoryHook DEFAULT_REPOSITORY_HOOK = new RepositoryHook() {
        @Override
        public Class getTopActivity() {
            return null;
        }
    };

    SimplePlugins() {
    }

    public static SimplePlugins getInstance() {
        return INSTANCE;
    }

    public void registerRepositoryHook(RepositoryHook repositoryHook) {
        if (!repositoryHookAtomicReference.compareAndSet(null, repositoryHook)) {
            throw new IllegalStateException("Another strategy was already registered: " + repositoryHookAtomicReference.get());
        }
    }

    public RepositoryHook getRepositoryHook() {
        if (repositoryHookAtomicReference.get() == null) {
            repositoryHookAtomicReference.compareAndSet(null, DEFAULT_REPOSITORY_HOOK);
        }
        return repositoryHookAtomicReference.get();
    }


}
