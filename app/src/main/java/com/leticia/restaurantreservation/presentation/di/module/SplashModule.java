package com.leticia.restaurantreservation.presentation.di.module;

import com.leticia.restaurantreservation.domain.repository.IUserRepository;
import com.leticia.restaurantreservation.presentation.mvpview.SplashMvpView;
import com.leticia.restaurantreservation.presentation.presenter.ISplashPresenter;
import com.leticia.restaurantreservation.presentation.presenter.SplashPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by leticia on 12/10/17.
 */
@Module
public class SplashModule {

    private SplashMvpView mvpView;

    public SplashModule(SplashMvpView mvpView) {
        this.mvpView = mvpView;
    }

    @Provides
    public ISplashPresenter provideLoginPresenter(IUserRepository userRepository) {
        return new SplashPresenter(mvpView, userRepository);
    }
}
