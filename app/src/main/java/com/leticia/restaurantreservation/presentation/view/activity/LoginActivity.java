package com.leticia.restaurantreservation.presentation.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.leticia.restaurantreservation.R;
import com.leticia.restaurantreservation.infrastructure.repository.UserRepository;
import com.leticia.restaurantreservation.presentation.mvpview.LoginMvpView;
import com.leticia.restaurantreservation.presentation.presenter.ILoginPresenter;
import com.leticia.restaurantreservation.presentation.presenter.LoginPresenter;

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

        presenter = new LoginPresenter(this, new UserRepository());
    }

    @OnClick(R.id.btn_create_or_login)
    public void login() {
        presenter.doLogin(editUsername.getText().toString(), editPassword.getText().toString());
    }

    private void setupDependenceInjection() {

    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void goToHomeActivity() {
        Intent intent = new Intent(this, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
