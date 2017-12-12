package com.leticia.restaurantreservation.presentation;

import android.content.Context;
import android.content.Intent;

import com.leticia.restaurantreservation.presentation.view.activity.HomeActivity;

import static com.leticia.restaurantreservation.presentation.view.activity.LoginActivity.FIRST_NAME_KEY;
import static com.leticia.restaurantreservation.presentation.view.activity.LoginActivity.LAST_NAME_KEY;
import static com.leticia.restaurantreservation.presentation.view.activity.LoginActivity.USERNAME_KEY;

/**
 * Created by leticia on 12/11/17.
 */

public class ActivityHelper {

    public static void goToHomeActivity(Context context, String firstName, String lastName,
                                        String username) {
        Intent intent = new Intent(context, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra(FIRST_NAME_KEY, firstName);
        intent.putExtra(LAST_NAME_KEY, lastName);
        intent.putExtra(USERNAME_KEY, username);
        context.startActivity(intent);
    }
}
