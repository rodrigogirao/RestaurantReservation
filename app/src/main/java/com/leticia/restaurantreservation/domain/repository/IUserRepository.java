package com.leticia.restaurantreservation.domain.repository;

import com.leticia.restaurantreservation.domain.model.LoginResponse;
import com.leticia.restaurantreservation.domain.model.User;

import io.reactivex.Observable;

/**
 * Created by leticia on 12/10/17.
 */

public interface IUserRepository {
    Observable<LoginResponse> createNewUser(User user);
}
