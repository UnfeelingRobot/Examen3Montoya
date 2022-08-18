package com.example.examen3montoya.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.examen3montoya.table.Tables;

public class Connection extends SQLiteOpenHelper {

    public Connection(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Tables.CREATE_TABLE_USER);
        db.execSQL(Tables.CREATE_TABLE_DATES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAntigua, int versionNueva) {
        db.execSQL("DROP TABLE IF EXISTS " + Tables.TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + Tables.TABLE_DATES);
        onCreate(db);
    }

}
