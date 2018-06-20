package com.jorgereina.codingexercise.earthquakefragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.jorgereina.codingexercise.R;
import com.jorgereina.codingexercise.application.EarthquakesApp;
import com.jorgereina.codingexercise.model.Earthquake;

import static com.jorgereina.codingexercise.earthquakefragment.EarthquakeFragmentContract.*;


public class EarthquakeFragment extends Fragment
        implements EarthquakeFragmentContract.View {

    private static final String TAG = EarthquakeFragment.class.getSimpleName();
    private EarthquakeAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;


    private Presenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_earthquake, container, false);
        EarthquakesApp earthquakesApp = (EarthquakesApp) getActivity().getApplication();
        presenter = new EarthquakeFragmentPresenter(this, earthquakesApp.getDataRepository());
        recyclerView = view.findViewById(R.id.earthquake_rv);
        progressBar = view.findViewById(R.id.progress_bar);
        adapter = new EarthquakeAdapter(presenter);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        presenter.loadEarthquakesData();
        return view;
    }

    @Override
    public void onEarthquakeDataLoaded() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showEarthquakeDetails(Earthquake earthquake) {

        EarthquakeDetailsFragment detailsFragment = EarthquakeDetailsFragment.newInstance(earthquake);
        FragmentManager fragmentManager = getFragmentManager();
        if (getActivity().findViewById(R.id.fragment_container_2) == null) {
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, detailsFragment, "earthquakedetails")
                    .addToBackStack(null)
                    .commit();
        }
        else {
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container_2, detailsFragment, "earthquakedetails")
                    .commit();
        }
    }
}
