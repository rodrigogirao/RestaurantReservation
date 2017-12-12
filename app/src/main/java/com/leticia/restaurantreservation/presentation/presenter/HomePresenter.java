package com.leticia.restaurantreservation.presentation.presenter;

import com.leticia.restaurantreservation.domain.model.User;
import com.leticia.restaurantreservation.domain.repository.IUserRepository;
import com.leticia.restaurantreservation.infrastructure.SharedPreferencesManager;
import com.leticia.restaurantreservation.infrastructure.service.RetrofitService;
import com.leticia.restaurantreservation.presentation.mvpview.HomeMvpView;

/**
 * Created by leticia on 12/11/17.
 */

public class HomePresenter implements IHomePresenter {

    private HomeMvpView mvpView;
    private final IUserRepository userRepository;
    private SharedPreferencesManager sharedPreferencesManager;

    public HomePresenter(HomeMvpView mvpView, IUserRepository userRepository) {
        this.mvpView = mvpView;
        this.userRepository = userRepository;
        sharedPreferencesManager = new SharedPreferencesManager(mvpView.getContext());
    }

    @Override
    public void editUserInfo(String firstName, String lastName, String password) {
        userRepository.updateUserInfo(createUser(sharedPreferencesManager.getUserToken(),
                firstName, lastName, password)).subscribe(messageResponse -> {
                    mvpView.showMessage(messageResponse.getMessage());
                    mvpView.finishEditMode();
                    if (!password.isEmpty()) {
                        sharedPreferencesManager.saveUserToken("");
                        mvpView.goToLoginScreen();
                    }
        }, throwable -> {
                    mvpView.showMessage(RetrofitService.convertThrowableToHttpMessage(throwable));
                });
    }

    private User createUser(String userToken, String firstName, String lastName, String password) {
        User user = new User();
        user.setToken(userToken);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPassword(password);
        return user;
    }
}
