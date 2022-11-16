package com.ciclo4.misionfit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.ciclo4.misionfit.config.MyAdapter;
import com.ciclo4.misionfit.models.Medition;

import java.util.ArrayList;
import java.util.List;

public class HistorialActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial);


        List<Medition> data = new ArrayList<Medition>();

        data.add(new Medition("16/11/2022", 21.9));
        data.add(new Medition("16/10/2022", 22.5));
        data.add(new Medition("16/09/2022", 22.9));

        RecyclerView recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyAdapter(getApplicationContext(), data));
    }
}