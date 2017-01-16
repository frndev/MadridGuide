package com.example.fran.madridguide.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.fran.madridguide.R;
import com.example.fran.madridguide.adapters.ActivityInfoWindowAdapter;
import com.example.fran.madridguide.fragments.ActivitiesFragment;
import com.example.fran.madridguide.interactors.GetAllActivitiesInteractor;
import com.example.fran.madridguide.interactors.InteractorCompletion;
import com.example.fran.madridguide.model.Activities;
import com.example.fran.madridguide.model.Activity;
import com.example.fran.madridguide.navigator.Navigator;
import com.example.fran.madridguide.views.OnElementClick;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class ActivitiesActivity extends AppCompatActivity {

    private ActivitiesFragment activitiesFragment;
    private MapFragment mapFragment;
    private GoogleMap googleMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activities);

        activitiesFragment = (ActivitiesFragment) getSupportFragmentManager().findFragmentById(R.id.activity_activities_fragment_activities);
        mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.activities_map);

        googleMap = mapFragment.getMap();

        GetAllActivitiesInteractor interactor = new GetAllActivitiesInteractor();
        interactor.executeFromCache(this, new InteractorCompletion<Activities>() {
            @Override
            public void completion(Activities activities) {
                activitiesFragment.setActivities(activities);
                setUpMap(activities);
            }
        });


        activitiesFragment.setOnElementClickListener(new OnElementClick<Activity>() {
            @Override
            public void elementClicked(Activity activity, int position) {

                Navigator.navigateFromActivitiesActivityToDetailActivityActivity(activity, ActivitiesActivity.this);


            }
        });

    }

    private void setUpMap(final Activities activities) {

        googleMap = mapFragment.getMap();

        if (googleMap == null) {
            Toast.makeText(getApplicationContext(), "Sorry! unable to create maps", Toast.LENGTH_SHORT)
                    .show();
            return;
        }
        googleMap.setMyLocationEnabled(true);

        if (activities == null) {
            return;
        }

        for (Activity activity : activities.allElements()) {
            // create marker
            MarkerOptions marker = new MarkerOptions().position(new LatLng(activity.getLatitude(), activity.getLongitude())).title(activity.getName());
            marker.snippet(activity.getLogoImageURL() + "activity:" + activities.indexOf(activity));

            // adding marker
            googleMap.addMarker(marker);
        }

        googleMap.getUiSettings().setZoomGesturesEnabled(false);
        googleMap.getUiSettings().setZoomControlsEnabled(false);
        googleMap.getUiSettings().setAllGesturesEnabled(false);
        googleMap.getUiSettings().setMapToolbarEnabled(false);
        googleMap.setInfoWindowAdapter(new ActivityInfoWindowAdapter(getApplicationContext()));
        googleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                long position = Integer.parseInt(marker.getSnippet().split("activity:")[1]);
                Activity activity = activities.get(position);
                Navigator.navigateFromActivitiesActivityToDetailActivityActivity(activity,ActivitiesActivity.this);
            }
        });
        moveCamera();

    }

    private void moveCamera() {
        CameraPosition cameraPosition = new CameraPosition.Builder().target(
                new LatLng(40.4189886, -3.7047680000000500)).zoom(12).build();
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }
}
