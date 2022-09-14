package com.hafiz.quranproject;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;


public class DatabaseHelper extends SQLiteOpenHelper {

    String DB_PATH = null;
    private static String DB_NAME = "QuranDb.db";
    private SQLiteDatabase myDataBase;
    private final Context myContext;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, 10);
        this.myContext = context;
        this.DB_PATH = "/data/data/" + context.getPackageName() + "/" + "databases/";
        Log.e("Path 1", DB_PATH);
    }


    public void createDataBase() throws IOException {
        boolean dbExist = checkDataBase();
        if (dbExist) {
        } else {
            this.getReadableDatabase();
            try {
                copyDataBase();
            } catch (IOException e) {
                throw new Error("Error copying database");
            }
        }
    }

    private boolean checkDataBase() {
        SQLiteDatabase checkDB = null;
        try {
            String myPath = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        } catch (SQLiteException e) {
        }
        if (checkDB != null) {
            checkDB.close();
        }
        return checkDB != null ? true : false;
    }

    private void copyDataBase() throws IOException {
        InputStream myInput = myContext.getAssets().open(DB_NAME);
        String outFileName = DB_PATH + DB_NAME;
        OutputStream myOutput = new FileOutputStream(outFileName);
        byte[] buffer = new byte[10];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }
        myOutput.flush();
        myOutput.close();
        myInput.close();

    }

    public void openDataBase() throws SQLException {
        String myPath = DB_PATH + DB_NAME;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

    }

    @Override
    public synchronized void close() {
        if (myDataBase != null)
            myDataBase.close();
        super.close();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion)
            try {
                copyDataBase();
            } catch (IOException e) {
                e.printStackTrace();

            }
    }

    public Cursor query(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy) {
        return myDataBase.query(table, null, null, null, null, null, null);
    }

    public ArrayList<String> getAllSurahsNameU() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM  tsurah", null);
        ArrayList<String> surahsList = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                surahsList.add(cursor.getString(4));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return surahsList;
    }

    public ArrayList<String> getSurahAyats(int suraId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM  tayah WHERE SuraId =" + suraId, null);
        ArrayList<String> ayatList = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                ayatList.add(cursor.getString(3));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return ayatList;
    }

    public ArrayList<String> getAyat(int ayaNo, int suraId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM  tayah WHERE SuraId =" + suraId + " AND AyaNo = " + ayaNo, null);
        ArrayList<String> ayat = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                ayat.add(cursor.getString(3));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return ayat;
    }

    public ArrayList<String> getTranslation(int ayaNo, int suraId, String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + name + " FROM  tayah WHERE SuraId =" + suraId + " AND AyaNo = " + ayaNo, null);
        ArrayList<String> ayat = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                ayat.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return ayat;
    }

    public ArrayList<String> getSurahIntro(int suraId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT SurahIntro, SurahNameE, Nazool FROM tsurah WHERE SurahID = " + suraId, null);
        ArrayList<String> surahIntro = new ArrayList<>();
        if (cursor.moveToFirst()) {
            surahIntro.add(cursor.getString(1));
            surahIntro.add( "Nazool: " + cursor.getString(2));
            surahIntro.add(cursor.getString(0));
        }
        cursor.close();
        return surahIntro;
    }


    public HashMap<String, Integer> specialFunc(){
        HashMap<String, Integer> mp = new HashMap<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM  tsurah", null);
        if (cursor.moveToFirst()) {
            do {
                mp.put(cursor.getString(4), new Integer(cursor.getInt(0)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        Log.d("db.specialFunc", "db.specialFunc Called");
        return mp;
    }

}