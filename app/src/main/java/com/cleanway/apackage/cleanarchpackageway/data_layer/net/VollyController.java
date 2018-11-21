package com.cleanway.apackage.cleanarchpackageway.data_layer.net;

import android.content.Context;
import android.text.TextUtils;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

import static com.cleanway.apackage.cleanarchpackageway.presenter_layer.Utils.AppConstant.TAG_CLAIM_UPLOAD;
import static com.cleanway.apackage.cleanarchpackageway.presenter_layer.Utils.AppConstant.TAG_PAP_MYSELF;
import static com.cleanway.apackage.cleanarchpackageway.presenter_layer.Utils.AppConstant.TAG_REIMBURSE_MYSELF;
import static com.cleanway.apackage.cleanarchpackageway.presenter_layer.Utils.AppConstant.TIME_OUT_SECONDS;
import static com.cleanway.apackage.cleanarchpackageway.presenter_layer.Utils.AppConstant.TIME_OUT_SECONDS1;


public class VollyController {

    private static final String TAG = VollyController.class
            .getSimpleName();
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;
    private static Context mCtx;
    private static VollyController mInstance;


    private VollyController(Context ctx) {
        this.mCtx = ctx;
        mRequestQueue = getRequestQueue();
        mImageLoader = getImageLoader();
    }


    public static synchronized VollyController getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new VollyController(context);
        }
        return mInstance;
    }


    public RequestQueue getRequestQueue() {

      //  if (mRequestQueue == null) {
            //SSLSocketFactory pinnedSocketFactory = getPinnedSSLSoketFactory();
            mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
            //mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext(), new HurlStack(null, pinnedSocketFactory));
      //  }
        return mRequestQueue;
    }

    public ImageLoader getImageLoader() {
        getRequestQueue();
        if (mImageLoader == null) {
            mImageLoader = new ImageLoader(this.mRequestQueue,
                    new LruBitmapCache());
        }
        return this.mImageLoader;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty

       if(tag.equalsIgnoreCase(TAG_PAP_MYSELF)||tag.equalsIgnoreCase(TAG_REIMBURSE_MYSELF)||tag.equalsIgnoreCase( TAG_CLAIM_UPLOAD)){
            req.setRetryPolicy(new DefaultRetryPolicy(
                    TIME_OUT_SECONDS1,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
            getRequestQueue().add(req);
        }
       else {
           req.setRetryPolicy(new DefaultRetryPolicy(
                   TIME_OUT_SECONDS,
                   DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                   DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
           req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
           getRequestQueue().add(req);
       }
    }

  /*  public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }*/

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }

   /* public SSLSocketFactory getPinnedSSLSoketFactory() {
        TrustManager tm[] = {new PubKeyManager(Configuration.getPINS())};
        SSLSocketFactory pinnedSSLSocketFactory = null;
        try {
            SSLContext context = SSLContext.getInstance("TLS");
            context.init(null, tm, null);
            pinnedSSLSocketFactory = context.getSocketFactory();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }

        return pinnedSSLSocketFactory;
    }*/
}
