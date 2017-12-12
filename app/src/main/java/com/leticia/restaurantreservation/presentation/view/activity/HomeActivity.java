package com.leticia.restaurantreservation.presentation.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.leticia.restaurantreservation.R;
import com.leticia.restaurantreservation.presentation.di.component.DaggerHomeComponent;
import com.leticia.restaurantreservation.presentation.di.module.HomeModule;
import com.leticia.restaurantreservation.presentation.di.module.UserModule;
import com.leticia.restaurantreservation.presentation.mvpview.HomeMvpView;
import com.leticia.restaurantreservation.presentation.presenter.IHomePresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends AppCompatActivity implements HomeMvpView {

    @BindView(R.id.edit_first_name)
    EditText editFirstName;
    @BindView(R.id.edit_last_name)
    EditText editLastName;
    @BindView(R.id.edit_username)
    EditText editUsername;
    @BindView(R.id.edit_password)
    EditText editPassword;
    @BindView(R.id.img_edit_user_info)
    ImageView imgEditUserInfo;
    @Inject
    IHomePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        setupDependenceInjection();

        if (getIntent() != null) {
            String firstName = getIntent().getStringExtra(LoginActivity.FIRST_NAME_KEY);
            String lastName = getIntent().getStringExtra(LoginActivity.LAST_NAME_KEY);
            String username = getIntent().getStringExtra(LoginActivity.USERNAME_KEY);

            editFirstName.setText(firstName);
            editLastName.setText(lastName);
            editUsername.setText(username);
        }
    }

    @OnClick(R.id.img_edit_user_info)
    public void editUserInfo() {
        if (!editFirstName.isEnabled()) {
            editFirstName.setEnabled(true);
            editLastName.setEnabled(true);
            editPassword.setEnabled(true);
            imgEditUserInfo.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_check_circle_red_24dp));
        } else {
            presenter.editUserInfo(editFirstName.getText().toString(), editLastName.getText().toString(),
                    editPassword.getText().toString());
        }
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
    public void goToLoginScreen() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public void finishEditMode() {
        imgEditUserInfo.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_mode_edit_red_24dp));
        editFirstName.setEnabled(false);
        editLastName.setEnabled(false);
        editPassword.setEnabled(false);
    }

    private void setupDependenceInjection() {
        DaggerHomeComponent.builder()
                .homeModule(new HomeModule(this))
                .userModule(new UserModule())
                .build()
                .inject(this);
    }
}
