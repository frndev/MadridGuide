package com.example.fran.madridguide.views;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.fran.madridguide.R;
import com.example.fran.madridguide.model.Activity;
import com.google.android.gms.maps.model.Marker;
import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;

/**
 * Created by fran on 16/1/17.
 */

public class ActivityInfoWindow extends RelativeLayout {

    WeakReference<Context> context;

    ImageView logoImageView;
    TextView nameTextView;

    public ActivityInfoWindow(Context context) {
        super(context);
        this.context = new WeakReference<Context>(context);
        inflate(context,R.layout.activity_info_window,this);
        logoImageView = (ImageView) findViewById(R.id.activity_info_window_image_view);
        nameTextView = (TextView) findViewById(R.id.activity_info_window_text_view);
    }


    public void setInfoView(Marker marker) {
        Picasso.with(context.get())
                .load(marker.getSnippet().split("activity:")[0])
                .placeholder(android.R.drawable.ic_menu_search)
                .into(logoImageView);

        nameTextView.setText(marker.getTitle());
    }


}
