package com.example.fran.madridguide.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fran.madridguide.R;
import com.example.fran.madridguide.model.Activities;
import com.example.fran.madridguide.model.Activity;

import com.example.fran.madridguide.views.ActivityRowViewHolder;
import com.example.fran.madridguide.views.OnElementClick;
import com.example.fran.madridguide.views.ActivityRowViewHolder;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by fran on 15/1/17.
 */

public class ActivitiesAdapter extends RecyclerView.Adapter<ActivityRowViewHolder> {

    private final LayoutInflater layoutInflater;
    private final Activities activities;

    private List<OnElementClick<Activity>> listeners;

    public ActivitiesAdapter(Activities activities,Context context) {
        this.activities= activities;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public ActivityRowViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.row_activity,parent,false);

        return new ActivityRowViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ActivityRowViewHolder row, final int position) {

        final Activity activity = activities.get(position);

        row.setActivity(activity);

        row.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                for (OnElementClick<Activity> listener : getListeners()) {
                    listener.elementClicked(activity,position);
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return (int) activities.size();
    }

    public List<OnElementClick<Activity>> getListeners() {

        if (listeners == null) {
            listeners = new LinkedList<>();
        }

        return listeners;
    }

    public void setOnElementClickListener(OnElementClick<Activity> listener) {

        getListeners().add(listener);

    }
}
