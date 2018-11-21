package com.cleanway.apackage.cleanarchpackageway.presenter_layer.features.login.presenter;


import com.cleanway.apackage.cleanarchpackageway.presenter_layer.features.Presenter;

public interface LoginPresenter extends Presenter {
    void onLogin(String name, String password);
}
