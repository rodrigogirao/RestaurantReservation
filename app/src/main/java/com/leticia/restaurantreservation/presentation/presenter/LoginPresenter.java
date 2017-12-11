package com.leticia.restaurantreservation.presentation.presenter;

import com.leticia.restaurantreservation.infrastructure.service.request.TokenRequest;
import com.leticia.restaurantreservation.domain.repository.IUserRepository;
import com.leticia.restaurantreservation.infrastructure.SharedPreferencesManager;
import com.leticia.restaurantreservation.infrastructure.service.RetrofitService;
import com.leticia.restaurantreservation.presentation.mvpview.LoginMvpView;

/**
 * Created by leticia on 12/10/17.
 */

public class LoginPresenter implements ILoginPresenter {

    private SharedPreferencesManager sharedPreferencesManager;
    private IUserRepository userRepository;
    private LoginMvpView mvpView;

    public LoginPresenter(LoginMvpView mvpView, IUserRepository userRepository) {
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
                    });
        }
    }
}
