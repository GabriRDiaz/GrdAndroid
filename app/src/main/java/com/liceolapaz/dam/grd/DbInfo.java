package com.liceolapaz.dam.grd;

import androidx.appcompat.app.AppCompatActivity;

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
        Toast.makeText(this, "Button Working!", Toast.LENGTH_SHORT).show();
    }
}