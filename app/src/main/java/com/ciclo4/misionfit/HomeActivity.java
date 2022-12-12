package com.ciclo4.misionfit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.ciclo4.misionfit.models.Medition;
import com.ciclo4.misionfit.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Objects;

public class HomeActivity extends AppCompatActivity {

    Context contextHome;
    Button btnHistorial, btnMedicion;
    ImageButton btnHomeH, btnPerfilH, btnHistorialH;
    TextView txtBienv;
    FirebaseAuth au;

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

        txtBienv = findViewById(R.id.txtBienv);

        au = FirebaseAuth.getInstance();
        FirebaseUser user = au.getCurrentUser();

        FirebaseFirestore db = FirebaseFirestore.getInstance();

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
                                    txtBienv.setText("Hola " + user1.getNombre());
                                }
                            }
                        }else{
                            Log.w("Main", "Error getting documents", task.getException());
                        }
                    }
                });



    }


}