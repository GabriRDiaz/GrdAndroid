package com.liceolapaz.dam.grd;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import static android.app.PendingIntent.getActivity;

public class addInfo extends AppCompatActivity {
    private Spinner addPos;
    private String txtDialog;
    private String titleDialog;
    private ArrayList<String> idA = new ArrayList<>();
    Button but;
    TextView id;
    TextView name;
    TextView price;
    TextView points;
    byte add;
    final String[] DATA =
            {"FW","MF", "DF","GK"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_info);
        Intent in = getIntent();

        Bundle b = in.getExtras();
        changeMenuView(b);
    }

    public void clearValues() {
        fview();
        id.setText("");
        name.setText("");
        price.setText("");
        addPos.setSelection(0);
        points.setText("");
    }

    public void cancelAddInfo(View view) {
        txtDialog="Data will be discarted";
        titleDialog="Cancel";
        if(add==1){
            Toast.makeText(this, "CancelAdd", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "CancelUpd", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteInfo(View view) {
        txtDialog="Data will be removed from database";
        titleDialog="Delete";
        if(add==1){
            Toast.makeText(this, "DeleteAdd", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "DeleteUpd", Toast.LENGTH_SHORT).show();
        }
    }

    public void acceptAddInfo(View view) {
        txtDialog="Data will be saved in database";
        titleDialog="Accept";
        if(add==1){
            Toast.makeText(this, "AcceptAdd", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "AcceptUpd", Toast.LENGTH_SHORT).show();
        }

    }
    private void createDialogUpd(String txtDialog, String titleDialog){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(titleDialog);
        builder.setMessage(txtDialog);
        builder.setPositiveButton("Accept", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(addInfo.this, "AcceptUpd", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(addInfo.this, "NeutralUpd", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(addInfo.this, "DenyUpd", Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    private void createDialogAdd(String txtDialog, String titleDialog){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(titleDialog);
        builder.setMessage(txtDialog);
        builder.setPositiveButton("Accept", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void changeMenuView(Bundle b){
        but = findViewById(R.id.delete);
        add = b.getByte("isFromAddButton");
        if(add==0){
            setValues();
            but.setVisibility(View.GONE);
        }else{
            clearValues();
            but.setVisibility(View.VISIBLE);
        }
    }
    public void setValues() {
        fview();
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_item, DATA);
        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);

        addPos.setAdapter(adapter);
        MainActivity.setSelectQuery("SELECT * FROM players WHERE code=" + MainActivity.getQueryId());
        Database db = new Database(addInfo.this);
        Cursor cursor = db.readData();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, R.string.noData, Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                idA.add(cursor.getString(0));
                idA.add(cursor.getString(1));
                idA.add(cursor.getString(2));
                idA.add(cursor.getString(3));
                idA.add(cursor.getString(4));

            }
            id.setText(idA.get(0));
            name.setText(idA.get(1));
            price.setText(idA.get(2));
            addPos.setSelection(getSpinnerInfo());
            points.setText(idA.get(4));

        }
    }
    private void fview(){
        id = findViewById(R.id.addId);
        name = findViewById(R.id.addName);
        price = findViewById(R.id.addPrice);
        addPos = (Spinner)findViewById(R.id.addPosition);
        points = findViewById(R.id.addPoints);
    }
    private int getSpinnerInfo(){
        for(int i=0; i<4; i++){
           if(DATA[i].equals(idA.get(3))){
//                Toast.makeText(this, ""+idA.get(i), Toast.LENGTH_SHORT).show();
                return i;
            }
        }
        return 1;
    }
    private void addInfoDb(){
        Database dbase = new Database(this);
        SQLiteDatabase dbaseSQL = dbase.getWritableDatabase();
        dbase.addPlayer(dbaseSQL, name.getText().toString(), price.getText().toString(), (String)addPos.getSelectedItem(), points.getText().toString());
    }
}
