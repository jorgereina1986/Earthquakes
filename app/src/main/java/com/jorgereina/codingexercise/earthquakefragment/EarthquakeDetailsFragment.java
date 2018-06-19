package com.jorgereina.codingexercise.earthquakefragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jorgereina.codingexercise.R;
import com.jorgereina.codingexercise.model.Earthquake;

public class EarthquakeDetailsFragment extends Fragment {

    private static final String EARTHQUAKE_PARCEL = "earthquake_parcel";

    private TextView locationTv;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_earthquake_details, container, false);
        locationTv = view.findViewById(R.id.location_tv);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle bundle = this.getArguments();
        Earthquake earthquake = bundle.getParcelable(EARTHQUAKE_PARCEL);

        locationTv.setText(earthquake.getPlace());
    }

    public static EarthquakeDetailsFragment newInstance(Earthquake earthquake) {
        Bundle args = new Bundle();
        args.putParcelable(EARTHQUAKE_PARCEL, earthquake);

        EarthquakeDetailsFragment fragment = new EarthquakeDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
