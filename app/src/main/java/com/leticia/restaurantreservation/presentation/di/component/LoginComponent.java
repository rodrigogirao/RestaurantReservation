package com.leticia.restaurantreservation.presentation.di.component;

import com.leticia.restaurantreservation.presentation.di.module.LoginModule;
import com.leticia.restaurantreservation.presentation.di.module.UserModule;
import com.leticia.restaurantreservation.presentation.presenter.ILoginPresenter;
import com.leticia.restaurantreservation.presentation.view.activity.LoginActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by leticia on 12/11/17.
 */
@Singleton
@Component(modules = {LoginModule.class, UserModule.class})
public interface LoginComponent {

    void inject(LoginActivity activity);

    ILoginPresenter getLoginPresenter();
}
