package com.example.jingliu.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class ResultsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        RecyclerView recycleView = (RecyclerView) findViewById(R.id.list_view);
        recycleView.setLayoutManager(new LinearLayoutManager(this));
        SearchResultAdapter adapter = new SearchResultAdapter();
        recycleView.setAdapter(adapter);

        Flight flight = new Flight("JFK", "SFO");
        Flight[] data = new Flight[]{flight, flight, flight, flight};
        adapter.setData(data);
    }
}
