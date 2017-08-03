package com.example.jingliu.myapplication;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.AbstractList;
import java.util.List;

import static com.example.jingliu.myapplication.DetailActivity.BUNDLE_KEY_END;
import static com.example.jingliu.myapplication.DetailActivity.BUNDLE_KEY_START;

class SearchResultAdapter extends RecyclerView.Adapter<FlightViewHolder> {
    private Flight[] data = new Flight[]{};
    private List<Flight> flightList;

    @Override
    public FlightViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_layout, parent, false);
        return new FlightViewHolder(v);
    }

    @Override
    public void onBindViewHolder(FlightViewHolder holder, int position) {
        holder.flightStartText.setText(data[position].getStart());
        holder.flightEndText.setText(data[position].getEnd());
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public void setData(Flight[] data) {
        this.data = data;
    }

    public void addFlight(List<Flight> flightList) {
        this.data = flightList.toArray(data);
    }
}

class FlightViewHolder extends RecyclerView.ViewHolder {
    public TextView flightStartText;
    public TextView flightEndText;

    public FlightViewHolder(View itemView) {
        super(itemView);
        flightStartText = (TextView) itemView.findViewById(R.id.from);
        flightEndText = (TextView) itemView.findViewById(R.id.to);
        itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View itemView) {
                Intent intent = new Intent(itemView.getContext(), DetailActivity.class);
                intent.putExtra(BUNDLE_KEY_START, flightStartText.getText());
                intent.putExtra(BUNDLE_KEY_END, flightEndText.getText());
                itemView.getContext().startActivity(intent);
            }
        });
    }
}
