package com.example.fran.madridguide.interactors;

import android.content.Context;


/**
 * Created by fran on 15/1/17.
 */

public interface IGetAllDataInteractor<T> {

    public void execute(final Context context, final InteractorCompletion<T> completion);
    public void executeFromCache(final Context context, final InteractorCompletion<T> completion);


}
