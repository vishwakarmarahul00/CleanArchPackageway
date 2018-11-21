package com.cleanway.apackage.cleanarchpackageway.data_layer.net;



public interface VollyRequestInterface {

    void vollyResponse(String response, String tag);

    void vollyErrorResponse(String error, String tag);
}
