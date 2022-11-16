package com.ciclo4.misionfit.config;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.ciclo4.misionfit.R;
import com.ciclo4.misionfit.models.User;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    Context context;
    List<User> items;

    public MyAdapter(Context context, List<User> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
//        holder.nameView.setText(items.get(position).getName());
//        holder.ownerView.setText(items.get(position).getOwner());
//        holder.imageView.setImageResource(items.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView nameView, ownerView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView2);
            nameView = itemView.findViewById(R.id.name);
            ownerView = itemView.findViewById(R.id.owner);
        }

    }
}
