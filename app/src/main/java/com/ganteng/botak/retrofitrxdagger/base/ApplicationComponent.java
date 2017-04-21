package com.ganteng.botak.retrofitrxdagger.base;

import com.ganteng.botak.retrofitrxdagger.login.LoginActivity;
import com.ganteng.botak.retrofitrxdagger.network.NetworkModule;

import dagger.Component;

import javax.inject.Singleton;

/**
 * Created by adit on 4/19/2017.
 */

@Singleton
@Component(modules = {ApplicationModule.class, NetworkModule.class})
public interface ApplicationComponent {

    void inject(LoginActivity activity);

}
