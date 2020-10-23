package com.liceolapaz.dam.grd;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;


import static android.app.PendingIntent.getActivity;

public class addInfo extends AppCompatActivity {
    private Spinner addPos;
    private String txtDialog;
    private String titleDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_info);

        addPos = (Spinner)findViewById(R.id.addposition);

        final String[] data =
                new String[]{"Forward (FW)","Midfielder (MF)","Defender (DF)","Goalkeeper (GK)"};

        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_item, data);
//
        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);

        addPos.setAdapter(adapter);
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



}
