package com.ciclo4.misionfit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.ciclo4.misionfit.models.User;
import java.util.List;

public class MyAdapter1 extends RecyclerView.Adapter<MyViewHolder1> implements View.OnClickListener{
    Context context;
    List<User> items;
    View.OnClickListener listener;

    public MyAdapter1(Context context, List<User> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public MyViewHolder1 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_view1, parent,false);
        view.setOnClickListener((View.OnClickListener) this);
        return new MyViewHolder1(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder1 holder, int position) {
        holder.nombreView.setText(items.get(position).getNombre());
        holder.emailView.setText(items.get(position).getEmail());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if(listener!=null){
            listener.onClick(view);
        }
    }
}
