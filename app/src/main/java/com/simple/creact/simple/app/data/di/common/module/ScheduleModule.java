package com.simple.creact.simple.app.data.di.common.module;


import com.simple.creact.simple.app.data.di.common.ApplicationScope;
import com.simple.creact.simple.app.data.di.common.QualifierConstants;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author:YJJ
 * @date:2016/3/10
 * @email:yangjianjun@117go.com
 */
@Module
public class ScheduleModule {
    @ApplicationScope
    @Provides
    @Named(QualifierConstants.PROVIDE_POST_SCHEDULE)
    Scheduler postScheduler() {
        return AndroidSchedulers.mainThread();
    }

    @ApplicationScope
    @Provides
    @Named(QualifierConstants.PROVIDE_WORK_SCHEDULE)
    Scheduler workScheduler() {
        return Schedulers.newThread();
    }

}
