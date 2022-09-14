package com.hafiz.quranproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class FrontAppPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front_app_page);
    }
    public void surahsListBtnClick(View view){
        Intent intent = new Intent(FrontAppPageActivity.this, SurahNamesActivity.class);
        startActivity(intent);
    }

    public void surahsIntroBtnClick(View view){
        Intent intent = new Intent(FrontAppPageActivity.this, SurahNamesActivity2.class);
        startActivity(intent);
    }
}