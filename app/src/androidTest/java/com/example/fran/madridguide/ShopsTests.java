package com.example.fran.madridguide;

import android.support.annotation.NonNull;
import android.test.AndroidTestCase;

import com.example.fran.madridguide.model.Shop;
import com.example.fran.madridguide.model.Shops;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fran on 13/1/17.
 */

public class ShopsTests extends AndroidTestCase {

    private static final String SHOP = "shop";
    private static final String ADDRESS = "ADDRESS";

    public void testCanCreateAShopsWithNullReturnsNotNull() {
        Shops sut = Shops.build(null);

        assertNotNull(sut);
        assertNotNull(sut.allElements());

    }
    public void testCanCreateAShopsWithAListNReturnsNotNull() {

        List<Shop> data = getShops();

        Shops sut = Shops.build(data);

        assertNotNull(sut);
        assertNotNull(sut.allElements());
        assertEquals(sut.allElements(),data);
        assertEquals(sut.allElements().size(),data.size());
    }

    @NonNull
    private List<Shop> getShops() {
        List<Shop> data = new ArrayList<>();
        data.add(new Shop(0,SHOP).setAddress(ADDRESS));
        data.add(new Shop(10,SHOP).setAddress(ADDRESS));
        return data;
    }
}
