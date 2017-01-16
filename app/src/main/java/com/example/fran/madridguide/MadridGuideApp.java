package com.example.fran.madridguide;

import android.app.Application;
import android.content.Context;

import com.example.fran.madridguide.interactors.CacheAllActivitiesInteractor;
import com.example.fran.madridguide.interactors.CacheAllShopsInteractor;
import com.example.fran.madridguide.interactors.CacheCompletion;
import com.example.fran.madridguide.interactors.GetAllActivitiesInteractor;
import com.example.fran.madridguide.interactors.GetAllShopsInteractor;
import com.example.fran.madridguide.interactors.InteractorCompletion;
import com.example.fran.madridguide.manager.db.ShopDAO;
import com.example.fran.madridguide.manager.net.NetworkManager;
import com.example.fran.madridguide.manager.net.ShopEntity;
import com.example.fran.madridguide.model.Activities;
import com.example.fran.madridguide.model.Shop;
import com.example.fran.madridguide.model.Shops;
import com.example.fran.madridguide.model.mappers.ShopEntityShopMapper;
import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by fran on 8/1/17.
 */

public class MadridGuideApp extends Application {


    private static WeakReference<Context> appContext;

    @Override
    public void onCreate() {
        super.onCreate();

        appContext = new WeakReference<Context>(getApplicationContext());

        Picasso.with(getApplicationContext()).setLoggingEnabled(true);
        Picasso.with(getApplicationContext()).setIndicatorsEnabled(true);


        new GetAllShopsInteractor().execute(appContext.get(),
                new InteractorCompletion<Shops>() {
                    @Override
                    public void completion(Shops shops) {
                        new CacheAllShopsInteractor().execute(appContext.get(), shops, new CacheCompletion() {
                            @Override
                            public void completion(boolean success) {

                            }
                        });
                    }
                });

        new GetAllActivitiesInteractor().execute(appContext.get(),
                new InteractorCompletion<Activities>() {
                    @Override
                    public void completion(Activities activities) {
                        new CacheAllActivitiesInteractor().execute(appContext.get(), activities, new CacheCompletion() {
                            @Override
                            public void completion(boolean success) {

                            }
                        });
                    }
                });


    }



    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    public static Context getAppContext(){
        return appContext.get();
    }
}
