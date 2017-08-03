package com.example.jingliu.myapplication;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.reactivestreams.Publisher;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class ResultsActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    private RecyclerView recycleView;
    private SearchResultAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        initView();

        recycleView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new SearchResultAdapter();
        recycleView.setAdapter(adapter);

        try {
            loadDataFromAssets();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        createAsync(adapter);
    }

    private void initView() {
        recycleView = (RecyclerView) findViewById(R.id.list_view);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
    }

    public void loadDataFromAssets() throws IOException {

        AssetManager assetManager = getAssets();

        String[] files = assetManager.list("");
        System.out.println(files[0]);

        InputStream input = assetManager.open("flight.json");
        InputStreamReader inputStreamReader = new InputStreamReader(input);

        Object flight = new Gson().fromJson(inputStreamReader, new TypeToken<List<Flight>>() {
        }.getType());
        adapter.addFlight((List<Flight>) flight);
    }

    public void createAsync() {
        recycleView.setVisibility(View.GONE);

        Flowable.just(R.raw.flight)
                .map(getResources()::openRawResource)
                .map(InputStreamReader::new)
                .map(new Function<InputStreamReader, Object>() {
                    @Override
                    public Object apply(@NonNull InputStreamReader v) throws Exception {
                        return new Gson().fromJson(v, new TypeToken<List<Flight>>() {
                        }.getType());
                    }
                })
                .subscribeOn(Schedulers.io())
                .delay(3, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(o -> {
                    adapter.addFlight((List<Flight>) o);
                    adapter.notifyDataSetChanged();

                    recycleView.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);
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
