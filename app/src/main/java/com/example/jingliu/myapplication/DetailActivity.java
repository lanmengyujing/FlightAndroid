package com.example.jingliu.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
    public static final String BUNDLE_KEY_END = "BUNDLE_KEY_END";
    public static final String BUNDLE_KEY_START = "BUNDLE_KEY_START";
    private TextView flightStartText;
    private TextView flightEndText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_IMMERSIVE);

        setupToolbar();
        initViews();
        initData();
    }

    private void initViews() {
        flightStartText = (TextView) findViewById(R.id.from);
        flightEndText = (TextView) findViewById(R.id.to);
    }

    private void initData() {
        final String start = getIntent().getStringExtra(BUNDLE_KEY_END);
        final String end = getIntent().getStringExtra(BUNDLE_KEY_START);
        flightStartText.setText(start);
        flightEndText.setText(end);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
//        getSupportActionBar().setTitle("");
    }
}
