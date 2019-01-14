package com.herprogramacion.hazloakki.network;

import android.app.Activity;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.Response;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by JAarzate on 19/11/2017.
 */

public class RestUtil {

    private static Activity activity;
    private static RestUtil restUtil;
    private static String TAG = RestUtil.class.getSimpleName();

    public static RestUtil getIntance(Activity activity){
        if(restUtil == null){
            restUtil = new RestUtil();
        }
        return restUtil;
    }

    public static void sendRequestJsonPost(String url, JSONObject jsonObject) {
        try {
            /** json object parameter**/
            JSONObject jsonObject2 = new JSONObject();
            jsonObject.put("hello", "hello");
            Log.e(TAG, jsonObject.toString() + "");

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject jsonObject) {

                    Log.e(TAG, "Response " + jsonObject.toString());
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    Log.e(TAG, volleyError.getMessage());
                }
            });
            //requestQueue.add(jsonObjectRequest);
            AppController.getInstance(activity.getApplicationContext()).getRequestQueue().add(jsonObjectRequest);
        } catch (JSONException e) {
            Log.e(TAG, e.getMessage());
        }
        catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }

    }
}


