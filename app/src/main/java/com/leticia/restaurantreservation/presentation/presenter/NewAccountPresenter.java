package com.leticia.restaurantreservation.presentation.presenter;

import com.leticia.restaurantreservation.domain.model.User;
import com.leticia.restaurantreservation.domain.repository.IUserRepository;
import com.leticia.restaurantreservation.infrastructure.SharedPreferencesManager;
import com.leticia.restaurantreservation.infrastructure.service.RetrofitService;
import com.leticia.restaurantreservation.presentation.mvpview.INewAccountMvpView;

/**
 * Created by leticia on 12/10/17.
 */

public class NewAccountPresenter extends UserPresenter implements INewAccountPresenter {

    private IUserRepository userRepository;
    private INewAccountMvpView mvpView;
    private SharedPreferencesManager sharedPreferencesManager;

    public NewAccountPresenter(INewAccountMvpView mvpView, IUserRepository userRepository) {
        super(mvpView, userRepository);
        this.mvpView = mvpView;
        this.userRepository = userRepository;
        sharedPreferencesManager = new SharedPreferencesManager(mvpView.getContext());
    }

    @Override
    public void createNewUser(String username, String password) {
        if (!username.isEmpty() && !password.isEmpty()) {
            userRepository.createNewUser(new User(username, password)).subscribe(response -> {
                mvpView.showMessage(response.getMessage());
                authenticateUser(username, password);
            }, throwable -> {
                mvpView.showMessage(RetrofitService.convertThrowableToHttpMessage(throwable));
            });
        }
    }

    private void authenticateUser(String username, String password) {
        userRepository.authenticateUser(new User(username, password)).subscribe(authenticateUserResponse -> {
            sharedPreferencesManager.saveUserToken(authenticateUserResponse.getSession().getToken());
            super.getUserDetails(sharedPreferencesManager.getUserToken());
        }, throwable -> {
            mvpView.showMessage(RetrofitService.convertThrowableToHttpMessage(throwable));
        });
    }
}
