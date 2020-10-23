package com.liceolapaz.dam.grd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class Database extends SQLiteOpenHelper {
    public final String CREATE_QUERY = "CREATE TABLE IF NOT EXISTS players(" +
            "code INT(3) PRIMARY KEY AUTOINCREMENT, " +
            "name VARCHAR(45) NOT NULL, " +
            "price INT(10) NOT NULL, " +
            "position VARCHAR(15) NOT NULL, " +
            "points INT(5) NOT NULL)";

    private Context context;
    private static final String SELECT_QUERY = "SELECT * FROM players";
    private static final String DB_NAME = "players.db";
    public Database(Context context) {
        super(context, DB_NAME, null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS players");
        db.execSQL(CREATE_QUERY);
    }

    public void addPlayer(SQLiteDatabase db, String name, int price, String position, int points){
        db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("name",name);
        cv.put("price",price);
        cv.put("position",position);
        cv.put("points",points);

        long result = db.insert("players", null, cv);
        if(result!=-1){
            Toast.makeText(, .getString(R.string.infoAdded), Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(, .getString(R.string.infoNotAdded), Toast.LENGTH_SHORT).show();;
        }
    }

    public Cursor readData(){
       SQLiteDatabase db = this.getReadableDatabase();

       Cursor cursor = null;
       if(db!=null){
           cursor=db.rawQuery(SELECT_QUERY, null);
           return cursor
       }else{
           onUpgrade();
           return void
       }
    }
}
