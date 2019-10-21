package com.example.github_repo_lister.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_Name = "name";
    private static final String TABLE_NAME = "bookmark";
    private static final String DATABASE_NAME = "bookmarks_db";
    private static final int DATABASE_VERSION = 1;

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE if not exists " + TABLE_NAME + "("
                        + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + COLUMN_Name + " VARCHAR(100)"
                        + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertBookmark(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("name", name);
            Log.d("insert", name);
            db.insert(TABLE_NAME, null, contentValues);
            return true;
    }

    public Integer deleteBookmark(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "name = ? ", new String[]{(name)});
    }

    public boolean ifExists(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        String checkQuery = "SELECT " + COLUMN_Name + " FROM " + TABLE_NAME + " WHERE " + COLUMN_Name + " =?";
        Cursor cursor = db.rawQuery(checkQuery, new String[]{name});

        if(cursor.getCount() <= 0)
            return false;
        else
            return true;
    }
}