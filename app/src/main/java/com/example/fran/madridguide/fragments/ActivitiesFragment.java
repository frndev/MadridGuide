package com.example.fran.madridguide.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fran.madridguide.R;
import com.example.fran.madridguide.adapters.ActivitiesAdapter;
import com.example.fran.madridguide.model.Activities;
import com.example.fran.madridguide.model.Activity;
import com.example.fran.madridguide.views.OnElementClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class ActivitiesFragment extends Fragment {

    private RecyclerView activitiesRecyclerView;
    private ActivitiesAdapter adapter;
    private Activities activities;
    private OnElementClick<Activity> listener;


    public ActivitiesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_activities, container, false);
        activitiesRecyclerView = (RecyclerView) view.findViewById(R.id.activities_recycler_view);

        activitiesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));



        return view;
    }

    private void updateUI() {

        Activities activities = getActivities();

        adapter = new ActivitiesAdapter(activities,getActivity());

        adapter.setOnElementClickListener(new OnElementClick<Activity>() {
            @Override
            public void elementClicked(Activity activity, int position) {
                if (listener != null) {
                    listener.elementClicked(activity,position);
                }
            }
        });

        activitiesRecyclerView.setAdapter(adapter);

    }



    public void setOnElementClickListener(OnElementClick<Activity> listener) {

        this.listener = listener;

    }

    public Activities getActivities() {
        return activities;
    }

    public void setActivities(Activities activities) {
        this.activities = activities;
        updateUI();
    }

}
