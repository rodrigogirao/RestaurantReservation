package com.leticia.restaurantreservation.presentation.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import com.leticia.restaurantreservation.R;
import com.leticia.restaurantreservation.presentation.di.component.DaggerNewAccountComponent;
import com.leticia.restaurantreservation.presentation.di.module.NewAccountModule;
import com.leticia.restaurantreservation.presentation.di.module.UserModule;
import com.leticia.restaurantreservation.presentation.mvpview.INewAccountMvpView;
import com.leticia.restaurantreservation.presentation.presenter.INewAccountPresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewAccountActivity extends AppCompatActivity implements INewAccountMvpView {

    @BindView(R.id.edit_username)
    EditText editUsername;
    @BindView(R.id.edit_password)
    EditText editPassword;
    @Inject
    INewAccountPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_account);
        ButterKnife.bind(this);

        setupDependenceInjection();
    }

    @OnClick(R.id.btn_create_or_login)
    public void onButtonCreateAccountClicked() {
        presenter.createNewUser(editUsername.getText().toString(), editPassword.getText().toString());
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void goToLoginActivity() {
    }

    @Override
    public Context getContext() {
        return this;
    }

    private void setupDependenceInjection() {
        DaggerNewAccountComponent.builder()
                .newAccountModule(new NewAccountModule(this))
                .userModule(new UserModule())
                .build()
                .inject(this);
    }
}
