package com.cleanway.apackage.cleanarchpackageway.presenter_layer.features.appdetail.presenter;



import com.cleanway.apackage.cleanarchpackageway.presenter_layer.Utils.AppConstant;
import com.cleanway.apackage.cleanarchpackageway.presenter_layer.features.appdetail.view.AppDetailView;

import java.util.UUID;


public class AppDetailPresenterImpl implements AppDetailPresenter {
    private AppDetailView appview;

    private UUID uuid;

    public AppDetailPresenterImpl(AppDetailView appview) {
        this.appview = appview;
        // this.uuid = UUID.randomUUID();
    }

    @Override
    public void getAppData() {
        if (appview != null) {
            appview.bindData(AppConstant.selectedApp);
        }
    }

    @Override
    public void onDestroy() {
        appview = null;
    }
}
