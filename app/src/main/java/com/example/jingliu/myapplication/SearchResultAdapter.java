package com.example.jingliu.myapplication;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

class SearchResultAdapter extends RecyclerView.Adapter<FlightViewHolder> {
    private Flight[] data = new Flight[]{};

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
}

class FlightViewHolder extends RecyclerView.ViewHolder {
    public TextView flightStartText;
    public TextView flightEndText;

    public FlightViewHolder(View itemView) {
        super(itemView);
        flightStartText = (TextView) itemView.findViewById(R.id.from);
        flightEndText = (TextView) itemView.findViewById(R.id.to);
    }
}
