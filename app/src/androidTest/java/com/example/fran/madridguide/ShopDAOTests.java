package com.example.fran.madridguide;

import android.database.Cursor;
import android.test.AndroidTestCase;

import com.example.fran.madridguide.manager.db.ShopDAO;
import com.example.fran.madridguide.model.Shop;

import java.util.List;

/**
 * Created by fran on 13/1/17.
 */

public class ShopDAOTests extends AndroidTestCase {

    public static final String SHOP_TESTING_NAME = "shop";

    public void testCanInsertANewShop() {

        final ShopDAO sut = new ShopDAO(getContext());

        final int count = getCount(sut);

        final long id = insertShop(sut);

        assertTrue(id > 0);
        assertTrue(count + 1 == sut.queryCursor().getCount());
    }

    public void testCanDeleteAShop() {

        final ShopDAO sut = new ShopDAO(getContext());

        final long id = insertShop(sut);

        final int count = getCount(sut);

        assertEquals(1,sut.delete(id));

        assertTrue(count - 1 == sut.queryCursor().getCount());

    }

    public void testDeleteAll(){

        final ShopDAO sut = new ShopDAO(getContext());

        sut.deleteAll();

        final int count = getCount(sut);

        assertEquals(0,count);

    }

    public void testQueryOneShop(){

        final ShopDAO dao = new ShopDAO(getContext());

        final long id = insertShop(dao);

        Shop sut = dao.query(id);

        assertNotNull(sut);
        assertEquals(sut.getId(), id);
    }

    public void testQueryAllShops() {

        final ShopDAO dao = new ShopDAO(getContext());

        insertShop(dao);

        List<Shop> shopList = dao.query();

        assertNotNull(shopList);
        assertTrue(shopList.size() > 0);

    }

    private long insertShop(ShopDAO sut) {
        final Shop shop = new Shop(1, SHOP_TESTING_NAME).setAddress("AD 1");

        return sut.insert(shop);
    }

    private int getCount(ShopDAO sut) {
        final Cursor cursor = sut.queryCursor();
        return cursor.getCount();
    }
}
