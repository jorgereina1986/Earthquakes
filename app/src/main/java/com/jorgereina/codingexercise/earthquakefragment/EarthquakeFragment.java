package com.jorgereina.codingexercise.earthquakefragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.jorgereina.codingexercise.R;
import com.jorgereina.codingexercise.model.Earthquake;

import java.util.List;

import static com.jorgereina.codingexercise.earthquakefragment.EarthquakeFragmentContract.*;


public class EarthquakeFragment extends Fragment
        implements EarthquakeFragmentContract.View, EarthquakeAdapter.SelectedItemListener {

    private static final String TAG = "lagarto";
    private EarthquakeAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    private Presenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_earthquake, container, false);
        presenter = new EarthquakeFragmentPresenterImpl(this);
        recyclerView = view.findViewById(R.id.earthquake_rv);
        progressBar = view.findViewById(R.id.progress_bar);
        presenter.fetchEarthquakesTask();
        return view;
    }

    @Override
    public void setRecyclerView(List<Earthquake> earthquakes) {
        adapter = new EarthquakeAdapter(earthquakes, presenter, this);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void goToDetailsFragment(Earthquake earthquake) {
        EarthquakeDetailsFragment detailsFragment = EarthquakeDetailsFragment.newInstance(earthquake);
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().add(R.id.fragment_container_2, detailsFragment, "earthquakedetails").addToBackStack(null).commit();
    }

    @Override
    public Context mGetContext() {
        return getActivity();
    }


    @Override
    public void onItemSelected(int index) {
        Log.d(TAG, "onItemSelected: " + index);

    }
}
