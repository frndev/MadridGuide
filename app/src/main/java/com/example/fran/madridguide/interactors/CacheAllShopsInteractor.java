package com.example.fran.madridguide.interactors;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.example.fran.madridguide.manager.db.ShopDAO;
import com.example.fran.madridguide.manager.net.NetworkManager;
import com.example.fran.madridguide.manager.net.ShopEntity;
import com.example.fran.madridguide.model.Shop;
import com.example.fran.madridguide.model.Shops;
import com.example.fran.madridguide.model.mappers.ShopEntityShopMapper;

import java.util.List;


/**
 * Created by fran on 15/1/17.
 */

public class CacheAllShopsInteractor implements ICacheAllDataInteractor<Shops>{

    public void execute(final Context context, final Shops shops, final CacheCompletion completion) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                ShopDAO dao = new ShopDAO(context);
                boolean success = true;
                for (Shop shop : shops.allElements()){
                    success = dao.insert(shop) > 0;
                    if (!success){
                        break;
                    }
                }

                Handler handler = new Handler(Looper.getMainLooper());
                final boolean finalSuccess = success;
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (completion != null) {
                            completion.completion(finalSuccess);
                        }
                    }
                });

            }
        }).start();

    }

}
