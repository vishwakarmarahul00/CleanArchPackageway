package com.cleanway.apackage.cleanarchpackageway.presenter_layer.features.signup.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.cleanway.apackage.cleanarchpackageway.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class SignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_layout);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_sign_ups)
    public void onSignupDone(View view){
        finish();
    }

}
