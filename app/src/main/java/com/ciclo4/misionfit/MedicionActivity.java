package com.ciclo4.misionfit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.ciclo4.misionfit.models.Medition;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.time.LocalDate;

public class MedicionActivity extends AppCompatActivity {

    ImageButton btnHomeH, btnPerfilH, btnHistorialH;
    Button btnMedicionM, btnHistorialM, btnMedirImc;
    Context contextMedir;
    EditText txtAltura, txtPeso;
    FirebaseFirestore db;

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

    private final View.OnClickListener btnMedirImcListener = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            if ( txtAltura.getText().toString() != null && txtPeso.getText().toString() != null){
                Double altura = Double.valueOf(txtAltura.getText().toString());
                Double peso = Double.valueOf(txtPeso.getText().toString());

                altura = altura/100.0;
                Double medicion = Double.valueOf(Math.round(peso/(altura * altura)));
                String fecha_medicion = LocalDate.now().toString();
                FirebaseAuth au= FirebaseAuth.getInstance();
                FirebaseUser user = au.getCurrentUser();
                Medition med = new Medition(fecha_medicion, medicion, user.getEmail().toString());
                Log.d("Main", med.getFecha_medicion());
                db.collection("Medicion")
                        .add(med)
                        .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentReference> task) {
                                Toast.makeText(MedicionActivity.this, "Medicion " + med.getMedicion().toString() + " almacenada con exito", Toast.LENGTH_SHORT).show();
                                Intent it = new Intent(MedicionActivity.this, HistorialActivity.class);
                                startActivity(it);
                            }
                        });
            }else{
                Toast.makeText(MedicionActivity.this, "Ingrese datos de altura y peso.", Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicion);

        contextMedir = getApplicationContext();
        db = FirebaseFirestore.getInstance();

        txtAltura = findViewById(R.id.txtAltura);
        txtPeso = findViewById(R.id.txtPeso);

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

        btnMedirImc = findViewById(R.id.btnMedirImc);
        btnMedirImc.setOnClickListener(btnMedirImcListener);






    }

}