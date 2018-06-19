package com.jorgereina.codingexercise.earthquakefragment;

import android.app.Fragment;
import android.content.Context;

import com.jorgereina.codingexercise.model.Earthquake;

import java.util.List;

public interface EarthquakeFragmentContract {

    interface View {
        void setRecyclerView(List<Earthquake> earthquakes);
        void hideProgress();
        void goToDetailsFragment(Earthquake earthquake);
        //handles fragment launch

        Context mGetContext();
    }

    interface Presenter {
        void fetchEarthquakesTask();
        void onEarthquakeSelected(int index);
        //handles onclick???
    }
}
