package com.liceolapaz.dam.grd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class DbInfo extends AppCompatActivity {

    RecyclerView recyclerView;
    Database db;
    ArrayList<String>id, name, price, position, points;
    CustomAdapter playerInfoAdapter;
    Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db_info);

        recyclerView = findViewById(R.id.playerList);

        retrieveData();
//       Toast.makeText(this, , Toast.LENGTH_SHORT).show();
       playerInfoAdapter = new CustomAdapter(this, id, name, price,position,points);
       recyclerView.setAdapter(playerInfoAdapter);
       recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void retrieveData(){
        MainActivity.setSelectQuery("SELECT * FROM players");
        db = new Database(DbInfo.this);
        id = new ArrayList<>();
        name = new ArrayList<>();
        price = new ArrayList<>();
        position = new ArrayList<>();
        points = new ArrayList<>();

        cursor = db.readData();

        if(cursor.getCount() == 0){
                Toast.makeText(this, R.string.noData, Toast.LENGTH_SHORT).show();
        }else{
            while(cursor.moveToNext()){
            id.add(cursor.getString(0));
            name.add(cursor.getString(1));
            price.add(cursor.getString(2));
            position.add(cursor.getString(3));
            points.add(cursor.getString(4));
            }

        }

    }
    public void addPlayerBtn(View view) {
        Intent initAddInfo = new Intent(this, addInfo.class);
        startActivity(initAddInfo);
    }
    
//    public void viewPlayer(View view){
////        Toast.makeText(this, "1", Toast.LENGTH_SHORT).show();
//        MainActivity.setQueryId("1");
//        Intent initAddInfo= new Intent(this, addInfo.class);
//        startActivity(initAddInfo);
//    }
}