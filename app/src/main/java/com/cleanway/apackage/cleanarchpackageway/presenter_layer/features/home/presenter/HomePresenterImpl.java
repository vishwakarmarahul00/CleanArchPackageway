package com.cleanway.apackage.cleanarchpackageway.presenter_layer.features.home.presenter;

import android.content.Context;


import com.cleanway.apackage.cleanarchpackageway.domain_layer.homeinteractor.HomeInteractor;
import com.cleanway.apackage.cleanarchpackageway.domain_layer.homeinteractor.HomeInteractorImpl;
import com.cleanway.apackage.cleanarchpackageway.model_layer.Apps;
import com.cleanway.apackage.cleanarchpackageway.presenter_layer.features.home.view.HomeView;

import java.util.List;


public class HomePresenterImpl implements HomePresenter, HomeInteractor.onResponseListener {
    private HomeView homeView;
    //private UUID uuid;
    private HomeInteractorImpl homeUseCase;
    Context mContext;

    public HomePresenterImpl(HomeView homeView, Context mContext) {
        this.homeView = homeView;
        this.mContext = mContext;
        //  this.uuid = UUID.randomUUID();
        homeUseCase = new HomeInteractorImpl();
    }


    @Override
    public void requestAppsData() {
        if (homeView != null) {
            homeView.showLoader();
        }
        // get response from usecase from domain layer
        homeUseCase.getAppsDataFromServer(this, mContext);
    }

    @Override
    public void onResponse(List<Apps> arrData) {
        if (homeView != null) {
            homeView.setHomeData(arrData);
            homeView.hideLoader();
        }
    }

    @Override
    public void onError(String exception) {
        if (homeView != null) {
            homeView.showErrorMsg(exception);
            homeView.hideLoader();
        }
    }

    @Override
    public void onDestroy() {
        homeView = null;
    }

}
