package com.example.fran.madridguide.views;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fran.madridguide.MadridGuideApp;
import com.example.fran.madridguide.R;
import com.example.fran.madridguide.model.Shop;
import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;

/**
 * Created by fran on 13/1/17.
 */

public class ShopRowViewHolder extends RecyclerView.ViewHolder {

    private TextView nameTextView;

    private ImageView logoImageView;

    WeakReference<Context> context;


    public ShopRowViewHolder(View rowShop) {
        super(rowShop);
        context = new WeakReference<Context>(rowShop.getContext());
        nameTextView = (TextView) rowShop.findViewById(R.id.row_shop_name);

        logoImageView = (ImageView) rowShop.findViewById(R.id.row_shop_logo);
    }

    public void setShop(final @NonNull Shop shop){

        if (shop == null){
            return;
        }

        nameTextView.setText(shop.getName());

        Picasso.with(context.get())
                .load(shop.getLogoImageURL())
                .placeholder(android.R.drawable.ic_menu_search)
                .into(logoImageView);
    }
}
