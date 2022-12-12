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
import android.widget.ImageButton;

import com.ciclo4.misionfit.models.Medition;
import com.ciclo4.misionfit.models.User;
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

public class SearchBienestarActivity extends AppCompatActivity {

    ImageButton btnHomeH, btnPerfilH, btnHistorialH;
    Context contextBuscar;
    FirebaseAuth au;

    private final View.OnClickListener btnHomeHListener = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            Intent intentHome = new Intent(contextBuscar, HomeBienestarActivity.class);
            startActivity(intentHome);
            finish();
        }
    };

    private final View.OnClickListener btnPerfilHListener = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            Intent intentProfile = new Intent(contextBuscar, ProfileBienestarActivity.class);
            startActivity(intentProfile);
            finish();
        }
    };

    private final View.OnClickListener btnHistorialHListener = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            Intent intentHistory = new Intent(contextBuscar, SearchBienestarActivity.class);
            startActivity(intentHistory);
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_bienestar);

        contextBuscar = getApplicationContext();

        btnHomeH = findViewById(R.id.btnHomeH);
        btnHomeH.setOnClickListener(btnHomeHListener);

        btnPerfilH = findViewById(R.id.btnPerfilH);
        btnPerfilH.setOnClickListener(btnPerfilHListener);

        btnHistorialH = findViewById(R.id.btnHistorialH);
        btnHistorialH.setOnClickListener(btnHistorialHListener);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        au= FirebaseAuth.getInstance();
        FirebaseUser user = au.getCurrentUser();

        RecyclerView recyclerView = findViewById(R.id.recycler1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<User> userList = new ArrayList<User>();

        db.collection("Usuarios")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for(QueryDocumentSnapshot document : task.getResult()){
                                Log.d("Main",document.getId() + "=>" + document.getData());
                                String email = document.getString("email");
                                String nombre = document.getString("nombre");
                                String apellido = document.getString("apellido");
                                String rol = document.getString("rol");
                                String id = document.getId();
                                User us = new User(email, nombre, apellido, rol);
                                userList.add(us);
                            }
                            MyAdapter1 adapter = new MyAdapter1(getApplicationContext(), userList);
                            adapter.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent it = new Intent(contextBuscar, UserSearch.class);
                                    it.putExtra("eEmail", userList.get(recyclerView.getChildAdapterPosition(v)).getEmail());
                                    it.putExtra("eNombre", userList.get(recyclerView.getChildAdapterPosition(v)).getNombre());
                                    it.putExtra("eApellido", userList.get(recyclerView.getChildAdapterPosition(v)).getApellido());
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