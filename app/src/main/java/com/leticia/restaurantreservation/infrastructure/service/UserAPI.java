package com.leticia.restaurantreservation.infrastructure.service;

import com.leticia.restaurantreservation.domain.model.LoginResponse;
import com.leticia.restaurantreservation.domain.model.User;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by leticia on 12/10/17.
 */

public interface UserAPI {

    @POST("/user/create")
    Observable<LoginResponse> createNewUser(@Body User user);
}
