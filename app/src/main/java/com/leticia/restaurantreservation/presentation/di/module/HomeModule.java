package com.leticia.restaurantreservation.presentation.di.module;

import com.leticia.restaurantreservation.domain.repository.IUserRepository;
import com.leticia.restaurantreservation.presentation.mvpview.HomeMvpView;
import com.leticia.restaurantreservation.presentation.presenter.HomePresenter;
import com.leticia.restaurantreservation.presentation.presenter.IHomePresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by leticia on 12/11/17.
 */
@Module
public class HomeModule {

    private HomeMvpView mvpView;

    public HomeModule(HomeMvpView mvpView) {
        this.mvpView = mvpView;
    }

    @Provides
    public IHomePresenter provideHomePresenter(IUserRepository userRepository) {
        return new HomePresenter(mvpView, userRepository);
    }
}
