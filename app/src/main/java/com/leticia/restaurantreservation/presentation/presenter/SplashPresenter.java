package com.leticia.restaurantreservation.presentation.presenter;

import com.leticia.restaurantreservation.domain.repository.IUserRepository;
import com.leticia.restaurantreservation.infrastructure.SharedPreferencesManager;
import com.leticia.restaurantreservation.presentation.mvpview.SplashMvpView;

/**
 * Created by leticia on 12/10/17.
 */

public class SplashPresenter extends UserPresenter implements ISplashPresenter {

    private SharedPreferencesManager sharedPreferencesManager;
    private IUserRepository userRepository;
    private SplashMvpView mvpView;

    public SplashPresenter(SplashMvpView mvpView, IUserRepository userRepository) {
        super(mvpView, userRepository);
        this.mvpView = mvpView;
        this.userRepository = userRepository;
        sharedPreferencesManager = new SharedPreferencesManager(mvpView.getContext());
    }

    @Override
    public void checkIfTokenIsValid() {
        if (sharedPreferencesManager.getUserToken() != null && !sharedPreferencesManager.getUserToken().isEmpty()) {
            super.getUserDetails(sharedPreferencesManager.getUserToken());
        } else {
            mvpView.goToLoginActivity();
        }
    }
}
