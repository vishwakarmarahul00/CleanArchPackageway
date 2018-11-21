package com.cleanway.apackage.cleanarchpackageway.presenter_layer.features.home.view;


import com.cleanway.apackage.cleanarchpackageway.model_layer.Apps;

import java.util.List;


public interface HomeView {
    void showLoader();
    void hideLoader();
    void getHomeData();
    void setHomeData(List<Apps> arrdata);
    void showErrorMsg(String message);

}
