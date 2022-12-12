package com.ciclo4.misionfit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ciclo4.misionfit.models.Medition;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

public class MedicionSearch extends AppCompatActivity {

    TextView tvMed, tvFecha, tvEmail;
    Button bt1, bt2;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicion_search);

        tvMed = findViewById(R.id.tvNom);
        tvFecha = findViewById(R.id.tvPro);
        tvEmail = findViewById(R.id.tvEstId);

        context = getApplicationContext();

        Bundle bundle = getIntent().getExtras();

        String id = null;
        if (bundle != null) {
            id = bundle.getString("eId");
            String email = bundle.getString("eEmail");
            String medicion = bundle.getString("eMedida");
            String fecha = bundle.getString("eFecha");
            tvMed.setText(medicion);
            tvFecha.setText(fecha);
            tvEmail.setText(email);
            Log.d("MainActivity", id);
        }

        Medition e = new Medition();
        e.setId(id);
        e.setMedicion(e.getMedicion());
        e.setFecha_medicion(e.getFecha_medicion());
        e.setCorreo_usuario(e.getCorreo_usuario());
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        tvEmail = findViewById(R.id.tvEstId);

        String finalId = id;
        View.OnClickListener ev = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.collection("Medicion")
                        .document(finalId)
                        .update("medicion", tvMed.getText().toString(), "fecha_medicion", tvFecha.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(MedicionSearch.this, "Datos actualizados", Toast.LENGTH_SHORT).show();
                                Intent it = new Intent(context, HomeBienestarActivity.class);
                                startActivity(it);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(MedicionSearch.this, "Problema con la actualizacion", Toast.LENGTH_SHORT).show();
                                Log.w("MainActivity", "Error", e);
                            }
                        });
            }
        };

        View.OnClickListener evE = new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MedicionSearch.this);
                builder.setMessage("¿Desea eliminar la medicion?")
                        .setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int which) {
                                Log.d("MainActivity", "Eliminar...");
                                db.collection("Medicion")
                                        .document(finalId)
                                        .delete()
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                Toast.makeText(MedicionSearch.this, "Medicion eliminada", Toast.LENGTH_SHORT).show();
                                                Intent it = new Intent(context, HomeBienestarActivity.class);
                                                startActivity(it);
                                            }
                                        });
                            }
                        })
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                builder.create().show();
            }
        };

        bt1 = (Button) findViewById(R.id.btnActualizar);
        bt1.setOnClickListener(ev);
        bt2 = (Button) findViewById(R.id.btnEliminar);
        bt2.setOnClickListener(evE);
    }
}