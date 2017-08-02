package com.example.jingliu.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.reactivestreams.Publisher;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

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

        Flowable.range(1, 4)
                .map((v) -> {
                    System.out.println("1");
                    System.out.println(Thread.currentThread());
                    return v + " Map";
                })
                .subscribeOn(Schedulers.computation())
                .flatMap(new Function<String, Publisher<?>>() {
                    @Override
                    public Publisher<?> apply(@NonNull String s) throws Exception {
                        return null;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((x) -> {
                    System.out.println("2");
                    System.out.println(Thread.currentThread());
                    System.out.println(x);
                });
    }
}
