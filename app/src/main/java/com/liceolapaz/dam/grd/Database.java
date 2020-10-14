package com.liceolapaz.dam.grd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {
    String query = "CREATE TABLE IF NOT EXISTS jugadores(" +
            "codigo INT(3) PRIMARY KEY AUTOINCREMENT, " +
            "nombre VARCHAR(45) NOT NULL, " +
            "precio INT(10) NOT NULL, " +
            "posicion CHAR(2) NOT NULL, " +
            "puntos INT(5) NOT NULL)";

    public Database(Context context, String dbName, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, dbName, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS jugadores");
        db.execSQL(query);
    }
}
