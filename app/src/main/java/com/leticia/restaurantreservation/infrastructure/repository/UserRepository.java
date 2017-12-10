package com.leticia.restaurantreservation.infrastructure.repository;

import com.leticia.restaurantreservation.domain.model.LoginResponse;
import com.leticia.restaurantreservation.domain.model.User;
import com.leticia.restaurantreservation.domain.repository.IUserRepository;
import com.leticia.restaurantreservation.infrastructure.service.RetrofitService;
import com.leticia.restaurantreservation.infrastructure.service.UserAPI;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * Created by leticia on 12/10/17.
 */

public class UserRepository implements IUserRepository {

    @Override
    public Observable<LoginResponse> createNewUser(User user) {
        Retrofit retrofit = RetrofitService.getRetrofit();
        UserAPI userAPI = retrofit.create(UserAPI.class);
        return userAPI.createNewUser(user).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
}
