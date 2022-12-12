package com.ciclo4.misionfit;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



public class MyViewHolder extends RecyclerView.ViewHolder {
    TextView medicionView, fecha_medicionView;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        medicionView = (TextView) itemView.findViewById(R.id.medition);
        fecha_medicionView = (TextView) itemView.findViewById(R.id.date);
    }
}
