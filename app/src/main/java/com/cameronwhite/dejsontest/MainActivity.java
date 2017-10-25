package com.cameronwhite.dejsontest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    String url = "https://de-coding-test.s3.amazonaws.com/books.json";
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.mainRecyclerView);
        //performance increase on readonly data sets
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        LoadAdapterFromJSONAsyncTask loadAdapterFromJSONAsyncTask = new LoadAdapterFromJSONAsyncTask(recyclerView, url);
        loadAdapterFromJSONAsyncTask.execute();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        recyclerView = null;
    }
}




