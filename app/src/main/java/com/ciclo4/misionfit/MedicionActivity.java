package com.ciclo4.misionfit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.ciclo4.misionfit.config.MyAdapter;
import com.ciclo4.misionfit.models.Medition;

import java.util.ArrayList;
import java.util.List;

public class MedicionActivity extends AppCompatActivity {

    ImageButton btnHomeH, btnPerfilH, btnHistorialH;
    Button btnMedicionM, btnHistorialM;
    Context contextMedir;

    private final View.OnClickListener btnMedicionMListener = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            Intent intentMedicion = new Intent(contextMedir, MedicionActivity.class);
            startActivity(intentMedicion);
            finish();
        }
    };

    private final View.OnClickListener btnHistorialMListener = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            Intent intentHistorial = new Intent(contextMedir, HistorialActivity.class);
            startActivity(intentHistorial);
            finish();
        }
    };

    private final View.OnClickListener btnHomeHListener = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            Intent intentHome = new Intent(contextMedir, HomeActivity.class);
            startActivity(intentHome);
            finish();
        }
    };

    private final View.OnClickListener btnPerfilHListener = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            Intent intentProfile = new Intent(contextMedir, ProfileActivity.class);
            startActivity(intentProfile);
            finish();
        }
    };

    private final View.OnClickListener btnHistorialHListener = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            Intent intentHistory = new Intent(contextMedir, HistorialActivity.class);
            startActivity(intentHistory);
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicion);

        contextMedir = getApplicationContext();

        btnHomeH = findViewById(R.id.btnHomeH);
        btnHomeH.setOnClickListener(btnHomeHListener);

        btnPerfilH = findViewById(R.id.btnPerfilH);
        btnPerfilH.setOnClickListener(btnPerfilHListener);

        btnHistorialH = findViewById(R.id.btnHistorialH);
        btnHistorialH.setOnClickListener(btnHistorialHListener);

        btnHistorialM = findViewById(R.id.btnHistorialM);
        btnHistorialM.setOnClickListener(btnHistorialMListener);

        btnMedicionM = findViewById(R.id.btnMedirM);
        btnMedicionM.setOnClickListener(btnMedicionMListener);

    }

}