package com.hafiz.quranproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class SurahNamesActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private ArrayList<String> aList;
    private ArrayAdapter<String> arrayAdapter;
    private HashMap<String, Integer> mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surah_names);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(SurahNamesActivity.this));
        DatabaseHelper db = new DatabaseHelper(SurahNamesActivity.this);
        this.mp = db.specialFunc();

        for(String i:mp.keySet()){
            System.out.println(i);
        }

        aList = new ArrayList<>();
        aList = db.getAllSurahsNameU();
        recyclerViewAdapter = new RecyclerViewAdapter(SurahNamesActivity.this, aList);
        recyclerView.setAdapter(recyclerViewAdapter);
    }
}