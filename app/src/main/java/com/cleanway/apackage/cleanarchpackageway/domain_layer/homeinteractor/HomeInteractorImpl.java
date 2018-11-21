package com.cleanway.apackage.cleanarchpackageway.domain_layer.homeinteractor;

import android.content.Context;

import com.cleanway.apackage.cleanarchpackageway.R;
import com.cleanway.apackage.cleanarchpackageway.data_layer.net.VollyRequestHelper;
import com.cleanway.apackage.cleanarchpackageway.data_layer.net.VollyRequestInterface;
import com.cleanway.apackage.cleanarchpackageway.model_layer.AppsRes;
import com.cleanway.apackage.cleanarchpackageway.presenter_layer.Utils.AppConfig;
import com.cleanway.apackage.cleanarchpackageway.presenter_layer.Utils.AppConstant;
import com.google.gson.Gson;


public class HomeInteractorImpl implements HomeInteractor, VollyRequestInterface {
    private onResponseListener onResponseListener;
    private Context nContext;

    @Override
    public void getAppsDataFromServer(final onResponseListener onResponseListener, final Context nContext) {
        this.onResponseListener = onResponseListener;
        this.nContext=nContext;
        VollyRequestHelper helper = new VollyRequestHelper(this, nContext);
        helper.makeJsonObjReq(AppConstant.GET_METHOD, AppConfig.HOME_URL, AppConstant.TAG_HOME);

    }

    @Override
    public void vollyResponse(String response, String tag) {
        // get api data from data layer
        AppsRes gson = new Gson().fromJson(response, AppsRes.class);
        if (gson.success) {
            onResponseListener.onResponse(gson.data);
        } else {
            onResponseListener.onError(nContext.getString(R.string.something_went_wrong));
        }
//        try {
//            JSONObject object=new JSONObject(response);
//            JSONArray jsonArray=object.getJSONArray("data");
//            for(int i=0; i<jsonArray.length(); i++){
//                JSONObject obj=jsonArray.getJSONObject(i);
//                Apps objapp=new Apps();
//                objapp.id=obj.getInt("id");
//                objapp.name=obj.getString("name");
//                objapp.active_devices=obj.getString("active_devices");
//                objapp.app_units=obj.getString("app_units");
//                objapp.impressions=obj.getString("impressions");
//                objapp.sales=obj.getString("sales");
//                objapp.crashes=obj.getString("crashes");
//                objapp.sessions=obj.getString("sessions");
//
//                arrdata.add(objapp);
//            }
//            onResponseListener.onResponse(arrdata);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

    }

    @Override
    public void vollyErrorResponse(String error, String tag) {
        onResponseListener.onError(error);
    }
}
