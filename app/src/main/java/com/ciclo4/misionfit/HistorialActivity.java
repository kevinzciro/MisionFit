package com.ciclo4.misionfit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.ciclo4.misionfit.models.Medition;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HistorialActivity extends AppCompatActivity {

    ImageButton btnHomeH, btnPerfilH, btnHistorialH;
    Button btnMedicionM, btnHistorialM;
    Context contextHistorial;
    TextView txtImc, txtRecom;
    FirebaseAuth au;

    private final View.OnClickListener btnMedicionMListener = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            Intent intentMedicion = new Intent(contextHistorial, MedicionActivity.class);
            startActivity(intentMedicion);
            finish();
        }
    };

    private final View.OnClickListener btnHistorialMListener = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            Intent intentHistorial = new Intent(contextHistorial, HistorialActivity.class);
            startActivity(intentHistorial);
            finish();
        }
    };

    private final View.OnClickListener btnHomeHListener = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            Intent intentHome = new Intent(contextHistorial, HomeActivity.class);
            startActivity(intentHome);
            finish();
        }
    };

    private final View.OnClickListener btnPerfilHListener = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            Intent intentProfile = new Intent(contextHistorial, ProfileActivity.class);
            startActivity(intentProfile);
            finish();
        }
    };

    private final View.OnClickListener btnHistorialHListener = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            Intent intentHistory = new Intent(contextHistorial, HistorialActivity.class);
            startActivity(intentHistory);
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        contextHistorial = getApplicationContext();

        RecyclerView recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<Medition> medList = new ArrayList<Medition>();

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

        txtImc = findViewById(R.id.txtImc);
        txtRecom = findViewById(R.id.txtRecom);

        au= FirebaseAuth.getInstance();
        FirebaseUser user = au.getCurrentUser();

        db.collection("Medicion")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for(QueryDocumentSnapshot document : task.getResult()){
                                Log.d("Main",document.getId() + "=>" + document.getData());
                                Double medicion = document.getDouble("medicion");
                                String correo_usuario = document.getString("correo_usuario");
                                String fecha = document.getString("fecha_medicion");
                                String id = document.getId();
                                Medition med = new Medition(id, fecha, medicion, correo_usuario);
                                if (Objects.equals(med.getCorreo_usuario(), user.getEmail())){
                                    medList.add(med);
                                }
                            }
                            MyAdapter adapter = new MyAdapter(getApplicationContext(), medList);
                            recyclerView.setAdapter(adapter);
                            Double ultimaMed = medList.get(0).getMedicion();
                            txtImc.setText(ultimaMed.toString());
                            if (ultimaMed <= 18.5){
                                txtRecom.setText("Estas en un rango bajo");
                            }else if(ultimaMed > 18.5 && ultimaMed < 25.0){
                                txtRecom.setText("Estas en un rango normal");
                            }else if(ultimaMed >= 25.0 && ultimaMed < 30){
                                txtRecom.setText("Estas en un rango un poco alto");
                            }else if(ultimaMed > 30){
                                txtRecom.setText("Estas en un rango alto");
                            }
                            Log.d("Main", ultimaMed.toString());

                        }else{
                            Log.w("Main", "Error getting documents", task.getException());
                        }
                    }
                });

    }
}