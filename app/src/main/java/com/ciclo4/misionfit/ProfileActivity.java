package com.ciclo4.misionfit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends AppCompatActivity {

    ImageButton btnHomeH, btnPerfilH, btnHistorialH;
    ConstraintLayout historialLay, salirLay;
    Context contextPerfil;
    FirebaseAuth au;

    private final View.OnClickListener historialLayListener = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            Intent intentHistory = new Intent(contextPerfil, HistorialActivity.class);
            startActivity(intentHistory);
            finish();
        }
    };

    private final View.OnClickListener btnHomeHListener = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            Intent intentHome = new Intent(contextPerfil, HomeActivity.class);
            startActivity(intentHome);
            finish();
        }
    };

    private final View.OnClickListener btnPerfilHListener = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            Intent intentProfile = new Intent(contextPerfil, ProfileActivity.class);
            startActivity(intentProfile);
            finish();
        }
    };

    private final View.OnClickListener btnHistorialHListener = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            Intent intentHistory = new Intent(contextPerfil, HistorialActivity.class);
            startActivity(intentHistory);
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        contextPerfil = getApplicationContext();

        btnHomeH = findViewById(R.id.btnHomeH);
        btnHomeH.setOnClickListener(btnHomeHListener);

        btnPerfilH = findViewById(R.id.btnPerfilH);
        btnPerfilH.setOnClickListener(btnPerfilHListener);

        btnHistorialH = findViewById(R.id.btnHistorialH);
        btnHistorialH.setOnClickListener(btnHistorialHListener);

        historialLay = findViewById(R.id.historialLay);
        historialLay.setOnClickListener(historialLayListener);

        salirLay = findViewById(R.id.salirLay);
        salirLay.setOnClickListener(ev);

        au= FirebaseAuth.getInstance();
        FirebaseUser user = au.getCurrentUser();
        if(user==null){
            Toast.makeText(ProfileActivity.this,"Usuario sin Sesion",
                    Toast.LENGTH_SHORT).show();
            Intent it = new Intent(ProfileActivity.this,MainActivity.class);
            startActivity(it);
        }
    }

    View.OnClickListener ev = new View.OnClickListener()
    {
        @Override
        public void onClick(View v) {
            FirebaseAuth.getInstance().signOut();
            Intent it = new Intent(ProfileActivity.this, LoginActivity.class);
            startActivity(it);
            Toast.makeText(ProfileActivity.this,"Has salido", Toast.LENGTH_SHORT).show();
        }
    };
}