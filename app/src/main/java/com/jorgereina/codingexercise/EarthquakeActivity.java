package com.jorgereina.codingexercise;

import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.jorgereina.codingexercise.earthquakefragment.EarthquakeAdapter;
import com.jorgereina.codingexercise.earthquakefragment.EarthquakeDetailsFragment;
import com.jorgereina.codingexercise.earthquakefragment.EarthquakeFragment;

public class EarthquakeActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (findViewById(R.id.fragment_container) != null) {

            if (savedInstanceState != null) {
                return;
            }

            EarthquakeFragment earthquakeFragment = new EarthquakeFragment();
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()
                    .add(R.id.fragment_container, earthquakeFragment, "EarthquakeFragment")
                    .commit();
        }
        else if (findViewById(R.id.fragment_container_2) != null) {

            if (savedInstanceState != null) {
                return;
            }
            EarthquakeDetailsFragment earthquakeDetailsFragment = new EarthquakeDetailsFragment();
            getFragmentManager().beginTransaction().add(R.id.fragment_container_2, earthquakeDetailsFragment, "details").commit();

        }

    }

}
