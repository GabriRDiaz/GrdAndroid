package com.liceolapaz.dam.grd;


import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

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
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        return new MyViewHolder(inflater.inflate(R.layout.recyclerview_rows, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int arrayPosition) {
        holder.id.setText(String.valueOf(id.get(arrayPosition)));
        holder.name.setText(String.valueOf(name.get(arrayPosition)));
        holder.price.setText(String.valueOf(price.get(arrayPosition)));
        holder.position.setText(String.valueOf(position.get(arrayPosition)));
        holder.points.setText(String.valueOf(points.get(arrayPosition)));
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
