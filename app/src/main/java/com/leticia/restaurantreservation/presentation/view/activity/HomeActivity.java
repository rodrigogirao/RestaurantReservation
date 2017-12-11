package com.leticia.restaurantreservation.presentation.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.leticia.restaurantreservation.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity {

    @BindView(R.id.txt_first_name)
    TextView txtFirstName;
    @BindView(R.id.txt_last_name)
    TextView txtLastName;
    @BindView(R.id.txt_username)
    TextView txtUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        if (getIntent() != null) {
            String firstName = getIntent().getStringExtra(LoginActivity.FIRST_NAME_KEY);
            String lastName = getIntent().getStringExtra(LoginActivity.LAST_NAME_KEY);
            String username = getIntent().getStringExtra(LoginActivity.USERNAME_KEY);

            txtFirstName.setText(firstName);
            txtLastName.setText(lastName);
            txtUsername.setText(username);
        }
    }
}
