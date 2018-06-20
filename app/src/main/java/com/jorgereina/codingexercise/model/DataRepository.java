package com.jorgereina.codingexercise.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jorgereina on 6/19/18.
 */

public class DataRepository {
    private List<Earthquake> earthquakes = new ArrayList<>();

    public List<Earthquake> getEarthquakes() {
        return earthquakes;
    }

    public void setEarthquakes(List<Earthquake> earthquakes) {
        this.earthquakes = earthquakes;

    }

    public int getEarthquakeCount() {
        if (earthquakes == null) return 0;
        return earthquakes.size();
    }

    public Earthquake getData(int position) {
        return earthquakes.get(position);
    }
}
