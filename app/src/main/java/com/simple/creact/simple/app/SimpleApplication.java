package com.simple.creact.simple.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.appcompat.BuildConfig;

import com.simple.creact.library.framework.SimplePlugins;
import com.simple.creact.library.framework.repository.impl.RepositoryManager;
import com.simple.creact.library.framework.util.Logger;
import com.simple.creact.library.framework.util.RepositoryHook;
import com.simple.creact.simple.app.data.di.common.component.ApplicationComponent;
import com.simple.creact.simple.app.data.di.common.component.DaggerApplicationComponent;
import com.simple.creact.simple.app.data.di.common.module.ApplicationModule;
import com.squareup.picasso.Picasso;


/**
 * @author:YJJ
 * @date:2016/3/14
 * @email:yangjianjun@117go.com
 */
public class SimpleApplication extends Application {
    public static Activity topActivity;
    private static Context context;
    private static ApplicationComponent applicationComponent;
    private static Picasso picasso;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            registerActivityLifecycleCallbacks(new ActivityCallbacks());
        }
        //register repository hook
        SimplePlugins.getInstance().registerRepositoryHook(new SimpleRepositoryHook());
        //init appComponent
        applicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(context))
                .build();
        //picasso debug
        initPicasso();
    }


    public static ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    public static Context getContext() {
        return context;
    }

    public static Picasso getPicasso() {
        return picasso;
    }

    private void initPicasso() {
        picasso = Picasso.with(this);
        picasso.setIndicatorsEnabled(BuildConfig.DEBUG);
        picasso.setLoggingEnabled(BuildConfig.DEBUG);
    }

    private static class ActivityCallbacks implements ActivityLifecycleCallbacks {
        private static String TAG = "ActivityLifecycle";

        @Override
        public void onActivityResumed(Activity activity) {
            Logger.i(TAG, activity.getLocalClassName() + ":Resumed");
            topActivity = activity;
        }

        @Override
        public void onActivityStopped(Activity activity) {
            Logger.i(TAG, activity.getLocalClassName() + ":Stopped");

        }

        @Override
        public void onActivityStarted(Activity activity) {
            Logger.i(TAG, activity.getLocalClassName() + ":Started");
            topActivity = activity;
        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
            Logger.i(TAG, activity.getLocalClassName() + ":SaveInstanceState");
        }

        @Override
        public void onActivityPaused(Activity activity) {
            Logger.i(TAG, activity.getLocalClassName() + ":Paused");
        }

        @Override
        public void onActivityDestroyed(Activity activity) {
            Logger.i(TAG, activity.getLocalClassName() + ":Destroyed");
            //unregister
            RepositoryManager.getInstance().unRegister(activity.getClass());
        }

        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            Logger.i(TAG, activity.getLocalClassName() + ":Created");
            topActivity = activity;
        }
    }
    /**
     * hook for repository
     */
    private static class SimpleRepositoryHook extends RepositoryHook {

        @Override
        public Class getTopActivity() {
            return SimpleApplication.topActivity.getClass();
        }
    }
}
