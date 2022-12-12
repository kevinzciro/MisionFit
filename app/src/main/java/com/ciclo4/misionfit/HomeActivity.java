package com.ciclo4.misionfit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class HomeActivity extends AppCompatActivity {

    Context contextHome;
    Button btnHistorial, btnMedicion;
    ImageButton btnHomeH, btnPerfilH, btnHistorialH;

    private final View.OnClickListener btnHomeHListener = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            Intent intentHome = new Intent(contextHome, HomeActivity.class);
            startActivity(intentHome);
            finish();
        }
    };

    private final View.OnClickListener btnPerfilHListener = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            Intent intentProfile = new Intent(contextHome, ProfileActivity.class);
            startActivity(intentProfile);
            finish();
        }
    };

    private final View.OnClickListener btnHistorialHListener = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            Intent intentHistory = new Intent(contextHome, HistorialActivity.class);
            startActivity(intentHistory);
            finish();
        }
    };

    private final View.OnClickListener btnHistorialListener = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            Intent intentHistory = new Intent(contextHome, HistorialActivity.class);
            startActivity(intentHistory);
            finish();
        }
    };

    private final View.OnClickListener btnMedicionListener = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            Intent intentMedicion = new Intent(contextHome, MedicionActivity.class);
            startActivity(intentMedicion);
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        contextHome = getApplicationContext();

        btnHomeH = findViewById(R.id.btnHomeH);
        btnHomeH.setOnClickListener(btnHomeHListener);

        btnPerfilH = findViewById(R.id.btnPerfilH);
        btnPerfilH.setOnClickListener(btnPerfilHListener);

        btnHistorialH = findViewById(R.id.btnHistorialH);
        btnHistorialH.setOnClickListener(btnHistorialHListener);

        btnHistorial = findViewById(R.id.btnHistorial);
        btnHistorial.setOnClickListener(btnHistorialListener);

        btnMedicion = findViewById(R.id.btnMedir);
        btnMedicion.setOnClickListener(btnMedicionListener);
    }


}