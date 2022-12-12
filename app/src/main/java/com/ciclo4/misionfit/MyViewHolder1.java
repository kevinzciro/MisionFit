package com.ciclo4.misionfit;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class MyViewHolder1 extends RecyclerView.ViewHolder {
    TextView nombreView, emailView;

    public MyViewHolder1(@NonNull View itemView) {
        super(itemView);
        nombreView = (TextView) itemView.findViewById(R.id.name);
        emailView = (TextView) itemView.findViewById(R.id.email);
    }
}