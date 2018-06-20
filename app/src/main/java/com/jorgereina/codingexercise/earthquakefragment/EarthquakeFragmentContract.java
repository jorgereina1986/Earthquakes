package com.jorgereina.codingexercise.earthquakefragment;

import com.jorgereina.codingexercise.model.Earthquake;

import java.util.List;

public interface EarthquakeFragmentContract {

    interface View {
        void onEarthquakeDataLoaded();

        void showProgress();

        void hideProgress();

        void showEarthquakeDetails(Earthquake earthquake);
    }

    interface Presenter {
        void loadEarthquakesData();

        int getEarthquakesCount();

        Earthquake getEarthquakeData(int position);

        void earthquakeSelected(int position);
    }
}
