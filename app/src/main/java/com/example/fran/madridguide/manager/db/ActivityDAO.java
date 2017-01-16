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


import com.example.fran.madridguide.model.Activities;
import com.example.fran.madridguide.model.Activity;


import static com.example.fran.madridguide.manager.db.DBConstants.*;

public class ActivityDAO implements DAOPersistable<Activity>{

    private WeakReference<Context> context;

    private DBHelper dbHelper;
    private SQLiteDatabase db;

    public ActivityDAO(@NonNull Context context,DBHelper dbHelper) {
        this.context = new WeakReference<Context>(context);
        this.dbHelper = dbHelper;
        this.db = dbHelper.getDB();

    }

    public ActivityDAO(Context context) {
        this(context,DBHelper.getInstance(context));
    }

    @Override
    public long insert(@NonNull Activity activity) {
        if (activity == null) {
            return 0;
        }
        // insert



        db.beginTransaction();
        long id = DBHelper.INVALID_ID;
        try {
            id = db.insert(TABLE_ACTIVITY, null, this.getContentValues(activity));
            activity.setId(id);
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }

        return id;
    }

    @Override
    public void update(long id, @NonNull Activity activity) {

    }

    @Override
    public int delete(long id) {

        return db.delete(TABLE_ACTIVITY,  KEY_ACTIVITY_ID + " = " + id, null);
    }

    @Override
    public void deleteAll() {

        db.delete(TABLE_ACTIVITY,  null, null);

    }

    public static @NonNull ContentValues getContentValues(@NonNull Activity activity) {
        final ContentValues content = new ContentValues();

        if (activity == null) {
            return content;
        }
        content.put(KEY_ACTIVITY_NAME, activity.getName());
        content.put(KEY_ACTIVITY_ADDRESS, activity.getAddress());
        content.put(KEY_ACTIVITY_URL, activity.getUrl());
        content.put(KEY_ACTIVITY_DESCRIPTION_ES, activity.getDescriptionES());
        content.put(KEY_ACTIVITY_DESCRIPTION_EN, activity.getDescriptionEN());
        content.put(KEY_ACTIVITY_IMAGE_URL, activity.getImageURL());
        content.put(KEY_ACTIVITY_LOGO_IMAGE_URL, activity.getLogoImageURL());
        content.put(KEY_ACTIVITY_LATITUDE, activity.getLatitude());
        content.put(KEY_ACTIVITY_LONGITUDE, activity.getLongitude());

        return content;
    }


    // convenience method
    public static Activity activityFromCursor(Cursor c) {
        assert c != null;

        Activity a = new Activity(c.getLong(c.getColumnIndex(KEY_ACTIVITY_ID)),c.getString(c.getColumnIndex(KEY_ACTIVITY_NAME)));

        a.setAddress(c.getString(c.getColumnIndex(KEY_ACTIVITY_ADDRESS)));
        a.setDescriptionES(c.getString(c.getColumnIndex(KEY_ACTIVITY_DESCRIPTION_ES)));
        a.setDescriptionEN(c.getString(c.getColumnIndex(KEY_ACTIVITY_DESCRIPTION_EN)));
        a.setUrl(c.getString(c.getColumnIndex(KEY_ACTIVITY_URL)));
        a.setLogoImageURL(c.getString(c.getColumnIndex(KEY_ACTIVITY_LOGO_IMAGE_URL)));
        a.setImageURL(c.getString(c.getColumnIndex(KEY_ACTIVITY_IMAGE_URL)));
        a.setLatitude(c.getDouble(c.getColumnIndex(KEY_ACTIVITY_LATITUDE)));
        a.setLongitude(c.getDouble(c.getColumnIndex(KEY_ACTIVITY_LONGITUDE)));

        return a;
    }

    public @NonNull Cursor queryCursor() {
        // select

        Cursor c = db.query(TABLE_ACTIVITY, allActivityColumns, null, null, null, null, KEY_ACTIVITY_ID);
        if (c != null && c.getCount() > 0){
            c.moveToFirst();
        }

        return c;
    }


    /**
     * Returns an Activity object from its id
     * @param id - the activity id in db
     * @return Activity object if found, null otherwise
     */
    @Override
    public @Nullable Activity query(long id) {
        Activity activity = null;

        String where = KEY_ACTIVITY_ID + "=" + id;
        Cursor c = db.query(TABLE_ACTIVITY, allActivityColumns, where, null, null, null, KEY_ACTIVITY_ID);
        if (c != null) {
            if (c.getCount() > 0) {
                c.moveToFirst();
                activity = activityFromCursor(c);
            }
        }
        c.close();
        return activity;
    }

    @Nullable
    @Override
    public List<Activity> query() {

        Cursor c = queryCursor();
        if (c == null || !c.moveToFirst()) {
            return null;
        }

        List<Activity> activities = new LinkedList<>();
        c.moveToFirst();
        do {

            activities.add(activityFromCursor(c));

        } while (c.moveToNext());


        return activities;
    }
    @NonNull
    public static Activities getActivities(Cursor data) {
        List<Activity> activityList = new LinkedList<>();

        while (data.moveToNext()) {
            Activity activity = ActivityDAO.activityFromCursor(data);
            activityList.add(activity);
        }

        return Activities.build(activityList);
    }

    public static Activity getActivityFromContentValues(ContentValues contentValues) {
        final Activity activity = new Activity(1,contentValues.getAsString(KEY_ACTIVITY_NAME));

        activity.setAddress(contentValues.getAsString(KEY_ACTIVITY_ADDRESS));
        activity.setUrl(contentValues.getAsString(KEY_ACTIVITY_URL));
        activity.setImageURL(contentValues.getAsString(KEY_ACTIVITY_IMAGE_URL));
        activity.setLogoImageURL(contentValues.getAsString(KEY_ACTIVITY_LOGO_IMAGE_URL));
        activity.setLatitude(contentValues.getAsDouble(KEY_ACTIVITY_LATITUDE));
        activity.setLongitude(contentValues.getAsDouble(KEY_ACTIVITY_LONGITUDE));
        activity.setDescriptionES(contentValues.getAsString(KEY_ACTIVITY_DESCRIPTION_ES));
        activity.setDescriptionEN(contentValues.getAsString(KEY_ACTIVITY_DESCRIPTION_EN));

        return activity;
    }

    public Cursor queryCursor(long id) {

        Cursor c = db.query(TABLE_ACTIVITY, allActivityColumns, "WHERE ID = " + id, null, null, null, KEY_ACTIVITY_ID);
        if (c != null && c.getCount() > 0){
            c.moveToFirst();
        }

        return c;

    }
}