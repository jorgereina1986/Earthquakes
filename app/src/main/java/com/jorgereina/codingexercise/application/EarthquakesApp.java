package com.jorgereina.codingexercise.application;

import android.app.Application;

import com.jorgereina.codingexercise.model.DataRepository;

/**
 * Created by jorgereina on 6/19/18.
 */

public class EarthquakesApp extends Application {

    private DataRepository dataRepository;

    @Override
    public void onCreate() {
        super.onCreate();
        dataRepository = new DataRepository();
    }

    public DataRepository getDataRepository() {
        return dataRepository;
    }

    public void setDataRepository(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }
}
