package com.cleanway.apackage.cleanarchpackageway.data_layer.dao;


import android.content.Context;

import com.cleanway.apackage.cleanarchpackageway.domain_layer.loginInteractor.LoginInteractorImp;

public interface LoginDao {
    interface OnLoginListenerI{
        void onLoginDaoSuccess();
        void onLoginDaoFailure(String message);
    }
    void getUserAuthenticate(String name, String password, Context context, LoginInteractorImp loginBeanImp);
}
