package com.example.fran.madridguide.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.fran.madridguide.R;
import com.example.fran.madridguide.model.Activity;
import com.example.fran.madridguide.model.Shop;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.lang.ref.WeakReference;

/**
 * Created by fran on 15/1/17.
 */

public class ActivityRowViewHolder extends RecyclerView.ViewHolder {

    WeakReference<Context> context;

    ImageView logoImageView;
    TextView nameTextView;

    RelativeLayout relativeLayout;


    public ActivityRowViewHolder(View rowActivity) {
        super(rowActivity);

        context = new WeakReference<Context>(rowActivity.getContext());
        nameTextView = (TextView) rowActivity.findViewById(R.id.row_activity_name);

        logoImageView = (ImageView) rowActivity.findViewById(R.id.row_activity_logo);

        relativeLayout = (RelativeLayout) rowActivity.findViewById(R.id.activities_layout);
    }

    public void setActivity(final @NonNull Activity activity){

        if (activity == null){
            return;
        }

        nameTextView.setText(activity.getName());

        Picasso.with(context.get())
                .load(activity.getLogoImageURL())
                .placeholder(android.R.drawable.ic_menu_search)
                .into(logoImageView);

        Picasso.with(context.get())
                .load(activity.getImageURL())
                .into(new Target() {

                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        relativeLayout.setBackgroundDrawable(new BitmapDrawable(context.get().getResources(), bitmap));
                    }

                    @Override
                    public void onBitmapFailed(final Drawable errorDrawable) {
                        Log.d("TAG", "FAILED");
                    }

                    @Override
                    public void onPrepareLoad(final Drawable placeHolderDrawable) {
                        Log.d("TAG", "Prepare Load");
                    }
                });
    }
}
