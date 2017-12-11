package com.leticia.restaurantreservation.infrastructure.service;

import com.leticia.restaurantreservation.infrastructure.service.response.AuthenticateUserResponse;
import com.leticia.restaurantreservation.infrastructure.service.response.LoginResponse;
import com.leticia.restaurantreservation.infrastructure.service.request.TokenRequest;
import com.leticia.restaurantreservation.domain.model.User;
import com.leticia.restaurantreservation.infrastructure.service.response.UserDetailsResponse;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by leticia on 12/10/17.
 */

public interface UserAPI {

    @POST("/user/create")
    Observable<LoginResponse> createNewUser(@Body User user);

    @POST("/auth/authUser")
    Observable<AuthenticateUserResponse> authenticateUser(@Body User user);

    @POST("/user/getDetails")
    Observable<UserDetailsResponse> retrieveUserInformation(@Body TokenRequest tokenRequest);
}
