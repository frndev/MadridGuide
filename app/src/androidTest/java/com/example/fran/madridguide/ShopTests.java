package com.example.fran.madridguide;

import android.test.AndroidTestCase;

import com.example.fran.madridguide.model.Shop;

/**
 * Created by fran on 13/1/17.
 */

public class ShopTests extends AndroidTestCase {

    public static final String SHOP = "shop";
    public static final String ADDRESS = "ADDRESS";
    public static final String DESC = "DESC";
    public static final String URL = "URL";

    public void testCanCreateAShop() {
        Shop sut = new Shop(0, SHOP);

        assertNotNull(sut);
    }

    public void testANewShopStoresDataCorrectly(){
        Shop sut = new Shop(10, SHOP);

        assertEquals(10,sut.getId());
        assertEquals(SHOP,sut.getName());
    }

    public void testANewShopStoresDataInPropertiesCorrectly() {
        Shop sut = new Shop(11,SHOP)
                .setAddress(ADDRESS)
                .setDescription(DESC)
                .setUrl(URL)
                ;
        assertEquals(sut.getAddress(),ADDRESS);
        assertEquals(sut.getDescription(),DESC);
        assertEquals(sut.getUrl(),URL);
    }
}
