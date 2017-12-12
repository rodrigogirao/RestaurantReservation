package com.leticia.restaurantreservation.presentation.di.component;

import com.leticia.restaurantreservation.presentation.di.module.HomeModule;
import com.leticia.restaurantreservation.presentation.di.module.UserModule;
import com.leticia.restaurantreservation.presentation.presenter.IHomePresenter;
import com.leticia.restaurantreservation.presentation.view.activity.HomeActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by leticia on 12/11/17.
 */
@Singleton
@Component(modules = {HomeModule.class, UserModule.class})
public interface HomeComponent {

    void inject(HomeActivity activity);

    IHomePresenter getHomePresenter();
}
