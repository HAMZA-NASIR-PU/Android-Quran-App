package com.hafiz.quranproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class AyatTranslationActivity extends AppCompatActivity {
    private int suraId;
    private int ayaNo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayat_translation);
        Intent intent = getIntent();
        this.suraId = intent.getIntExtra("SURA_ID", 0);
        this.ayaNo = intent.getIntExtra("AYA_NO", 0);
        DatabaseHelper db = new DatabaseHelper(AyatTranslationActivity.this);


        ListView myListView3 = findViewById(R.id.myListView3);
        ArrayList<String> ayat = new ArrayList<>();
        ayat = db.getAyat(ayaNo, suraId);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(AyatTranslationActivity.this, R.layout.specialtextview, ayat);
        myListView3.setAdapter(arrayAdapter);
    }

    public void fatehMClickBtn(View view) {
        DatabaseHelper db = new DatabaseHelper(AyatTranslationActivity.this);
        ArrayList<String> ayatTrans = new ArrayList<>();
        ayatTrans = db.getTranslation(this.ayaNo, this.suraId, "FatehMuhammadJalandhrield");

        ListView myListView2 = findViewById(R.id.myListView2);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(AyatTranslationActivity.this, R.layout.specialtextview, ayatTrans);
        myListView2.setAdapter(arrayAdapter);

    }

    public void mehmoodClickBtn(View view){
        DatabaseHelper db = new DatabaseHelper(AyatTranslationActivity.this);
        ArrayList<String> ayatTrans = new ArrayList<>();
        ayatTrans = db.getTranslation(this.ayaNo, this.suraId, "MehmoodulHassan");

        ListView myListView2 = findViewById(R.id.myListView2);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(AyatTranslationActivity.this, R.layout.specialtextview, ayatTrans);
        myListView2.setAdapter(arrayAdapter);
    }

    public void drMohsinClickBtn(View view){
        DatabaseHelper db = new DatabaseHelper(AyatTranslationActivity.this);
        ArrayList<String> ayatTrans = new ArrayList<>();
        ayatTrans = db.getTranslation(this.ayaNo, this.suraId, "DrMohsinKhan");

        ListView myListView2 = findViewById(R.id.myListView2);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(AyatTranslationActivity.this, R.layout.specialtextview, ayatTrans);
        myListView2.setAdapter(arrayAdapter);
    }

    public void muftiTaqiClickBtn(View view){
        DatabaseHelper db = new DatabaseHelper(AyatTranslationActivity.this);
        ArrayList<String> ayatTrans = new ArrayList<>();
        ayatTrans = db.getTranslation(this.ayaNo, this.suraId, "MuftiTaqiUsmani");

        ListView myListView2 = findViewById(R.id.myListView2);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(AyatTranslationActivity.this, R.layout.specialtextview, ayatTrans);
        myListView2.setAdapter(arrayAdapter);
    }
}