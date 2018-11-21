package com.cleanway.apackage.cleanarchpackageway.presenter_layer.features.login.presenter;

import android.content.Context;

import com.cleanway.apackage.cleanarchpackageway.domain_layer.loginInteractor.LoginInteractorI;
import com.cleanway.apackage.cleanarchpackageway.domain_layer.loginInteractor.LoginInteractorImp;
import com.cleanway.apackage.cleanarchpackageway.presenter_layer.features.Screen;
import com.cleanway.apackage.cleanarchpackageway.presenter_layer.features.login.view.LoginView;

import java.util.UUID;


public class LoginPresenterImp implements LoginPresenter, LoginInteractorI.OnLoginListenerI {
    private UUID uuid;
    private LoginView view;
    private LoginInteractorI model;
    private Context context;

    public LoginPresenterImp(Context loginActivity) {
        this.uuid = UUID.randomUUID();
        this.context=loginActivity;
        view= (LoginView) loginActivity;
        model=new LoginInteractorImp(loginActivity);
    }


    @Override
    public void onLogin(String name, String password) {
        view.onProgressStart();
        model.onLoginInteractor(name,password,this);
    }

    @Override
    public void setScreen(Screen screen) {
        this.view = (LoginView) screen;
    }

    @Override
    public UUID getUuid() {
        return uuid;
    }


    @Override
    public void onSuccess() {
        view.onProgressEnd();
        view.onNavigateActivity();
    }

    @Override
    public void onFailure(String message) {
        view.onProgressEnd();
        view.onMessageShow(message);
    }


    @Override
    public void onNameError() {
        view.onProgressEnd();
        view.onEmailError();
    }


    @Override
    public void onPasswordError() {
        view.onProgressEnd();
        view.onPasswordError();
    }


}
