package com.liceolapaz.dam.grd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class DbInfo extends AppCompatActivity {

    RecyclerView recyclerView;
    Database db;
    ArrayList<String> name, price, position, points;
    CustomAdapter playerInfoAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db_info);

        recyclerView = findViewById(R.id.playerList);

       retrieveData();
       playerInfoAdapter = new CustomAdapter(this, name, price,position,points);
       recyclerView.setAdapter(playerInfoAdapter);
       recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void retrieveData(){
        db = new Database(DbInfo.this);
        name = new ArrayList<>();
        price = new ArrayList<>();
        position = new ArrayList<>();
        points = new ArrayList<>();

        Cursor cursor = db.readData();
        if(cursor.getCount() != 0){
            while(cursor.moveToNext()){
                name.add(cursor.getString(0));
                price.add(cursor.getString(1));
                position.add(cursor.getString(2));
                points.add(cursor.getString(3));
        }
        }else{
            Toast.makeText(this, R.string.noData, Toast.LENGTH_SHORT).show();
    }}

    public void addPlayerBtn(View view) {
        Intent initAddInfo = new Intent(this, addInfo.class);
        startActivity(initAddInfo);
    }
}