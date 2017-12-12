package com.leticia.restaurantreservation.presentation.mvpview;

import android.content.Context;

/**
 * Created by leticia on 12/11/17.
 */

public interface HomeMvpView {
    Context getContext();

    void showMessage(String message);

    void goToLoginScreen();

    void finishEditMode();
}
