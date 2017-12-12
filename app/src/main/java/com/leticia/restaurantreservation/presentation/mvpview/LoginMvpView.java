package com.leticia.restaurantreservation.presentation.mvpview;

import android.content.Context;

/**
 * Created by leticia on 12/10/17.
 */

public interface LoginMvpView extends IUserMvpView {
    Context getContext();

    void showMessage(String message);
}
