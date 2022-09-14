package com.hafiz.quranproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class SurahsIntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surahs_intro);
        DatabaseHelper db = new DatabaseHelper(SurahsIntroActivity.this);
        Intent intent = getIntent();
        int suraId = intent.getIntExtra("SURA_ID", 0);
        ArrayList<String> intro = db.getSurahIntro(suraId);
        ListView myListView5 = findViewById(R.id.myListView5);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(SurahsIntroActivity.this, R.layout.specialtextview, intro);
        myListView5.setAdapter(arrayAdapter);

    }
}