package com.cleanway.apackage.cleanarchpackageway.domain_layer.loginInteractor;


public interface LoginInteractorI {

    public interface OnLoginListenerI{
        void onSuccess();
        void onFailure(String message);
        void onNameError();
        void onPasswordError();
    }
    void onLoginInteractor(String name, String password, OnLoginListenerI listener);
}
