package com.jorgereina.codingexercise;

import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jorgereina.codingexercise.earthquakefragment.EarthquakeFragment;

public class EarthquakeActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (savedInstanceState != null) {
            return;
        }
        if (findViewById(R.id.fragment_container) != null) {

            EarthquakeFragment earthquakeFragment = new EarthquakeFragment();
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()
                    .add(R.id.fragment_container, earthquakeFragment, "EarthquakeFragment")
                    .commit();

        }

    }

}
