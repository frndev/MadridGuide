package com.example.fran.madridguide.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fran.madridguide.R;
import com.example.fran.madridguide.model.Activity;
import com.example.fran.madridguide.util.Constants;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ActivityDetailActivity extends AppCompatActivity {

    private Activity activity;


    @BindView(R.id.activity_detail_name)
    TextView nameTextView;

    @BindView(R.id.activity_detail_address)
    TextView addressTextView;

    @BindView(R.id.activity_detail_description)
    TextView descriptionTextView;

    private MapFragment mapFragment;
    private GoogleMap googleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        activity = (Activity) intent.getSerializableExtra(Constants.INTENT_KEY_DETAIL_ACTIVITY);

        if (activity != null) {
            nameTextView.setText(activity.getName());
            addressTextView.setText(activity.getAddress());


            if (Locale.getDefault().getLanguage().equals(Locale.ENGLISH.getLanguage())){
                descriptionTextView.setText(activity.getDescriptionEN());
            }else{
                descriptionTextView.setText(activity.getDescriptionES());

            }
        }

        mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.activities_map);


        setUpMap(activity);

    }

    private void setUpMap(Activity activity) {

        googleMap = mapFragment.getMap();

        if (googleMap == null) {
            Toast.makeText(getApplicationContext(), "Sorry! unable to create maps", Toast.LENGTH_SHORT)
                    .show();
            return;
        }
        googleMap.setMyLocationEnabled(true);

        if (activity == null) {
            return;
        }


        googleMap.getUiSettings().setZoomGesturesEnabled(false);
        googleMap.getUiSettings().setZoomControlsEnabled(false);
        googleMap.getUiSettings().setAllGesturesEnabled(false);
        googleMap.getUiSettings().setMapToolbarEnabled(false);


        moveCamera(activity);

    }

    private void moveCamera(Activity activity) {
        CameraPosition cameraPosition = new CameraPosition.Builder().target(
                new LatLng(activity.getLatitude(), activity.getLongitude())).zoom(12).build();
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }
}
