package com.leticia.restaurantreservation.infrastructure.service.response;

import com.leticia.restaurantreservation.domain.model.User;

/**
 * Created by leticia on 12/10/17.
 */

public class UserDetailsResponse {

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
