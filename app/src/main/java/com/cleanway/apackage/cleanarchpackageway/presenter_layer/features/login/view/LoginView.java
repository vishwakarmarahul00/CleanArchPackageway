package com.cleanway.apackage.cleanarchpackageway.presenter_layer.features.login.view;


import com.cleanway.apackage.cleanarchpackageway.presenter_layer.features.Screen;

public interface LoginView extends Screen {
    void onEmailError();
    void onPasswordError();
    void onProgressStart();
    void onProgressEnd();
    void onNavigateActivity();
    void onMessageShow(String message);

}
