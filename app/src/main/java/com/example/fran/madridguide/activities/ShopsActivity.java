package com.example.fran.madridguide.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.fran.madridguide.R;
import com.example.fran.madridguide.fragments.ShopsFragment;
import com.example.fran.madridguide.interactors.GetAllShopsInteractor;
import com.example.fran.madridguide.interactors.InteractorCompletion;
import com.example.fran.madridguide.model.Shop;
import com.example.fran.madridguide.model.Shops;
import com.example.fran.madridguide.navigator.Navigator;
import com.example.fran.madridguide.views.OnElementClick;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class ShopsActivity extends AppCompatActivity {

    private ShopsFragment shopsFragment;
    private MapFragment mapFragment;
    private GoogleMap googleMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shops);

        shopsFragment = (ShopsFragment) getSupportFragmentManager().findFragmentById(R.id.activity_shops_fragment_shops);
        mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);




        GetAllShopsInteractor interactor = new GetAllShopsInteractor();
        interactor.executeFromCache(this, new InteractorCompletion<Shops>() {
                    @Override
                    public void completion(Shops shops) {
                        shopsFragment.setShops(shops);
                        setUpMap(shops);

                    }
        });


        shopsFragment.setOnElementClickListener(new OnElementClick<Shop>() {
            @Override
            public void elementClicked(Shop shop, int position) {

                Navigator.navigateFromShopsActivityToDetailShopsActivity(shop, ShopsActivity.this);


            }
        });

    }

    private void setUpMap(Shops shops) {

        googleMap = mapFragment.getMap();

        if (googleMap == null) {
            Toast.makeText(getApplicationContext(), "Sorry! unable to create maps", Toast.LENGTH_SHORT)
                    .show();
            return;
        }
        googleMap.setMyLocationEnabled(true);

        if (shops == null) {
            return;
        }

        for (Shop shop : shops.allElements()) {
            // create marker
            MarkerOptions marker = new MarkerOptions().position(new LatLng(shop.getLatitude(), shop.getLongitude())).title(shop.getName());
            // adding marker
            googleMap.addMarker(marker);
        }

        moveCamera();

    }

    private void moveCamera() {
        CameraPosition cameraPosition = new CameraPosition.Builder().target(
                new LatLng(40.4189886, -3.7047680000000500)).zoom(12).build();
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

}
