package com.leticia.restaurantreservation.presentation.presenter;

import com.leticia.restaurantreservation.domain.model.User;
import com.leticia.restaurantreservation.domain.repository.IUserRepository;
import com.leticia.restaurantreservation.infrastructure.SharedPreferencesManager;
import com.leticia.restaurantreservation.infrastructure.service.RetrofitService;
import com.leticia.restaurantreservation.presentation.mvpview.LoginMvpView;

/**
 * Created by leticia on 12/10/17.
 */

public class LoginPresenter implements ILoginPresenter {

    private final LoginMvpView mvpView;
    private final IUserRepository userRepository;
    private final SharedPreferencesManager sharedPreferencesManager;

    public LoginPresenter(LoginMvpView mvpView, IUserRepository userRepository) {
        this.mvpView = mvpView;
        this.userRepository = userRepository;
        sharedPreferencesManager = new SharedPreferencesManager(mvpView.getContext());
    }

    @Override
    public void doLogin(String username, String password) {
        if (!username.isEmpty() && !password.isEmpty()) {
            authenticateUser(username, password);
        }
    }

    private void authenticateUser(String username, String password) {
        userRepository.authenticateUser(new User(username, password)).subscribe(authenticateUserResponse -> {
            sharedPreferencesManager.saveUserToken(authenticateUserResponse.getSession().getToken());
            mvpView.goToHomeActivity();
        }, throwable -> {
            mvpView.showMessage(RetrofitService.convertThrowableToHttpMessage(throwable));
        });
    }
}
