package com.leticia.restaurantreservation.presentation.di.component;

import com.leticia.restaurantreservation.presentation.di.module.SplashModule;
import com.leticia.restaurantreservation.presentation.di.module.UserModule;
import com.leticia.restaurantreservation.presentation.presenter.ISplashPresenter;
import com.leticia.restaurantreservation.presentation.view.activity.LoginActivity;
import com.leticia.restaurantreservation.presentation.view.activity.SplashActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by leticia on 12/10/17.
 */
@Singleton
@Component(modules = {SplashModule.class, UserModule.class})
public interface SplashComponent {

    void inject(LoginActivity activity);

    void inject(SplashActivity activity);

    ISplashPresenter getLoginPresenter();
}
