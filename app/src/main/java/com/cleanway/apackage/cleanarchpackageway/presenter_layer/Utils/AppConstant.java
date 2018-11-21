package com.cleanway.apackage.cleanarchpackageway.presenter_layer.Utils;

import com.android.volley.Request;
import com.cleanway.apackage.cleanarchpackageway.model_layer.Apps;

public class AppConstant {
    public static String EMAIL_ID="email_id";
    public static String PASSWORD="password";


    public static String FAILURE="Failure";
    public static final String WEB_CONTENT_TYPE = "application/json;charset=utf-8";
    public static final String TAG_PAP_MYSELF = "pap_myself";
    public static final String TAG_REIMBURSE_MYSELF = "reimburse_myself";
    public static final int TIME_OUT_SECONDS = 5000; //30 seconds
    public static final int TIME_OUT_SECONDS1 = 5000; // 5 min
    public static final String TAG_CLAIM_UPLOAD = "upload_doc";


    public static final int POST_METHOD = Request.Method.POST;
    public static final String LOGIN_NAME="unique_key";
    public static final String LOGIN_PASSWORD="password";
    public static final String TAG_LOGIN="login";

    public static final int GET_METHOD = Request.Method.GET;
    public static final String TAG_HOME="home";

    public static Apps selectedApp=new Apps();




}
