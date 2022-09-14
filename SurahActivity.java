package com.hafiz.quranproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class SurahActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surah);
        ListView myListView = findViewById(R.id.myListView);
        ArrayList<String> ayatList = new ArrayList<>();
        DatabaseHelper db = new DatabaseHelper(SurahActivity.this);
        Intent intent = getIntent();
        int suraId = intent.getIntExtra("SURA_ID", 0);
        ayatList = db.getSurahAyats(suraId);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(SurahActivity.this, R.layout.specialtextview, ayatList);
        myListView.setAdapter(arrayAdapter);

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(SurahActivity.this, AyatTranslationActivity.class);
            intent.putExtra("AYA_NO", position + 1);
            intent.putExtra("SURA_ID", suraId);
            startActivity(intent);

            }
        });

    }
}