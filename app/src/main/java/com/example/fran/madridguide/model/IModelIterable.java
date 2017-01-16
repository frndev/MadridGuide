package com.example.fran.madridguide.model;

import java.util.List;

/**
 * Created by fran on 12/1/17.
 */
public interface IModelIterable<T> {


    long size();
    T get(long index);
    List<T> allElements();

}
