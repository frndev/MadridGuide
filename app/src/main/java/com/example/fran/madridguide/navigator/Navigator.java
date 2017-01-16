package com.example.fran.madridguide.navigator;

import android.content.Intent;

import com.example.fran.madridguide.MadridGuideApp;
import com.example.fran.madridguide.activities.ActivitiesActivity;
import com.example.fran.madridguide.activities.ActivityDetailActivity;
import com.example.fran.madridguide.activities.MainActivity;
import com.example.fran.madridguide.activities.ShopDetailActivity;
import com.example.fran.madridguide.activities.ShopsActivity;
import com.example.fran.madridguide.model.Activity;
import com.example.fran.madridguide.model.Shop;
import com.example.fran.madridguide.util.Constants;

/**
 * Created by fran on 8/1/17.
 */
public class Navigator {

    public static Intent navigateFromMainActivityToShopsActivity(final MainActivity mainActivity) {

        final Intent i = new Intent(mainActivity, ShopsActivity.class);

        mainActivity.startActivity(i);

        return i;

    }

    public static Intent navigateFromShopsActivityToDetailShopsActivity(Shop shop, final ShopsActivity shopsActivity) {

        final Intent i = new Intent(shopsActivity, ShopDetailActivity.class);

        i.putExtra(Constants.INTENT_KEY_DETAIL_SHOP,shop);

        shopsActivity.startActivity(i);

        return i;

    }


    public static Intent navigateFromMainActivityToActivitiesActivity(MainActivity mainActivity) {

        final Intent i = new Intent(mainActivity, ActivitiesActivity.class);

        mainActivity.startActivity(i);

        return i;
    }

    public static Intent navigateFromShopsActivityToDetailActivityActivity(Activity activity, ActivitiesActivity activitiesActivity) {

        final Intent i = new Intent(activitiesActivity, ActivityDetailActivity.class);

        i.putExtra(Constants.INTENT_KEY_DETAIL_ACTIVITY,activity);

        activitiesActivity.startActivity(i);

        return i;


    }
}
