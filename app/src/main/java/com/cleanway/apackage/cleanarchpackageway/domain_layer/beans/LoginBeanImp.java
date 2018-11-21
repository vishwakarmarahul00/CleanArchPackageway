package com.cleanway.apackage.cleanarchpackageway.domain_layer.beans;


import android.content.Context;

import com.cleanway.apackage.cleanarchpackageway.data_layer.dao.LoginDao;
import com.cleanway.apackage.cleanarchpackageway.data_layer.dao.LoginDaoImp;
import com.cleanway.apackage.cleanarchpackageway.domain_layer.loginInteractor.LoginInteractorImp;

public class LoginBeanImp {
    private LoginDao loginDao;
    private Context context;
    private LoginInteractorImp loginInteractorImp;
    public LoginBeanImp(){
        loginDao=new LoginDaoImp();
    }


    public boolean checkIsNull(String stringChecker) {
        if (stringChecker==null || stringChecker.trim().length()==0){
            return true;
        }
        return false;
    }

}
