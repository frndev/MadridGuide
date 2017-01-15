package com.example.fran.madridguide.model;

/**
 * Created by fran on 12/1/17.
 */
public interface IModelUpdatable {

    void add(Shop shop);
    void delete(Shop shop);
    void edit(Shop newShop, long index);

}
