package com.leticia.restaurantreservation.presentation.view.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.leticia.restaurantreservation.presentation.di.component.DaggerSplashComponent;
import com.leticia.restaurantreservation.presentation.di.module.SplashModule;
import com.leticia.restaurantreservation.presentation.di.module.UserModule;
import com.leticia.restaurantreservation.presentation.mvpview.SplashMvpView;
import com.leticia.restaurantreservation.presentation.presenter.ISplashPresenter;

import javax.inject.Inject;

public class SplashActivity extends AppCompatActivity implements SplashMvpView {

    @Inject
    ISplashPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupDependenceInjection();

        presenter.checkIfTokenIsValid();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void goToLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private void setupDependenceInjection() {
        DaggerSplashComponent.builder()
                .splashModule(new SplashModule(this))
                .userModule(new UserModule())
                .build()
                .inject(this);
    }
}
