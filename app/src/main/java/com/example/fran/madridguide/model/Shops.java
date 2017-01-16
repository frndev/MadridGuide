package com.example.fran.madridguide.model;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fran on 12/1/17.
 */

public class Shops implements IModelIterable<Shop>, IModelUpdatable<Shop> {

    List<Shop> shops = new ArrayList<Shop>();

    public Shops(List<Shop> shops) {
        this.shops = shops;
    }

    private Shops() {
        shops = new ArrayList<>();
    }

    public static @NonNull Shops build(@NonNull final List<Shop> shopList) {

        Shops shops = new Shops(shopList);

        if (shopList == null){
            shops.shops = new ArrayList<>();
        }

        return shops;

    }

    public static Shops build() {
        return build(new ArrayList<Shop>());
    }


    @Override
    public long size() {
        return shops.size();
    }

    @Override
    public Shop get(long index) {
        return shops.get((int) index);
    }

    @Override
    public List<Shop> allElements() {
        return shops;
    }

    @Override
    public void add(Shop shop) {
        shops.add(shop);
    }

    @Override
    public void delete(Shop shop) {
        shops.remove(shop);
    }

    @Override
    public void edit(Shop newShop, long index) {
        shops.set((int) index,newShop);
    }
}
