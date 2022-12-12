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

public class UserSearch extends AppCompatActivity {

    ImageButton btnHomeH, btnPerfilH, btnHistorialH;
    Button btnMedicionM, btnHistorialM;
    Context context;
    TextView txtImc, txtRecom;
    FirebaseAuth au;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        context = getApplicationContext();

        RecyclerView recyclerView = findViewById(R.id.recycler3);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<Medition> medList = new ArrayList<Medition>();

        au= FirebaseAuth.getInstance();
        FirebaseUser user = au.getCurrentUser();

        Bundle bundle = getIntent().getExtras();

        String email = bundle.getString("eEmail");
        String nombre = bundle.getString("eNombre");
        String apellido = bundle.getString("eApellido");


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
                                if (Objects.equals(med.getCorreo_usuario(), email)){
                                    medList.add(med);
                                }
                            }
                            MyAdapter adapter = new MyAdapter(getApplicationContext(), medList);
                            adapter.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent it = new Intent(context, MedicionSearch.class);
                                    Log.d("Main", medList.get(recyclerView.getChildAdapterPosition(v)).getId());
                                    it.putExtra("eId", medList.get(recyclerView.getChildAdapterPosition(v)).getId());
                                    it.putExtra("eEmail", medList.get(recyclerView.getChildAdapterPosition(v)).getCorreo_usuario());
                                    it.putExtra("eMedicion", medList.get(recyclerView.getChildAdapterPosition(v)).getMedicion().toString());
                                    it.putExtra("eFecha", medList.get(recyclerView.getChildAdapterPosition(v)).getFecha_medicion());
                                    startActivity(it);
                                }
                            });
                            recyclerView.setAdapter(adapter);
                        }else{
                            Log.w("Main", "Error getting documents", task.getException());
                        }
                    }
                });
    }
}