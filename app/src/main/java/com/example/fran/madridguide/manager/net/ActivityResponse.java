package com.example.fran.madridguide.manager.net;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by fran on 15/1/17.
 */
public class ActivityResponse implements  ObjectResponse<ActivityEntity>{

    @SerializedName("result")
    List<ActivityEntity> result;

    @Override
    public List<ActivityEntity> result() {
        return result;
    }
}
