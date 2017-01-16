package com.example.fran.madridguide.adapters;

import android.content.Context;
import android.view.View;

import com.example.fran.madridguide.views.ActivityInfoWindow;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

import java.lang.ref.WeakReference;

/**
 * Created by fran on 16/1/17.
 */

public class ActivityInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {

    private WeakReference<Context> context;

    public ActivityInfoWindowAdapter(Context context) {
        this.context = new WeakReference<Context>(context);
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        ActivityInfoWindow infoWindow = new ActivityInfoWindow(context.get());
        infoWindow.setInfoView(marker);

        return infoWindow;
    }
}
