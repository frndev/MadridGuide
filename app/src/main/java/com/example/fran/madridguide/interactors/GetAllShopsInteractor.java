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
import com.example.fran.madridguide.util.Tools;

import java.util.List;

/**
 * Created by fran on 15/1/17.
 */

public class GetAllShopsInteractor implements IGetAllDataInteractor<Shops>{



    public void execute(final Context context, final InteractorCompletion<Shops> completion) {

        if (Tools.isShopsDownloaded(context)) {
            executeFromCache(context,null);
        }else{
            executeFromInternet(context,completion);
        }


    }

    private void executeFromInternet(final Context context, final InteractorCompletion<Shops> completion) {
        Shops shops = null;

        NetworkManager nm = new NetworkManager(context);
        nm.getShopsFromServer(new NetworkManager.GetShopsListener() {
            @Override
            public void getShopsCompletion(List<ShopEntity> result) {
                List<Shop> shopList = new ShopEntityShopMapper().map(result);
                if (result != null) {
                    completion.completion(Shops.build(shopList));
                }
                Tools.shopDataDownloaded(context);
            }

            @Override
            public void getShopsEntitiesDidFail() {

            }
        });
    }


    public void executeFromCache(final Context context, final InteractorCompletion<Shops> completion) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                ShopDAO dao = new ShopDAO(context);

                final List<Shop> shopList = dao.query();

                final Shops shops = Shops.build(shopList);
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (completion != null){
                            completion.completion(shops);
                        }
                    }
                });

            }
        }).start();

    }
}
