package com.jorgereina.codingexercise.earthquakefragment;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jorgereina.codingexercise.R;
import com.jorgereina.codingexercise.model.Earthquake;

import java.util.List;

import static com.jorgereina.codingexercise.earthquakefragment.EarthquakeFragmentContract.*;

public class EarthquakeAdapter extends RecyclerView.Adapter<EarthquakeAdapter.EarthquakeViewHolder> {

    Presenter presenter;

    public EarthquakeAdapter(Presenter presenter) {
        this.presenter = presenter;

    }

    @NonNull
    @Override
    public EarthquakeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.earthquake_item, parent, false);
        return new EarthquakeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EarthquakeViewHolder holder, int position) {

        Log.d("lagartobind", "onBindViewHolder: " + position);

        Earthquake earthquake = presenter.getEarthquakeData(position);

        holder.place.setText(earthquake.getPlace());
    }

    @Override
    public int getItemCount() {
        return presenter.getEarthquakesCount();
    }

    public class EarthquakeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView place;

        public EarthquakeViewHolder(View itemView) {
            super(itemView);
            place = itemView.findViewById(R.id.place_tv);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int clickedPosition = getAdapterPosition();
            presenter.earthquakeSelected(clickedPosition);

        }
    }

}
