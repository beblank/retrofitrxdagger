package com.ganteng.botak.retrofitrxdagger.base;


import android.app.Application;
import android.content.Context;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

/**
 * Created by adit on 4/19/2017.
 */

@Module
public class ApplicationModule {

    private Application application;

    public ApplicationModule(Application application){
        this.application = application;
    }

    @Provides
    @Singleton
    public Context provideContext(){
        return application;
    }

}
