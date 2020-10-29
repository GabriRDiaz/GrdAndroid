package com.liceolapaz.dam.grd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static androidx.core.content.ContextCompat.startActivity;

public class MainActivity extends AppCompatActivity {
    private static int i=0;
    private static String queryId;
    public static String selectQuery;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void login(View view) {
        EditText txtUser = (EditText)findViewById(R.id.txtUser);
        EditText txtPass = (EditText)findViewById(R.id.txtPass);
        if(!txtUser.getText().toString().equals("admin") || !txtPass.getText().toString().equals("liceo")){
            Toast.makeText(this, getString(R.string.LoginFailed), Toast.LENGTH_SHORT).show();
            i++;
            if(i>=3){
                i=0;
                this.finish();
            }
        }else{
            Toast.makeText(this, "Login succesful", Toast.LENGTH_SHORT).show();
            Intent initDbInfo = new Intent(this, DbInfo.class);
            startActivity(initDbInfo);
        }
    }

    public static void setQueryId(String queryId) {
        MainActivity.queryId = queryId;
    }

    public static String getQueryId() {
        return queryId;
    }

    public static String getSelectQuery() {
        return selectQuery;
    }

    public static void setSelectQuery(String selectQuery) {
        MainActivity.selectQuery = selectQuery;
    }

}