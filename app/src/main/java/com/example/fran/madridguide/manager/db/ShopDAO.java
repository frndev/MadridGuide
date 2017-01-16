package com.example.fran.madridguide.manager.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.lang.ref.WeakReference;
import java.util.LinkedList;
import java.util.List;

import com.example.fran.madridguide.model.Shop;
import com.example.fran.madridguide.model.Shops;

import static com.example.fran.madridguide.manager.db.DBConstants.*;

public class ShopDAO implements DAOPersistable<Shop>{

    private WeakReference<Context> context;

    private DBHelper dbHelper;
    private SQLiteDatabase db;

    public ShopDAO(@NonNull Context context,DBHelper dbHelper) {
        this.context = new WeakReference<Context>(context);
        this.dbHelper = dbHelper;
        this.db = dbHelper.getDB();

    }

    public ShopDAO(Context context) {
        this(context,DBHelper.getInstance(context));
    }

    @Override
    public long insert(@NonNull Shop shop) {
        if (shop == null) {
            return 0;
        }
        // insert



        db.beginTransaction();
        long id = DBHelper.INVALID_ID;
        try {
            id = db.insert(TABLE_SHOP, null, this.getContentValues(shop));
            shop.setId(id);
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }

        return id;
    }

    @Override
    public void update(long id, @NonNull Shop shop) {

    }

    @Override
    public int delete(long id) {

        return db.delete(TABLE_SHOP,  KEY_SHOP_ID + " = " + id, null);
    }

    @Override
    public void deleteAll() {

        db.delete(TABLE_SHOP,  null, null);

    }

    public static @NonNull ContentValues getContentValues(@NonNull Shop shop) {
        final ContentValues content = new ContentValues();

        if (shop == null) {
            return content;
        }
        content.put(KEY_SHOP_NAME, shop.getName());
        content.put(KEY_SHOP_ADDRESS, shop.getAddress());
        content.put(KEY_SHOP_URL, shop.getUrl());
        content.put(KEY_SHOP_DESCRIPTION, shop.getDescription());
        content.put(KEY_SHOP_IMAGE_URL, shop.getImageURL());
        content.put(KEY_SHOP_LOGO_IMAGE_URL, shop.getLogoImageURL());
        content.put(KEY_SHOP_LATITUDE, shop.getLatitude());
        content.put(KEY_SHOP_LONGITUDE, shop.getLongitude());

        return content;
    }


    // convenience method
    public static Shop shopFromCursor(Cursor c) {
        assert c != null;

        Shop s = new Shop(c.getLong(c.getColumnIndex(KEY_SHOP_ID)),c.getString(c.getColumnIndex(KEY_SHOP_NAME)));

        s.setAddress(c.getString(c.getColumnIndex(KEY_SHOP_ADDRESS)));
        s.setDescription(c.getString(c.getColumnIndex(KEY_SHOP_DESCRIPTION)));
        s.setUrl(c.getString(c.getColumnIndex(KEY_SHOP_URL)));
        s.setLogoImageURL(c.getString(c.getColumnIndex(KEY_SHOP_LOGO_IMAGE_URL)));
        s.setImageURL(c.getString(c.getColumnIndex(KEY_SHOP_IMAGE_URL)));
        s.setLatitude(c.getDouble(c.getColumnIndex(KEY_SHOP_LATITUDE)));
        s.setLongitude(c.getDouble(c.getColumnIndex(KEY_SHOP_LONGITUDE)));

        return s;
    }

    public @NonNull Cursor queryCursor() {
        // select

        Cursor c = db.query(TABLE_SHOP, allShopColumns, null, null, null, null, KEY_SHOP_ID);
        if (c != null && c.getCount() > 0){
            c.moveToFirst();
        }

        return c;
    }


    /**
     * Returns a Shop object from its id
     * @param id - the shop id in db
     * @return Shop object if found, null otherwise
     */
    @Override
    public @Nullable Shop query(long id) {
        Shop shop = null;

        String where = KEY_SHOP_ID + "=" + id;
        Cursor c = db.query(TABLE_SHOP, allShopColumns, where, null, null, null, KEY_SHOP_ID);
        if (c != null) {
            if (c.getCount() > 0) {
                c.moveToFirst();
                shop = shopFromCursor(c);
            }
        }
        c.close();
        return shop;
    }

    @Nullable
    @Override
    public List<Shop> query() {

        Cursor c = queryCursor();
        if (c == null || !c.moveToFirst()) {
            return null;
        }

        List<Shop> shops = new LinkedList<>();
        c.moveToFirst();
       do {

            shops.add(shopFromCursor(c));

        } while (c.moveToNext());


        return shops;
    }
    @NonNull
    public static Shops getShops(Cursor data) {
        List<Shop> shopList = new LinkedList<>();

        while (data.moveToNext()) {
            Shop shop = ShopDAO.shopFromCursor(data);
            shopList.add(shop);
        }

        return Shops.build(shopList);
    }

    public static Shop getShopFromContentValues(ContentValues contentValues) {
        final Shop shop = new Shop(1,contentValues.getAsString(KEY_SHOP_NAME));

        shop.setAddress(contentValues.getAsString(KEY_SHOP_ADDRESS));
        shop.setUrl(contentValues.getAsString(KEY_SHOP_URL));
        shop.setImageURL(contentValues.getAsString(KEY_SHOP_IMAGE_URL));
        shop.setLogoImageURL(contentValues.getAsString(KEY_SHOP_LOGO_IMAGE_URL));
        shop.setLatitude(contentValues.getAsDouble(KEY_SHOP_LATITUDE));
        shop.setLongitude(contentValues.getAsDouble(KEY_SHOP_LONGITUDE));
        shop.setDescription(contentValues.getAsString(KEY_SHOP_DESCRIPTION));

        return shop;
    }

    public Cursor queryCursor(long id) {

        Cursor c = db.query(TABLE_SHOP, allShopColumns, "WHERE ID = " + id, null, null, null, KEY_SHOP_ID);
        if (c != null && c.getCount() > 0){
            c.moveToFirst();
        }

        return c;

    }
}