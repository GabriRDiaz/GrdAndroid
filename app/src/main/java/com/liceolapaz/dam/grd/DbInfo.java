package com.liceolapaz.dam.grd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class DbInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db_info);
    }

    public void addPlayer(View view) {
        Intent initAddInfo = new Intent(this, addInfo.class);
        startActivity(initAddInfo);
    }
}