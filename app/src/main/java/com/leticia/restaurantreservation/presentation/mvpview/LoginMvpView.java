package com.leticia.restaurantreservation.presentation.mvpview;

import android.content.Context;

/**
 * Created by leticia on 12/10/17.
 */

public interface LoginMvpView {
    Context getContext();

    void goToHomeActivity(String firstName, String lastName, String username);

    void showMessage(String message);
}
