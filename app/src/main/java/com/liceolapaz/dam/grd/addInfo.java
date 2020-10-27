package com.liceolapaz.dam.grd;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
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
    private boolean upd; //True->Upd | False->Add
    TextView id;
    TextView name;
    TextView price;
    TextView points;
    final String[] DATA =
            {"FW","MF", "DF","GK"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_info);

        addPos = (Spinner)findViewById(R.id.addPosition);

        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_item, DATA);
        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);

        addPos.setAdapter(adapter);

        if(!MainActivity.add) {
            setValues();
            upd = true;
        }else{
            clearValues();
            upd = false;
        }
    }
    public void clearValues() {
        fview();
        id.setText("");
        name.setText("");
        price.setText("");
        addPos.setSelection(0);
        points.setText("");
    }
    private void checkBuilder(){
        if(!upd){
            createDialogAdd(txtDialog, titleDialog);
        }else{
            createDialogUpd(txtDialog, titleDialog);
        }
    }
    public void cancelAddInfo(View view) {
        txtDialog="Data will be discarted";
        titleDialog="Cancel";
        checkBuilder();
    }

    public void deleteInfo(View view) {
        txtDialog="Data will be removed from database";
        titleDialog="Delete";
        checkBuilder();
    }

    public void acceptAddInfo(View view) {
        txtDialog="Data will be saved in database";
        titleDialog="Accept";
        checkBuilder();
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
                Toast.makeText(addInfo.this, "AcceptAdd", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(addInfo.this, "NeutralAdd", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(addInfo.this, "DenyAdd", Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }


    public void setValues() {
        fview();

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
        //Spinner position = findViewById(R.id.addPosition);
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
}
