package com.ciclo4.misionfit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.ciclo4.misionfit.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Objects;

public class ProfileBienestarActivity extends AppCompatActivity {

    ImageButton btnHomeH, btnPerfilH, btnHistorialH;
    ConstraintLayout historialLay, salirLay;
    Context contextPerfil;
    TextView txtNomb;
    FirebaseAuth au;

    private final View.OnClickListener historialLayListener = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            Intent intentHistory = new Intent(contextPerfil, SearchBienestarActivity.class);
            startActivity(intentHistory);
            finish();
        }
    };

    private final View.OnClickListener btnHomeHListener = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            Intent intentHome = new Intent(contextPerfil, HomeBienestarActivity.class);
            startActivity(intentHome);
            finish();
        }
    };

    private final View.OnClickListener btnPerfilHListener = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            Intent intentProfile = new Intent(contextPerfil, ProfileBienestarActivity.class);
            startActivity(intentProfile);
            finish();
        }
    };

    private final View.OnClickListener btnHistorialHListener = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            Intent intentHistory = new Intent(contextPerfil, SearchBienestarActivity.class);
            startActivity(intentHistory);
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_bienestar);

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
            Toast.makeText(ProfileBienestarActivity.this,"Usuario sin Sesion",
                    Toast.LENGTH_SHORT).show();
            Intent it = new Intent(ProfileBienestarActivity.this,MainActivity.class);
            startActivity(it);
        }

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        txtNomb = findViewById(R.id.txtNomb);

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
                                User user1 = new User(email, nombre, apellido, rol);
                                Log.d("Main", user1.getRol());
                                if(Objects.equals(user1.getEmail(), user.getEmail())){
                                    txtNomb.setText(user1.getNombre());
                                }
                            }
                        }else{
                            Log.w("Main", "Error getting documents", task.getException());
                        }
                    }
                });
    }

    View.OnClickListener ev = new View.OnClickListener()
    {
        @Override
        public void onClick(View v) {
            FirebaseAuth.getInstance().signOut();
            Intent it = new Intent(ProfileBienestarActivity.this, LoginBienestarActivity.class);
            startActivity(it);
            Toast.makeText(ProfileBienestarActivity.this,"Has salido", Toast.LENGTH_SHORT).show();
        }
    };
}