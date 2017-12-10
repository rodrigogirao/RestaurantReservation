package com.leticia.restaurantreservation.presentation.di.module;

import com.leticia.restaurantreservation.domain.repository.IUserRepository;
import com.leticia.restaurantreservation.presentation.mvpview.INewAccountMvpView;
import com.leticia.restaurantreservation.presentation.presenter.INewAccountPresenter;
import com.leticia.restaurantreservation.presentation.presenter.NewAccountPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by leticia on 12/10/17.
 */
@Module
public class NewAccountModule {

    private INewAccountMvpView mvpView;

    public NewAccountModule(INewAccountMvpView mvpView) {
        this.mvpView = mvpView;
    }

    @Provides
    public INewAccountPresenter provideNewAccountPresenter(IUserRepository userRepository) {
        return new NewAccountPresenter(mvpView, userRepository);
    }
}
