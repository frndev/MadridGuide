package com.example.fran.madridguide.interactors;


import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.example.fran.madridguide.manager.db.ActivityDAO;
import com.example.fran.madridguide.model.Activities;
import com.example.fran.madridguide.model.Activity;

/**
 * Created by fran on 15/1/17.
 */

public class CacheAllActivitiesInteractor implements ICacheAllDataInteractor<Activities> {
    @Override
    public void execute(final Context context, final Activities activities, final CacheCompletion completion) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ActivityDAO dao = new ActivityDAO(context);
                boolean success = true;
                for (Activity activity : activities.allElements()){
                    success = dao.insert(activity) > 0;
                    if (!success){
                        break;
                    }
                }

                Handler handler = new Handler(Looper.getMainLooper());
                final boolean finalSuccess = success;
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (completion != null) {
                            completion.completion(finalSuccess);
                        }
                    }
                });

            }
        }).start();
    }
}
