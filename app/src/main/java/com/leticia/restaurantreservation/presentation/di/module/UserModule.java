package com.leticia.restaurantreservation.presentation.di.module;

import com.leticia.restaurantreservation.domain.repository.IUserRepository;
import com.leticia.restaurantreservation.infrastructure.repository.UserRepository;

import dagger.Module;
import dagger.Provides;

/**
 * Created by leticia on 12/10/17.
 */
@Module
public class UserModule {

    @Provides
    public IUserRepository provideUserRepository() {
        return new UserRepository();
    }
}
