package com.cleanway.apackage.cleanarchpackageway.domain_layer.loginInteractor;

import android.content.Context;

import com.cleanway.apackage.cleanarchpackageway.data_layer.dao.LoginDao;
import com.cleanway.apackage.cleanarchpackageway.data_layer.dao.LoginDaoImp;
import com.cleanway.apackage.cleanarchpackageway.domain_layer.beans.LoginBeanImp;


public class LoginInteractorImp implements LoginInteractorI,LoginDao.OnLoginListenerI{
    private Context context;
    private LoginDao loginDao;
    private OnLoginListenerI listener;
    private LoginBeanImp loginBeanImp;

    public LoginInteractorImp(Context context) {
        this.context=context;
        loginDao=new LoginDaoImp();
        loginBeanImp=new LoginBeanImp();
    }

    @Override
    public void onLoginInteractor(String name, String password, OnLoginListenerI listener) {
        this.listener=listener;
        if (loginBeanImp.checkIsNull(name)){
            listener.onNameError();
        }else if(loginBeanImp.checkIsNull(password)){
            listener.onPasswordError();
        }else {
            loginDao.getUserAuthenticate(name,password,context,this);
        }
    }


    @Override
    public void onLoginDaoSuccess() {
        listener.onSuccess();
    }

    @Override
    public void onLoginDaoFailure(String message) {
        listener.onFailure(message);
    }
}
