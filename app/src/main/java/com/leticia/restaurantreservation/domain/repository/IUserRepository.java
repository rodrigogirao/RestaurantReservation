package com.leticia.restaurantreservation.domain.repository;

import com.leticia.restaurantreservation.infrastructure.service.response.AuthenticateUserResponse;
import com.leticia.restaurantreservation.infrastructure.service.response.LoginResponse;
import com.leticia.restaurantreservation.infrastructure.service.request.TokenRequest;
import com.leticia.restaurantreservation.domain.model.User;
import com.leticia.restaurantreservation.infrastructure.service.response.MessageResponse;
import com.leticia.restaurantreservation.infrastructure.service.response.UserDetailsResponse;

import io.reactivex.Observable;

/**
 * Created by leticia on 12/10/17.
 */

public interface IUserRepository {
    Observable<LoginResponse> createNewUser(User user);

    Observable<AuthenticateUserResponse> authenticateUser(User user);

    Observable<UserDetailsResponse> retrieveUserInformation(TokenRequest tokenRequest);

    Observable<MessageResponse> updateUserInfo(User user);
}
