package com.herprogramacion.hazloakki.network;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;


public class AppController extends Application {
	private static AppController mInstance;
	private static Context mCtx;
	private RequestQueue mRequestQueue;
	public static final String TAG = AppController.class.getSimpleName();

	private AppController(Context context) {
		mCtx = context;
		mRequestQueue = getRequestQueue();
	}

	public static synchronized AppController getInstance(Context context) {
		if (mInstance == null) {
			mInstance = new AppController(context);
		}
		return mInstance;
	}

	public RequestQueue getRequestQueue() {
		if (mRequestQueue == null) {
			Cache cache = new DiskBasedCache(mCtx.getCacheDir(), 10 * 1024 * 1024);
			Network network = new BasicNetwork(new HurlStack());
			mRequestQueue = new RequestQueue(cache, network); // Volley.newRequestQueue(getApplicationContext());
			// Don't forget to start the volley request queue
			mRequestQueue.start();
		}
		return mRequestQueue;
	}

	public <T> void addToRequestQueue(Request<T> req, String tag) {
		req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
		getRequestQueue().add(req);
	}

	public <T> void addToRequestQueue(Request<T> req) {
		req.setTag(TAG);
		getRequestQueue().add(req);
	}

	public void cancelPendingRequests(Object tag) {
		if (mRequestQueue != null) {
			mRequestQueue.cancelAll(tag);
		}
	}

}
