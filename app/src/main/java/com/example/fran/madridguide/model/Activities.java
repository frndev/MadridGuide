package com.example.fran.madridguide.model;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fran on 15/1/17.
 */

public class Activities implements IModelIterable<Activity>,IModelUpdatable<Activity> {

    private List<Activity> activities = new ArrayList<>();

    public Activities(List<Activity> activities) {
        this.activities = activities;
    }

    private Activities() {
        activities = new ArrayList<>();
    }

    public static @NonNull Activities build(@NonNull final List<Activity> activitiesList) {

        Activities activities = new Activities(activitiesList);
        if (activitiesList == null){
            activities.activities = new ArrayList<>();
        }

        return activities;

    }

    public static Activities build() {
        return build(new ArrayList<Activity>());
    }


    @Override
    public long size() {
        return activities.size();
    }

    @Override
    public Activity get(long index) {
        return activities.get((int) index);
    }

    @Override
    public List<Activity> allElements() {
        return activities;
    }

    @Override
    public long indexOf(Activity element) {
        for (int i = 0; i < allElements().size(); i++) {
            if (allElements().get(i).equals(element)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void add(Activity activity) {
        activities.add(activity);

    }

    @Override
    public void delete(Activity activity) {
        activities.remove(activity);
    }

    @Override
    public void edit(Activity newActivity, long index) {
        activities.set((int) index,newActivity);
    }
}
