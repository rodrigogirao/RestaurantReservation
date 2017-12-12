package com.leticia.restaurantreservation.presentation.presenter;

import com.leticia.restaurantreservation.domain.repository.IUserRepository;
import com.leticia.restaurantreservation.infrastructure.service.RetrofitService;
import com.leticia.restaurantreservation.infrastructure.service.request.TokenRequest;
import com.leticia.restaurantreservation.presentation.ActivityHelper;
import com.leticia.restaurantreservation.presentation.mvpview.IUserMvpView;

/**
 * Created by leticia on 12/11/17.
 */

class UserPresenter {

    private final IUserMvpView mvpView;
    private final IUserRepository userRepository;

    UserPresenter(IUserMvpView mvpView, IUserRepository userRepository) {
        this.mvpView = mvpView;
        this.userRepository = userRepository;
    }

    void getUserDetails(String token) {
        userRepository.retrieveUserInformation(new TokenRequest(token))
                .subscribe(userDetailsResponse -> {
                    ActivityHelper.goToHomeActivity(mvpView.getContext(),
                            userDetailsResponse.getUser().getFirstName(),
                            userDetailsResponse.getUser().getLastName(),
                            userDetailsResponse.getUser().getUsername());
                }, throwable -> {
                    mvpView.showMessage(RetrofitService.convertThrowableToHttpMessage(throwable));
                    if (RetrofitService.isHttp500Error(throwable)) {
                        mvpView.goToLoginActivity();
                    }
                });
    }
}
