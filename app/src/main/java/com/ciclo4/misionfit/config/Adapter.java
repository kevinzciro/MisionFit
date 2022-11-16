package com.ciclo4.misionfit.config;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ciclo4.misionfit.R;

import java.util.ArrayList;

public class Adapter extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<String> data;
    private ArrayList<Integer> images;

    public Adapter(Context context, int layout, ArrayList<String> data, ArrayList<Integer> images) {
        this.context = context;
        this.layout = layout;
        this.data = data;
        this.images = images;
    }

    @Override
    public int getCount() {
        return this.data.size();
    }

    @Override
    public Object getItem(int position) {
        return this.data.get(position);
    }

    @Override
    public long getItemId(int id) {
        return id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {

        View v = convertView;

        LayoutInflater layoutInflater = LayoutInflater.from(this.context);
        v = layoutInflater.inflate(R.layout.item, null);

        String info = data.get(position);
        TextView textView = (TextView) v.findViewById(R.id.textList);
        textView.setText(info);
        ImageView imageView = (ImageView) v.findViewById(R.id.imageList);
        imageView.setImageResource(images.get(position));

        return v;
    }
}
