package com.leticia.restaurantreservation.presentation.di.component;

import com.leticia.restaurantreservation.presentation.di.module.NewAccountModule;
import com.leticia.restaurantreservation.presentation.di.module.UserModule;
import com.leticia.restaurantreservation.presentation.presenter.INewAccountPresenter;
import com.leticia.restaurantreservation.presentation.view.activity.NewAccountActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by leticia on 12/10/17.
 */
@Singleton
@Component(modules = {NewAccountModule.class, UserModule.class})
public interface NewAccountComponent {

    void inject(NewAccountActivity accountActivity);

    INewAccountPresenter getNewAccountPresenter();
}
