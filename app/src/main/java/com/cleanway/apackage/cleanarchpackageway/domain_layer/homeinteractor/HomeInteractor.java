package com.cleanway.apackage.cleanarchpackageway.domain_layer.homeinteractor;

import android.content.Context;


import com.cleanway.apackage.cleanarchpackageway.model_layer.Apps;

import java.util.List;


public interface HomeInteractor {
    interface onResponseListener{
        void onResponse(List<Apps> arrData);
        void onError(String exception);
    }
    void getAppsDataFromServer(final onResponseListener onResponseListener, final Context mContext);
}
