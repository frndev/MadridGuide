package com.example.fran.madridguide.model;

/**
 * Created by fran on 12/1/17.
 */
public interface IModelUpdatable<T> {

    void add(T element);
    void delete(T element);
    void edit(T newElement, long index);

}
