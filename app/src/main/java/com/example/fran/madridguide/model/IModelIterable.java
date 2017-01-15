package com.example.fran.madridguide.model;

import java.util.List;

/**
 * Created by fran on 12/1/17.
 */
public interface IModelIterable {


    long size();
    Shop get(long index);
    List<Shop> allShops();

}
