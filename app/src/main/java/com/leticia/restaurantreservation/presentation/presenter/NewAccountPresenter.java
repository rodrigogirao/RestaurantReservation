package com.leticia.restaurantreservation.presentation.presenter;

import com.leticia.restaurantreservation.domain.model.User;
import com.leticia.restaurantreservation.domain.repository.IUserRepository;
import com.leticia.restaurantreservation.infrastructure.service.RetrofitService;
import com.leticia.restaurantreservation.presentation.mvpview.INewAccountMvpView;

/**
 * Created by leticia on 12/10/17.
 */

public class NewAccountPresenter implements INewAccountPresenter {

    private IUserRepository userRepository;
    private INewAccountMvpView mvpView;

    public NewAccountPresenter(INewAccountMvpView mvpView, IUserRepository userRepository) {
        this.mvpView = mvpView;
        this.userRepository = userRepository;
    }

    @Override
    public void createNewUser(String username, String password) {

        if (!username.isEmpty() && !password.isEmpty()) {
            userRepository.createNewUser(new User(username, password)).subscribe(response -> {
                mvpView.showMessage(response.getMessage());
            }, throwable -> {
                mvpView.showMessage(RetrofitService.convertThrowableToHttpMessage(throwable));
            });
        }
    }
}
