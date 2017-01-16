package com.example.fran.madridguide.model.mappers;

import com.example.fran.madridguide.manager.net.ShopEntity;
import com.example.fran.madridguide.model.Shop;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by fran on 15/1/17.
 */

public class ShopEntityShopMapper {

    public List<Shop> map(List<ShopEntity> shopEntities) {

        List<Shop> shopList = new LinkedList<>();

        for(ShopEntity entity : shopEntities) {

            Shop shop = new Shop(entity.getId(),entity.getName());
            shop.setDescription(entity.getDescriptionES());
            shop.setAddress(entity.getAddress());
            shop.setUrl(entity.getUrl());
            shop.setLogoImageURL(entity.getLogoImg());
            shop.setImageURL(entity.getImg());
            shop.setLatitude(entity.getLatitude());
            shop.setLongitude(entity.getLongitude());
            shopList.add(shop);

        }

        return shopList;

    }
}
