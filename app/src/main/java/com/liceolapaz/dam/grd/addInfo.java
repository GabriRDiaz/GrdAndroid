package com.liceolapaz.dam.grd;

import androidx.appcompat.app.AppCompatActivity;

import android.app.FragmentManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


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
        txtDialog="Changes will be discarted";
        titleDialog="Cancel";

    }

    public void deleteInfo(View view) {
        txtDialog="Data will be removed from database";
        titleDialog="Delete";

    }

    public void acceptAddInfo(View view) {
        txtDialog="Data will be saved in database";
        titleDialog="Accept";
    }

//    private void createDialog(String txtDialog, String titleDialog, Bundle savedInstanceState){
//        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//
//        builder.setMessage(txtDialog)
//                .setTitle(titleDialog)
//                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        Toast.makeText(addInfo.this, "Yes", Toast.LENGTH_SHORT).show();
//                        dialog.cancel();
//                    }
//                })
//                .setNegativeButton("No", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        Toast.makeText(addInfo.this, "No", Toast.LENGTH_SHORT).show();
//                        dialog.cancel();
//                    }
//                })
//                .setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        Toast.makeText(addInfo.this, "Cancel", Toast.LENGTH_SHORT).show();
//                        dialog.cancel();
//                    }
//                });
//    }

}
