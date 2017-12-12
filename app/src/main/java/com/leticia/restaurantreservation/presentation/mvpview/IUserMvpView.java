package com.leticia.restaurantreservation.presentation.mvpview;

import android.content.Context;

/**
 * Created by leticia on 12/11/17.
 */

public interface IUserMvpView {
    Context getContext();

    void showMessage(String message);

    void goToLoginActivity();
}
