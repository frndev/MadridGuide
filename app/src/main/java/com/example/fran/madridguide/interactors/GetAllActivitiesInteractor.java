package com.example.fran.madridguide.interactors;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;

import com.example.fran.madridguide.R;
import com.example.fran.madridguide.manager.db.ActivityDAO;
import com.example.fran.madridguide.manager.net.ActivityEntity;
import com.example.fran.madridguide.manager.net.NetworkManager;
import com.example.fran.madridguide.model.Activities;
import com.example.fran.madridguide.model.Activity;

import com.example.fran.madridguide.model.mappers.ActivityEntityActivityMapper;
import com.example.fran.madridguide.util.Tools;


import java.util.List;

/**
 * Created by fran on 15/1/17.
 */

public class GetAllActivitiesInteractor implements IGetAllDataInteractor<Activities>{


    public void execute(final Context context, final InteractorCompletion<Activities> completion) {

        if (Tools.isActivitiesDownloaded(context)) {
            executeFromCache(context,completion);
        }else{
            executeFromInternet(context,completion);
        }

    }

    private void executeFromInternet(final Context context, final InteractorCompletion<Activities> completion) {

        Activities activities = null;

        NetworkManager nm = new NetworkManager(context);
        nm.getActivitiesFromServer(new NetworkManager.GetActivitiesListener() {
            @Override
            public void getActivitiesCompletion(List<ActivityEntity> result) {
                List<Activity> activityList = new ActivityEntityActivityMapper().map(result);
                if (result != null) {
                    completion.completion(Activities.build(activityList));
                }

                Tools.activityDataDownloaded(context);

            }

            @Override
            public void getActivitiesEntitiesDidFail() {

            }
        });
    }


    public void executeFromCache(final Context context, final InteractorCompletion<Activities> completion) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                ActivityDAO dao = new ActivityDAO(context);

                final List<Activity> activityList = dao.query();

                final Activities activities = Activities.build(activityList);
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        completion.completion(activities);
                    }
                });

            }
        }).start();

    }
}
