package com.example.fran.madridguide.model.mappers;

import com.example.fran.madridguide.manager.net.ActivityEntity;
import com.example.fran.madridguide.model.Activity;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by fran on 15/1/17.
 */

public class ActivityEntityActivityMapper {

    public List<Activity> map(List<ActivityEntity> activityEntities) {

        List<Activity> activityList = new LinkedList<>();

        for(ActivityEntity entity : activityEntities) {

            Activity activity = new Activity(entity.getId(),entity.getName());
            activity.setDescription(entity.getDescriptionES());
            activity.setAddress(entity.getAddress());
            activity.setUrl(entity.getUrl());
            activity.setLogoImageURL(entity.getLogoImg());
            activity.setLatitude(entity.getLatitude());
            activity.setLongitude(entity.getLongitude());
            activity.setImageURL(entity.getImg());

            activityList.add(activity);

        }

        return activityList;

    }
}
