package com.example.jingliu.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class ResultsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        RecyclerView recycleView = (RecyclerView) findViewById(R.id.list_view);
        recycleView.setLayoutManager(new LinearLayoutManager(this));
        recycleView.setAdapter(new SearchResultAdapter());
    }
}
