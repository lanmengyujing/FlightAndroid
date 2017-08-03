package com.example.jingliu.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.reactivestreams.Publisher;

import java.io.InputStreamReader;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
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

        createAsync(adapter);
    }

    public void createAsync(SearchResultAdapter adapter) {
        Flowable.just(R.raw.flight)
                .map(getResources()::openRawResource)
                .map(InputStreamReader::new)
                .map(v -> new Gson().fromJson(v, new TypeToken<List<Flight>>() {}.getType()))
                .subscribeOn(Schedulers.io())
                .delay(3, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(o -> {
                    adapter.addFlight((List<Flight>) o);
                    adapter.notifyDataSetChanged();
                });
    }

    private void test() {
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


        Flight flight = new Flight("JFK", "SFO");
        Flight[] data = new Flight[]{flight, flight, flight, flight};
//        adapter.setData(data);
    }
}
