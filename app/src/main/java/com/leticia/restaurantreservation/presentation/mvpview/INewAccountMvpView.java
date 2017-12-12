package com.leticia.restaurantreservation.presentation.mvpview;

import android.content.Context;

/**
 * Created by leticia on 12/10/17.
 */

public interface INewAccountMvpView extends IUserMvpView {
    void showMessage(String message);

    Context getContext();
}
