package com.example.fran.madridguide.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fran.madridguide.R;
import com.example.fran.madridguide.model.Shop;
import com.example.fran.madridguide.util.Constants;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShopDetailActivity extends AppCompatActivity {

    private Shop shop;

    @BindView(R.id.shop_detail_logo)
    ImageView logoImageView;

    @BindView(R.id.shop_detail_name)
    TextView nameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_detail);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        shop = (Shop) intent.getSerializableExtra(Constants.INTENT_KEY_DETAIL_SHOP);

        if (shop != null) {
            nameTextView.setText(shop.getName());
            Picasso.with(this).load(shop.getLogoImageURL()).into(logoImageView);
        }

    }
}
