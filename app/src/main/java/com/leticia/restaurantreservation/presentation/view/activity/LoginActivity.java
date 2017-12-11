package com.leticia.restaurantreservation.presentation.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.leticia.restaurantreservation.R;
import com.leticia.restaurantreservation.presentation.di.component.DaggerLoginComponent;
import com.leticia.restaurantreservation.presentation.di.module.LoginModule;
import com.leticia.restaurantreservation.presentation.di.module.UserModule;
import com.leticia.restaurantreservation.presentation.mvpview.LoginMvpView;
import com.leticia.restaurantreservation.presentation.presenter.ILoginPresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements LoginMvpView {

    public static final String FIRST_NAME_KEY = "first-name-key";
    public static final String LAST_NAME_KEY = "last-name-key";
    public static final String USERNAME_KEY = "username-key";
    @BindView(R.id.txt_create_account)
    TextView txtCreateAccount;
    @Inject
    ILoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        setupDependenceInjection();

        txtCreateAccount.setOnClickListener(view -> {
            Intent intent = new Intent(this, NewAccountActivity.class);
            startActivity(intent);
        });

        loginPresenter.checkIfTokenIsValid();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void goToHomeActivity(String firstName, String lastName, String username) {
        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra(FIRST_NAME_KEY, firstName);
        intent.putExtra(LAST_NAME_KEY, lastName);
        intent.putExtra(USERNAME_KEY, username);
        startActivity(intent);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void setupDependenceInjection() {
        DaggerLoginComponent.builder()
                .loginModule(new LoginModule(this))
                .userModule(new UserModule())
                .build()
                .inject(this);
    }
}
