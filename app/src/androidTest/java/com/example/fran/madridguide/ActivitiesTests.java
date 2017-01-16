package com.example.fran.madridguide;

import android.support.annotation.NonNull;
import android.test.AndroidTestCase;

import com.example.fran.madridguide.model.Activity;
import com.example.fran.madridguide.model.Activities;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fran on 13/1/17.
 */

public class ActivitiesTests extends AndroidTestCase {

    private static final String ACTIVITY = "activity";
    private static final String ADDRESS = "ADDRESS";

    public void testCanCreateActivitiesWithNullReturnsNotNull() {
        Activities sut = Activities.build(null);

        assertNotNull(sut);
        assertNotNull(sut.allElements());

    }
    public void testCanCreateActivitiesWithAListNReturnsNotNull() {

        List<Activity> data = getActivities();

        Activities sut = Activities.build(data);

        assertNotNull(sut);
        assertNotNull(sut.allElements());
        assertEquals(sut.allElements(),data);
        assertEquals(sut.allElements().size(),data.size());
    }

    @NonNull
    private List<Activity> getActivities() {
        List<Activity> data = new ArrayList<>();
        data.add(new Activity(0,ACTIVITY).setAddress(ADDRESS));
        data.add(new Activity(10,ACTIVITY).setAddress(ADDRESS));
        return data;
    }
}
