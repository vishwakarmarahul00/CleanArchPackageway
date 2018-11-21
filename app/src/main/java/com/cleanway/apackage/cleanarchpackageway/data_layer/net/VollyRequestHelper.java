package com.cleanway.apackage.cleanarchpackageway.data_layer.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.cleanway.apackage.cleanarchpackageway.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.cleanway.apackage.cleanarchpackageway.presenter_layer.Utils.AppConstant.FAILURE;
import static com.cleanway.apackage.cleanarchpackageway.presenter_layer.Utils.AppConstant.WEB_CONTENT_TYPE;


public class VollyRequestHelper {

    private String TAG = VollyRequestHelper.class.getSimpleName();
    private Context mCtx;
    private VollyRequestInterface requestInterfaceListner;

    public VollyRequestHelper(VollyRequestInterface vollyRequestInterface, Context ctx) {
        this.mCtx = ctx;
        this.requestInterfaceListner = vollyRequestInterface;
    }

    public VollyRequestHelper(Context ctx) {
        this.mCtx = ctx;
        this.requestInterfaceListner = (VollyRequestInterface) mCtx;
    }


    public boolean checkNetwork() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) mCtx.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }



    public void makeJsonObjReq(int restMethodType, String url, final Map<String, String> mHeaders, final Map<String, String> mParams, final String reqTag) {

        if (checkNetwork()) {
            JsonObjectRequest jsonObjReq = new JsonObjectRequest(restMethodType,
                    url, null,
                    new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {
                            Log.d(TAG, response.toString());
                            requestInterfaceListner.vollyResponse(response.toString(), reqTag);

                        }
                    }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e(TAG, "Request failed: " + error.toString());
                    requestInterfaceListner.vollyErrorResponse(error.getMessage(), reqTag);

                }
            }) {

                /**
                 * Passing some request headers
                 */
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    return mHeaders;
                }

                @Override
                protected Map<String, String> getParams() {
                    return mParams;
                }

            };

            // Adding request to request queue
            VollyController.getInstance(mCtx).addToRequestQueue(jsonObjReq,
                    reqTag);
        } else
            requestInterfaceListner.vollyErrorResponse(mCtx.getString(R.string.error_no_network), reqTag);

        // Cancelling request
        // ApplicationController.getInstance().getRequestQueue().cancelAll(tag_json_obj);
    }


    public void makeJsonObjReq(int restMethodType, String url, final String reqTag) {
        if (checkNetwork()) {
            JsonObjectRequest jsonObjReq = new JsonObjectRequest(restMethodType,
                    url, null,
                    new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {
                            requestInterfaceListner.vollyResponse(response.toString(), reqTag);

                        }
                    }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e(TAG, "Request failed: " + error.toString());
                    if (error.getMessage() != null)
                        requestInterfaceListner.vollyErrorResponse(error.getMessage(), reqTag);
                    else
                        requestInterfaceListner.vollyErrorResponse("Error", reqTag);

                }
            });


            // Adding request to request queue
            VollyController.getInstance(mCtx).addToRequestQueue(jsonObjReq,
                    reqTag);
        } else
            requestInterfaceListner.vollyErrorResponse(mCtx.getString(R.string.error_no_network), reqTag);

    }


    public void makeJsonObjReqWithoutAuth(int restMethodType, String url, JSONObject jsonObj, final String reqTag) {
        if (checkNetwork()) {
            JsonObjectRequest jsonObjReq = new JsonObjectRequest(restMethodType, url, jsonObj, new Response.Listener<JSONObject>() {

                @Override
                public void onResponse(JSONObject response) {
                    Log.d(TAG, response.toString());
                    requestInterfaceListner.vollyResponse(response.toString(), reqTag);

                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    try {

                        Log.e(TAG, "Request failed: " + error.toString());
                        if (error.getMessage() != null)
                            requestInterfaceListner.vollyErrorResponse(error.getMessage(), reqTag);
                        else
                            requestInterfaceListner.vollyErrorResponse("Error", reqTag);
                    } catch (Exception e) {

                    }

                }
            }) {


                @Override
                public String getBodyContentType() {
                    return null;
                }


            };

            // Adding request to request queue
            VollyController.getInstance(mCtx).addToRequestQueue(jsonObjReq,
                    reqTag);
        } else
            requestInterfaceListner.vollyErrorResponse(mCtx.getString(R.string.error_no_network), reqTag);

    }


    public void makeJsonObjReq(int restMethodType, String url, JSONObject jsonObj, final String reqTag) {
        if (checkNetwork()) {
            final HashMap<String, String> headers = new HashMap<String, String>();
            headers.put("Content-Type", WEB_CONTENT_TYPE);
           // headers.put("Authorization", Credentials.getAuthKey(mCtx));


            JsonObjectRequest jsonObjReq = new JsonObjectRequest(restMethodType, url, jsonObj, new Response.Listener<JSONObject>() {

                @Override
                public void onResponse(JSONObject response) {
                    Log.d(TAG, response.toString());
                    requestInterfaceListner.vollyResponse(response.toString(), reqTag);

                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {

                    if (error instanceof TimeoutError ) {
                        requestInterfaceListner.vollyErrorResponse("Network timeout error. Please check your connection and try again.", reqTag);
                    }
                    else if (error instanceof NoConnectionError) {
                        if(error.toString().contains("Not trusted"))
                        {
                            requestInterfaceListner.vollyErrorResponse(mCtx.getString(R.string.certificate_not_trust), reqTag);
                        }
                        else
                            requestInterfaceListner.vollyErrorResponse(mCtx.getString(R.string.no_connection_error), reqTag);
                        //TODO
                    }
                    else if (error instanceof NetworkError) {
                        requestInterfaceListner.vollyErrorResponse(mCtx.getString(R.string.error_no_network), reqTag);

                    }

                   else if (error instanceof AuthFailureError) {
                        requestInterfaceListner.vollyErrorResponse("The UserID and Password entered do not match our records.  Please try again.", reqTag);
                        //TODO
                    } else if (error instanceof ServerError) {
                        String json = null;
                        NetworkResponse response = error.networkResponse;
                        if(response != null && response.data != null){
                            json = new String(response.data);

                            try {
                                JSONObject jsonResponse = new JSONObject(json);
                                String status=jsonResponse.getString("status");
                                String message=jsonResponse.getString("message");

                                if(FAILURE.equalsIgnoreCase(status)){

                                    requestInterfaceListner.vollyErrorResponse(message, reqTag);

                                }
                                else {
                                    requestInterfaceListner.vollyErrorResponse(mCtx.getString(R.string.server_failure), reqTag);

                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }else {

                            requestInterfaceListner.vollyErrorResponse(mCtx.getString(R.string.server_failure), reqTag);
                        }
                        //TODO
                    }
                    else {
                        requestInterfaceListner.vollyErrorResponse(mCtx.getString(R.string.no_connection_error), reqTag);
                    }

                }
            }) {


                @Override
                public String getBodyContentType() {
                    return null;
                }


                /**
                 * Passing some request headers
                 * */


                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {

                    return headers;
                }


            };
            VollyController.getInstance(mCtx).addToRequestQueue(jsonObjReq,
                    reqTag);
        } else
            requestInterfaceListner.vollyErrorResponse(mCtx.getString(R.string.error_no_network), reqTag);

    }



}
