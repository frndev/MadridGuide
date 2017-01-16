package com.example.fran.madridguide;

import android.database.Cursor;
import android.test.AndroidTestCase;

import com.example.fran.madridguide.manager.db.ActivityDAO;
import com.example.fran.madridguide.model.Activity;

import java.util.List;

/**
 * Created by fran on 13/1/17.
 */

public class ActivityDAOTests extends AndroidTestCase {

    public static final String ACTIVITY_TESTING_NAME = "activity";

    public void testCanInsertANewActivity() {

        final ActivityDAO sut = new ActivityDAO(getContext());

        final int count = getCount(sut);

        final long id = insertShop(sut);

        assertTrue(id > 0);
        assertTrue(count + 1 == sut.queryCursor().getCount());
    }

    public void testCanDeleteAnActivity() {

        final ActivityDAO sut = new ActivityDAO(getContext());

        final long id = insertShop(sut);

        final int count = getCount(sut);

        assertEquals(1,sut.delete(id));

        assertTrue(count - 1 == sut.queryCursor().getCount());

    }

    public void testDeleteAll(){

        final ActivityDAO sut = new ActivityDAO(getContext());

        sut.deleteAll();

        final int count = getCount(sut);

        assertEquals(0,count);

    }

    public void testQueryOneActivity(){

        final ActivityDAO dao = new ActivityDAO(getContext());

        final long id = insertShop(dao);

        Activity sut = dao.query(id);

        assertNotNull(sut);
        assertEquals(sut.getId(), id);
    }

    public void testQueryAllShops() {

        final ActivityDAO dao = new ActivityDAO(getContext());

        insertShop(dao);

        List<Activity> shopList = dao.query();

        assertNotNull(shopList);
        assertTrue(shopList.size() > 0);

    }

    private long insertShop(ActivityDAO sut) {
        final Activity shop = new Activity(1, ACTIVITY_TESTING_NAME).setAddress("AD 1");

        return sut.insert(shop);
    }

    private int getCount(ActivityDAO sut) {
        final Cursor cursor = sut.queryCursor();
        return cursor.getCount();
    }
}
