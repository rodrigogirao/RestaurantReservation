package com.leticia.restaurantreservation.infrastructure;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by leticia on 12/3/17.
 */

public class SharedPreferencesManager {

    private static final String USER_TOKEN_KEY = "user-token-key";
    private SharedPreferences sharedPreferences;

    public SharedPreferencesManager(Context context) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public void saveUserToken(String token) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USER_TOKEN_KEY, token);
        editor.apply();
    }

    public String getUserToken(){
        return sharedPreferences.getString(USER_TOKEN_KEY, "");
    }
}
