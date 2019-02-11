package com.etwinkle.solutions.hardwaremanagement.network;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.util.Log;
import android.util.LruCache;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.HttpStack;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.etwinkle.solutions.hardwaremanagement.utils.TLSSocketFactory;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.security.ProviderInstaller;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;


public class VolleySingleton {

    private static VolleySingleton mInstance;
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;
    private static Context mCtx;


    private VolleySingleton(Context context) {
        mCtx = context;
        mRequestQueue = getRequestQueue();

        mImageLoader = new ImageLoader(mRequestQueue, new ImageLoader.ImageCache() {
            private final LruCache<String, Bitmap> cache = new LruCache<String, Bitmap>(20);

            @Override
            public Bitmap getBitmap(String url) {
                return cache.get(url);
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {
                cache.put(url, bitmap);
            }
        });
    }

    public static synchronized VolleySingleton getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new VolleySingleton(context);
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN
                    && Build.VERSION.SDK_INT <= Build.VERSION_CODES.KITKAT) {
                try {
                    ProviderInstaller.installIfNeeded(mCtx.getApplicationContext());
                } catch (GooglePlayServicesRepairableException e) {
                    // Indicates that Google Play services is out of date, disabled, etc.
                    // Prompt the user to install/update/enable Google Play services.
                    GooglePlayServicesUtil.showErrorNotification(e.getConnectionStatusCode(), mCtx.getApplicationContext());
                    // Notify the SyncManager that a soft error occurred.
//                    syncResult.stats.numIOExceptions++;
//                    return;
                } catch (GooglePlayServicesNotAvailableException e) {
                    // Indicates a non-recoverable error; the ProviderInstaller is not able
                    // to install an up-to-date Provider.
                    // Notify the SyncManager that a hard error occurred.
//                    syncResult.stats.numAuthExceptions++;
//                    return;
                }

                HttpStack stack = null;
                try {
                    stack = new HurlStack(null, new TLSSocketFactory());
                } catch (KeyManagementException e) {
                    e.printStackTrace();
                    Log.d("Your Wrapper Class", "Could not create new stack for TLS v1.2");
                    stack = new HurlStack();
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                    Log.d("Your Wrapper Class", "Could not create new stack for TLS v1.2");
                    stack = new HurlStack();
                }
                mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext(), stack);
            } else {
                mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
            }
        }
        return mRequestQueue;
    }


    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }


    public ImageLoader getImageLoader() {
        return mImageLoader;
    }
}