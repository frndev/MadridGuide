package com.example.fran.madridguide;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.test.AndroidTestCase;

import com.example.fran.madridguide.manager.db.DBConstants;
import com.example.fran.madridguide.manager.db.ShopDAO;
import com.example.fran.madridguide.manager.db.provider.MadridGuideProvider;
import com.example.fran.madridguide.model.Shop;

/**
 * Created by fran on 15/1/17.
 */

public class MadridGuideProviderTests extends AndroidTestCase{

    public void testQueryAllShops() {

        ContentResolver cr = getContext().getContentResolver();

        Cursor c = cr.query(MadridGuideProvider.SHOPS_URI, DBConstants.allShopColumns, null, null,null);

        assertNotNull(c);
    }

    public void testInsertAShop() {

        final ContentResolver cr = getContext().getContentResolver();

        final Cursor before = cr.query(MadridGuideProvider.SHOPS_URI, DBConstants.allShopColumns, null, null,null);

        final int beforeCount = before.getCount();

        final Shop shop = new Shop(1, "Little Shop of horrors!");

        final Uri inserted = cr.insert(MadridGuideProvider.SHOPS_URI, ShopDAO.getContentValues(shop));

        final Cursor after = cr.query(MadridGuideProvider.SHOPS_URI, DBConstants.allShopColumns, null, null,null);


        assertNotNull(inserted);
        assertEquals(beforeCount + 1, after.getCount());


    }

}
