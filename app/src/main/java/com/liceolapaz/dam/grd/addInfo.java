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

import static android.app.PendingIntent.getActivity;

public class addInfo extends AppCompatActivity {
    private Spinner addPos;
    private String txtDialog;
    private String titleDialog;
    private ArrayList<String> idA = new ArrayList<>();
    private ArrayList<String> nameA = new ArrayList<>();
    private ArrayList<String> priceA = new ArrayList<>();
    //private ArrayList<String> positionA = new ArrayList<>();
    private ArrayList<String> pointsA = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_info);

        addPos = (Spinner)findViewById(R.id.addPosition);

        final String[] data =
                new String[]{"Forward (FW)","Midfielder (MF)","Defender (DF)","Goalkeeper (GK)"};

        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_item, data);
        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);

        addPos.setAdapter(adapter);
        setValues();
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
        TextView id = findViewById(R.id.addId);
        TextView name = findViewById(R.id.addName);
        TextView price = findViewById(R.id.addPrice);
//      Spinner position = findViewById(R.id.addPosition);
        TextView points = findViewById(R.id.addPoints);

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
//                nameA.add(cursor.getString(1));
//                priceA.add(cursor.getString(2));
//              positionA.add(cursor.getString(3));
//                pointsA.add(cursor.getString(4));
            }
            id.setText(idA.get(0));
            name.setText(idA.get(1));
            price.setText(idA.get(2));
            points.setText(idA.get(4));
//        name.setText(nameA.add(cursor.getString(1)));
//        price.setText(priceA.add(cursor.getString(2)));
//        position.setSelection(options.indexOf(value));
//        points.setText(pointsA.add(cursor.getString(4)));
        }
    }
}
