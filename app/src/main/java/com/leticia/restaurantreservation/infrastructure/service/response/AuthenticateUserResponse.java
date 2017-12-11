package com.leticia.restaurantreservation.infrastructure.service.response;

import com.leticia.restaurantreservation.infrastructure.service.response.SessionResponse;

/**
 * Created by leticia on 12/10/17.
 */

public class AuthenticateUserResponse {

    private SessionResponse session;

    public SessionResponse getSession() {
        return session;
    }

    public void setSession(SessionResponse session) {
        this.session = session;
    }
}
