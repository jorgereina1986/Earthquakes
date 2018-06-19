package com.jorgereina.codingexercise.earthquakefragment;

import com.jorgereina.codingexercise.model.Earthquake;

import java.util.List;

public interface EarthquakeFragmentContract {

    interface View {
        void setRecyclerViewData(List<Earthquake> earthquakes);
        void hideProgress();
        void goToDetailsFragment(Earthquake earthquake);

    }

    interface Presenter {
        void fetchEarthquakesTask();
    }
}
