package com.example.a0stjal24.sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;

/**
 * Created by 0stjal24 on 21/03/2018.
 */

public class MyHelper extends SQLiteOpenHelper {

    static final int VERSION = 1;
    static final String DATABASE_NAME = "Myel";

    public MyHelper (Context ctx) {
        super(ctx, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL ("CREATE TABLE IF NOT EXISTS Songs (Id INTEGER PRIMARY KEY, Title VARCHAR(255), Artist VARCHAR(255), Year INTEGER)");
    }

    public ArrayList<Song> findSong()
    {
        String title = "";
        ArrayList<Song> songs = new ArrayList<Song>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery ("SELECT * FROM Songs WHERE Title=?",
                new String[] { Title } );
        if (cursor.moveToFirst())
        {
            while(!cursor.isAfterLast())
            {
                Song s = new Song
                        (cursor.getString(cursor.getColumnIndex("Title")),
                                cursor.getString(cursor.getColumnIndex("Artist")),
                                cursor.getLong(cursor.getColumnIndex("Year")));
                Songs.add(s);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return songs;
    }

    public long insertRecord()
    {
        SQLiteDatabase db = getWritableDatabase();
        SQLiteStatement stmt = db.compileStatement
                ("INSERT INTO people(Title,Artist,Year) VALUES (?, ?, ?)");
        stmt.bindString (1, "Not Afraid");
        stmt.bindString (2, "Eminem");
        stmt.bindLong (3, 2010);
        long id = stmt.executeInsert();
        return id;
    }

    public int insertRecord()
    {
        SQLiteDatabase db = getWritableDatabase();
        SQLiteStatement stmt = db.compileStatement
                ("UPDATE Songs SET Artist=? WHERE Artist=?");
        stmt.bindString (1, "EMINEM");
        stmt.bindString (2, "Kanye West");
        int nAffectedRows = stmt.executeUpdateDelete();
        return nAffectedRows;
    }

    public int insertRecord()
    {
        SQLiteDatabase db = getWritableDatabase();
        SQLiteStatement stmt = db.compileStatement
                ("DELETE FROM People WHERE Lastname=?");
        stmt.bindString (1, "Smith");
        int nAffectedRows = stmt.executeUpdateDelete();
        return nAffectedRows;
    }





    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL ("DROP TABLE IF EXISTS Songs");
        onCreate(db);

    }
}
