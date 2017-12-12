package com.leticia.restaurantreservation.presentation.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
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
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements LoginMvpView {

    public static final String FIRST_NAME_KEY = "first-name-key";
    public static final String LAST_NAME_KEY = "last-name-key";
    public static final String USERNAME_KEY = "username-key";
    @BindView(R.id.edit_username)
    TextView editUsername;
    @BindView(R.id.edit_password)
    TextView editPassword;
    @BindView(R.id.txt_create_account)
    TextView txtCreateAccount;
    @BindView(R.id.btn_create_or_login)
    Button btnLogin;
    @Inject
    ILoginPresenter presenter;

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
        btnLogin.setText(R.string.login);
    }

    @OnClick(R.id.btn_create_or_login)
    public void login() {
        presenter.doLogin(editUsername.getText().toString(), editPassword.getText().toString());
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
    }

    private void setupDependenceInjection() {
        DaggerLoginComponent.builder()
                .loginModule(new LoginModule(this))
                .userModule(new UserModule())
                .build()
                .inject(this);
    }
}
