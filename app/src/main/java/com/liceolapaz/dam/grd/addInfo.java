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
        }else{
            clearValues();
            MainActivity.add = false;
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
    public void cancelAddInfo(View view) {
        txtDialog="Data will be discarted";
        titleDialog="Cancel";
        createDialog(txtDialog, titleDialog);
    }

    public void deleteInfo(View view) {
        txtDialog="Data will be removed from database";
        titleDialog="Delete";
        createDialog(txtDialog, titleDialog);
    }

    public void acceptAddInfo(View view) {
        txtDialog="Data will be saved in database";
        titleDialog="Accept";
        createDialog(txtDialog, titleDialog);
    }
    private void createDialog(String txtDialog, String titleDialog){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(titleDialog);
        builder.setMessage(txtDialog);
        builder.setPositiveButton("Accept", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(addInfo.this, "Accept", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(addInfo.this, "Neutral", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(addInfo.this, "Deny", Toast.LENGTH_SHORT).show();
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
//            Toast.makeText(this, Arrays.asList(DATA).indexOf("MF"), Toast.LENGTH_SHORT).show();
//            position.setSelection(DATA.indexOf(idA.get(3)));
//            position.setSelection(DATA.indexOf(getSpinnerInfo();));
//            position.setSelection(DATA.indexOf(idA.get(3)));
//            position.setSelection(idA.get(3));
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
