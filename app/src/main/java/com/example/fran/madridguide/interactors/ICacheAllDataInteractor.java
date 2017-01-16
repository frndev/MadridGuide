package com.example.fran.madridguide.interactors;

import android.content.Context;

/**
 * Created by fran on 15/1/17.
 */

public interface ICacheAllDataInteractor<T> {

    public void execute(final Context context, final T elements, final CacheCompletion completion);
}
