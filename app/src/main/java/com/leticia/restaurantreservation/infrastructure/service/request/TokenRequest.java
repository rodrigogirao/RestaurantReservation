package com.leticia.restaurantreservation.infrastructure.service.request;

/**
 * Created by leticia on 12/10/17.
 */

public class TokenRequest {

    private String token;

    public TokenRequest(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
