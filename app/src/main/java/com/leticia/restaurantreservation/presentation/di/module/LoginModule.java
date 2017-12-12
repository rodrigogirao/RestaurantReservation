package com.leticia.restaurantreservation.presentation.di.module;

import com.leticia.restaurantreservation.domain.repository.IUserRepository;
import com.leticia.restaurantreservation.presentation.mvpview.LoginMvpView;
import com.leticia.restaurantreservation.presentation.presenter.ILoginPresenter;
import com.leticia.restaurantreservation.presentation.presenter.LoginPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by leticia on 12/11/17.
 */
@Module
public class LoginModule {

    private LoginMvpView mvpView;

    public LoginModule(LoginMvpView mvpView) {
        this.mvpView = mvpView;
    }

    @Provides
    public ILoginPresenter provideLoginPresenter(IUserRepository userRepository) {
        return new LoginPresenter(mvpView, userRepository);
    }
}
