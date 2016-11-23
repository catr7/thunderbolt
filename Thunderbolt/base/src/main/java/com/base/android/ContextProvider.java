package com.base.android;

import android.app.Application;
import android.content.Context;

/**
 * Created by Andres y Jess on 18/11/2016.
 */

public class ContextProvider extends Application {

    private Object object;
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}

