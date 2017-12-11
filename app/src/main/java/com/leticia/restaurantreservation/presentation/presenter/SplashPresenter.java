package com.leticia.restaurantreservation.presentation.presenter;

import com.leticia.restaurantreservation.infrastructure.service.request.TokenRequest;
import com.leticia.restaurantreservation.domain.repository.IUserRepository;
import com.leticia.restaurantreservation.infrastructure.SharedPreferencesManager;
import com.leticia.restaurantreservation.infrastructure.service.RetrofitService;
import com.leticia.restaurantreservation.presentation.mvpview.SplashMvpView;

/**
 * Created by leticia on 12/10/17.
 */

public class SplashPresenter implements ISplashPresenter {

    private SharedPreferencesManager sharedPreferencesManager;
    private IUserRepository userRepository;
    private SplashMvpView mvpView;

    public SplashPresenter(SplashMvpView mvpView, IUserRepository userRepository) {
        this.mvpView = mvpView;
        this.userRepository = userRepository;
        sharedPreferencesManager = new SharedPreferencesManager(mvpView.getContext());
    }

    @Override
    public void checkIfTokenIsValid() {
        if (sharedPreferencesManager.getUserToken() != null && !sharedPreferencesManager.getUserToken().isEmpty()) {
            userRepository.retrieveUserInformation(new TokenRequest(sharedPreferencesManager.getUserToken()))
                    .subscribe(userDetailsResponse -> {
                        mvpView.goToHomeActivity(userDetailsResponse.getUser().getFirstName(),
                                userDetailsResponse.getUser().getLastName(),
                                userDetailsResponse.getUser().getUsername());
                    }, throwable -> {
                        mvpView.showMessage(RetrofitService.convertThrowableToHttpMessage(throwable));
                        if (RetrofitService.isHttp500Error(throwable)) {
                            mvpView.goToLoginActivity();
                        }
                    });
        } else {
            mvpView.goToLoginActivity();
        }
    }
}
