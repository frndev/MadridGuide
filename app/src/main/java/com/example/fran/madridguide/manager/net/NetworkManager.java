package com.example.fran.madridguide.manager.net;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.Reader;
import java.io.StringReader;
import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by fran on 15/1/17.
 */

public class NetworkManager {

    private WeakReference<Context> context;

    public interface GetShopsListener {
        public void getShopsCompletion(List<ShopEntity> result);
        public void getShopsEntitiesDidFail();
    }
    public interface GetActivitiesListener {
        public void getActivitiesCompletion(List<ActivityEntity> result);
        public void getActivitiesEntitiesDidFail();
    }



    public NetworkManager(Context context) {
        this.context = new WeakReference<Context>(context);
    }

    public void getShopsFromServer(final GetShopsListener listener) {

        RequestQueue queue = Volley.newRequestQueue(context.get());

        String url = "http://madrid-shops.com/json_new/getShops.php";

        StringRequest request = new StringRequest(
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        
                        List<ShopEntity> shopResponse = parseResponse(response);
                        if (listener != null){
                            listener.getShopsCompletion(shopResponse);
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (listener != null) {
                            listener.getShopsEntitiesDidFail();
                        }
                    }
                }
        );

        queue.add(request);

    }    public void getActivitiesFromServer(final GetActivitiesListener listener) {

        RequestQueue queue = Volley.newRequestQueue(context.get());

        String url = "http://madrid-shops.com/json_new/getActivities.php";

        StringRequest request = new StringRequest(
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        List<ActivityEntity> activityResponse = parseActivityResponse(response);
                        if (listener != null){
                            listener.getActivitiesCompletion(activityResponse);
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (listener != null) {
                            listener.getActivitiesEntitiesDidFail();
                        }
                    }
                }
        );

        queue.add(request);

    }

    private List<ShopEntity> parseResponse(String response) {
        List<ShopEntity> result = null;
        try {

            Reader reader = new StringReader(response);
            Gson gson = new GsonBuilder().create();

            ShopResponse shopResponse = gson.fromJson(reader,ShopResponse.class);
            result = shopResponse.result;
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;

    }
    private List<ActivityEntity> parseActivityResponse(String response) {
        List<ActivityEntity> result = null;
        try {

            Reader reader = new StringReader(response);
            Gson gson = new GsonBuilder().create();

            ActivityResponse activityResponse = gson.fromJson(reader,ActivityResponse.class);
            result = activityResponse.result;
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;

    }
}
