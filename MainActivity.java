package com.hafiz.quranproject;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    Cursor c = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((Button) findViewById(R.id.button01)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper myDbHelper = new DatabaseHelper(MainActivity.this);
                try {
                    myDbHelper.createDataBase();
                } catch (IOException ioe) {
                    throw new Error("Unable to create database");
                }
                try {
                    myDbHelper.openDataBase();
                } catch (SQLException sqle) {
                    throw sqle;
                }
                Toast.makeText(MainActivity.this, "Successfully Imported", Toast.LENGTH_SHORT).show();
                c = myDbHelper.query("tayah", null, null, null, null, null, null);
                if (c.moveToFirst()) {
//                    do {
//                        Toast.makeText(MainActivity.this,
//                                "AyaID: " + c.getString(0) + "\n" +
//                                        "SuraID: " + c.getString(1) + "\n" +
//                                        "AyaNo: " + c.getString(2) + "\n" +
//                                        "ArabicText:  " + c.getString(3),
//                                Toast.LENGTH_LONG).show();
//                    } while (c.moveToNext());
                    Toast.makeText(MainActivity.this, "SUCCESS", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    public void buttonClick(View view){
        Intent i = new Intent(MainActivity.this, FrontAppPageActivity.class);
        startActivity(i);
    }
}