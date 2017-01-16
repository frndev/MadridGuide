package com.example.fran.madridguide.activities;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.fran.madridguide.R;
import com.example.fran.madridguide.model.Activity;
import com.example.fran.madridguide.navigator.Navigator;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.activity_main_shops_button)
    Button shopsButton;

    @BindView(R.id.activity_main_activities_button)
    Button activitiesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setupShopsButton();

        setupActivitiesButton();


        if (isInternetAvailable(this)) {
            Toast.makeText(MainActivity.this,"No Internet Connection", Toast.LENGTH_LONG).show();

        }


    }

    private void setupActivitiesButton() {

        activitiesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Navigator.navigateFromMainActivityToActivitiesActivity(MainActivity.this);

            }
        });

    }

    private void setupShopsButton() {

        shopsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Navigator.navigateFromMainActivityToShopsActivity(MainActivity.this);

            }
        });

    }

    private static boolean isInternetAvailable(Context context) {
        NetworkInfo info = (NetworkInfo) ((ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();

        if (info == null) {

            return false;
        }
        return true;
    }
}
