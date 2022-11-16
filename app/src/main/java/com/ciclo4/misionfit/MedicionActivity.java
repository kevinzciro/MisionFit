package com.ciclo4.misionfit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.ciclo4.misionfit.config.MyAdapter;
import com.ciclo4.misionfit.models.Medition;

import java.util.ArrayList;
import java.util.List;

public class MedicionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicion);

    }

}