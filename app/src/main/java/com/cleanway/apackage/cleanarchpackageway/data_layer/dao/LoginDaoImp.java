package com.cleanway.apackage.cleanarchpackageway.data_layer.dao;


import android.content.Context;

import com.cleanway.apackage.cleanarchpackageway.R;
import com.cleanway.apackage.cleanarchpackageway.domain_layer.loginInteractor.LoginInteractorImp;
import com.cleanway.apackage.cleanarchpackageway.model_layer.User;
import com.cleanway.apackage.cleanarchpackageway.data_layer.net.VollyRequestHelper;
import com.cleanway.apackage.cleanarchpackageway.data_layer.net.VollyRequestInterface;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import static com.cleanway.apackage.cleanarchpackageway.presenter_layer.Utils.AppConfig.LOGIN_URL;
import static com.cleanway.apackage.cleanarchpackageway.presenter_layer.Utils.AppConstant.LOGIN_NAME;
import static com.cleanway.apackage.cleanarchpackageway.presenter_layer.Utils.AppConstant.LOGIN_PASSWORD;
import static com.cleanway.apackage.cleanarchpackageway.presenter_layer.Utils.AppConstant.POST_METHOD;
import static com.cleanway.apackage.cleanarchpackageway.presenter_layer.Utils.AppConstant.TAG_LOGIN;

public class LoginDaoImp implements LoginDao,VollyRequestInterface {
    private LoginInteractorImp loginBeanImp;
    private Context mContext;
    @Override
    public void getUserAuthenticate(String name, String password, Context mContext, LoginInteractorImp loginBeanImp) {
        this.loginBeanImp=loginBeanImp;
        this.mContext=mContext;
        JSONObject js = new JSONObject();
        try {
            js.put(LOGIN_NAME, name);
            js.put(LOGIN_PASSWORD, password);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        VollyRequestHelper vollyRequestHelper = new VollyRequestHelper(this, mContext);
        vollyRequestHelper.makeJsonObjReq(POST_METHOD, LOGIN_URL, js, TAG_LOGIN);
    }

    @Override
    public void vollyResponse(String response, String tag) {
        User gson=new Gson().fromJson(response,User.class);
        if (gson.getSuccess()) {
            loginBeanImp.onLoginDaoSuccess();
        }else {
            loginBeanImp.onLoginDaoFailure(mContext.getString(R.string.something_went_wrong));
        }
    }

    @Override
    public void vollyErrorResponse(String error, String tag) {
        loginBeanImp.onLoginDaoFailure(error);
    }
}
