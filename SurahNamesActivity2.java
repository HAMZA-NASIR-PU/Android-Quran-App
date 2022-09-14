package com.hafiz.quranproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class SurahNamesActivity2 extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerViewAdapter2 recyclerViewAdapter;
    private ArrayList<String> aList;
    private ArrayAdapter<String> arrayAdapter;
    private HashMap<String, Integer> mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surah_names2);

        recyclerView = findViewById(R.id.recyclerView2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(SurahNamesActivity2.this));
        DatabaseHelper db = new DatabaseHelper(SurahNamesActivity2.this);
        this.mp = db.specialFunc();

        for(String i:mp.keySet()){
            System.out.println(i);
        }

        aList = new ArrayList<>();
        aList = db.getAllSurahsNameU();
        recyclerViewAdapter = new RecyclerViewAdapter2(SurahNamesActivity2.this, aList);
        recyclerView.setAdapter(recyclerViewAdapter);
    }
}