package com.ganteng.botak.retrofitrxdagger.base;

import android.app.Application;

import com.ganteng.botak.retrofitrxdagger.Constants;
import com.ganteng.botak.retrofitrxdagger.network.NetworkModule;

/**
 * Created by adit on 4/19/2017.
 */
public class App extends Application {

    private ApplicationComponent component;



    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .networkModule(new NetworkModule(Constants.LOGIN_BASE_URL))
                .build();

    }

    public ApplicationComponent getComponent(){
        return component;
    }
}
