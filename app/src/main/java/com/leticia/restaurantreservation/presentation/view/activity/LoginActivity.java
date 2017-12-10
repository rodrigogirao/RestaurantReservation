package com.leticia.restaurantreservation.presentation.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.leticia.restaurantreservation.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.txt_create_account)
    TextView txtCreateAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        txtCreateAccount.setOnClickListener(view -> {
            Intent intent = new Intent(this, NewAccountActivity.class);
            startActivity(intent);
        });
    }
}
