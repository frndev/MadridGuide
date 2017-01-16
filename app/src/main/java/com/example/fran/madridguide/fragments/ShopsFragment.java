package com.example.fran.madridguide.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fran.madridguide.R;
import com.example.fran.madridguide.adapters.ShopsAdapter;
import com.example.fran.madridguide.model.Shop;
import com.example.fran.madridguide.model.Shops;
import com.example.fran.madridguide.views.OnElementClick;

import java.util.ArrayList;
import java.util.List;

public class ShopsFragment extends Fragment {


    private RecyclerView shopsRecyclerView;
    private ShopsAdapter adapter;
    private Shops shops;
    private OnElementClick<Shop> listener;


    public ShopsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shops, container, false);
        shopsRecyclerView = (RecyclerView) view.findViewById(R.id.shops_recycler_view);

        shopsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));



        return view;
    }

    private void updateUI() {

        Shops shops = getShops();

        adapter = new ShopsAdapter(shops,getActivity());

        adapter.setOnElementClickListener(new OnElementClick<Shop>() {
            @Override
            public void elementClicked(Shop shop, int position) {
                if (listener != null) {
                    listener.elementClicked(shop,position);
                }
            }
        });

        shopsRecyclerView.setAdapter(adapter);

    }



    public void setOnElementClickListener(OnElementClick<Shop> listener) {

       this.listener = listener;

    }

    public Shops getShops() {
        return shops;
    }

    public void setShops(Shops shops) {
        this.shops = shops;
        updateUI();
    }
}
