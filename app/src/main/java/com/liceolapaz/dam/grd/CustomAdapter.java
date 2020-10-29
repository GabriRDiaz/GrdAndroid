package com.liceolapaz.dam.grd;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import static androidx.core.content.ContextCompat.startActivity;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private ArrayList id, name, price, position, points;

    CustomAdapter(Context context,ArrayList id, ArrayList name, ArrayList price, ArrayList position, ArrayList points){
        this.context = context;
        this.id = id;
        this.name = name;
        this.price = price;
        this.position = position;
        this.points = points;
        //Swap
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        return new MyViewHolder(inflater.inflate(R.layout.recyclerview_rows, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int arrayPosition) {
        holder.id.setText(String.valueOf(id.get(arrayPosition)));
        holder.price.setText(String.valueOf(price.get(arrayPosition)));
        holder.position.setText(String.valueOf(position.get(arrayPosition)));
        holder.points.setText(String.valueOf(points.get(arrayPosition)));

        String nameStr = String.valueOf(name.get(arrayPosition));
//      splitName();
        holder.name.setText(String.valueOf(name.get(arrayPosition)));
        holder.position.setText(String.valueOf(position.get(arrayPosition)));
        holder.points.setText(String.valueOf(points.get(arrayPosition)));
        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override public void onClick(View v) {
                String t = String.valueOf(id.get(arrayPosition));
                MainActivity.setQueryId(t);
                Bundle bundle = new Bundle();
                bundle.putByte("isFromAddButton", (byte)0);
                Intent initAddInfo= new Intent(context, addInfo.class);
                initAddInfo.putExtras(bundle);
                initAddInfo.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(initAddInfo);
            }
        });
    }

    @Override
    public int getItemCount() {
        return name.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView id, name, price, position, points;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.playerId);
            name = itemView.findViewById(R.id.playerName);
            price = itemView.findViewById(R.id.playerPrice);
            position = itemView.findViewById(R.id.playerPos);
            points = itemView.findViewById(R.id.playerPoints);
        }
    }
}
//        METHOD FOR SPLIT NAME AND SURNAME
//    private void splitName(){
//        if(nameStr.length()>6 && nameStr.contains(" ")){
//            String surName=nameStr.split(" ")[nameStr.split(" ").length-1];
//            String firstName = nameStr.substring(0, nameStr.length() - surName.length());
//            nameStr= firstName+ "\n" +surName;
//            holder.name.setSingleLine(false);
//            holder.name.setText(nameStr);
//        }else{
//            holder.name.setText(String.valueOf(name.get(arrayPosition)));
//        }
//    }