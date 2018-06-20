package com.jorgereina.codingexercise.earthquakefragment;

import android.os.AsyncTask;
import android.util.Log;

import com.jorgereina.codingexercise.earthquakefragment.EarthquakeFragmentContract.Presenter;
import com.jorgereina.codingexercise.earthquakefragment.EarthquakeFragmentContract.View;
import com.jorgereina.codingexercise.model.DataRepository;
import com.jorgereina.codingexercise.model.Earthquake;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class EarthquakeFragmentPresenter implements Presenter {

//    private List<Earthquake> earthquakes = new ArrayList<>();
    private DataRepository dataRepository;

    private View view;

    public EarthquakeFragmentPresenter(View view, DataRepository dataRepository) {
        this.view = view;
        this.dataRepository = dataRepository;
    }

    @Override
    public void loadEarthquakesData() {
        if (dataRepository.getEarthquakeCount() > 0) {
            view.onEarthquakeDataLoaded();
            return;
        }

        view.showProgress();
        FetchEarthquakesTask fetchEarthquakesTask = new FetchEarthquakesTask();
        fetchEarthquakesTask.execute();
    }

    @Override
    public int getEarthquakesCount() {
        return dataRepository.getEarthquakeCount();
    }

    @Override
    public Earthquake getEarthquakeData(int position) {
        return dataRepository.getData(position);
    }

    @Override
    public void earthquakeSelected(int position) {
        view.showEarthquakeDetails(dataRepository.getData(position));
    }

    public class FetchEarthquakesTask extends AsyncTask<Void, Integer, JSONObject> {
        private static final String EARTHQUAKE_URL = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2018-05-14&endtime=2018-06-14";
        private static final String TAG = "lagarto";


        //TODO: GET DATE!

        @Override
        protected JSONObject doInBackground(Void... voids) {

            URL url = null;
            HttpsURLConnection httpsURLConnection = null;
            JSONObject jsonObject = null;

            try {
//            url = new URL(EARTHQUAKE_URL + START_DATE + END_DATE);
                url = new URL(EARTHQUAKE_URL);
                httpsURLConnection = (HttpsURLConnection) url.openConnection();
                InputStream inputStream = new BufferedInputStream(httpsURLConnection.getInputStream());
                jsonObject = readStream(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (httpsURLConnection != null) {
                    httpsURLConnection.disconnect();
                }
            }
            return jsonObject;
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {

            List<Earthquake> earthquakes = new ArrayList<>();
            if (jsonObject != null) {
                try {
                    JSONArray features = jsonObject.getJSONArray("features");
                    for (int i = 0; i < features.length(); i++) {
                        JSONObject feature = features.getJSONObject(i);
                        JSONObject properties = feature.getJSONObject("properties");
                        String place = properties.getString("place");
                        Earthquake earthquake = new Earthquake(place);
                        earthquakes.add(earthquake);
                        Log.d(TAG, "onPostExecute: " + earthquake.getPlace());
                    }
                    dataRepository.setEarthquakes(earthquakes);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                view.hideProgress();
                view.onEarthquakeDataLoaded();
            }
        }

        public JSONObject readStream(InputStream in) {
            try {
                BufferedReader streamReader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
                StringBuilder sb = new StringBuilder();

                String inputStr;
                while ((inputStr = streamReader.readLine()) != null)
                    sb.append(inputStr);

                return new JSONObject(sb.toString());
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

    }


}
