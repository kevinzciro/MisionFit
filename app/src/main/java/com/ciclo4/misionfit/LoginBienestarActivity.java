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
import android.widget.TextView;
import android.widget.Toast;

import com.ciclo4.misionfit.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Objects;

public class LoginBienestarActivity extends AppCompatActivity {

    Context contextLogin;
    Button btnLogin;
    TextView link;
    EditText txtCorreo, txtContrasena;
    FirebaseAuth au;

    private final View.OnClickListener linkListener = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            Intent intentLogin = new Intent(contextLogin, LoginActivity.class);
            startActivity(intentLogin);
            finish();
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_bienestar);

        au = FirebaseAuth.getInstance();

        txtCorreo = findViewById(R.id.txtCorreo);
        txtContrasena = findViewById(R.id.txtContrasena);

        contextLogin = getApplicationContext();

        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(ev);

        link = findViewById(R.id.linkNormal);
        link.setOnClickListener(linkListener);
    }

    View.OnClickListener ev = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            String correo = txtCorreo.getText().toString();
            String contrasena = txtContrasena.getText().toString();
            au.signInWithEmailAndPassword(correo,contrasena)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {
                                if(task.isSuccessful()) {
                                    FirebaseUser user = au.getCurrentUser();
                                    Intent it = new Intent(LoginBienestarActivity.this, HomeBienestarActivity.class);
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
                                                                if (Objects.equals(user1.getRol(), "Bienestar")){
                                                                    Toast.makeText(LoginBienestarActivity.this, "Acceso Concedido", Toast.LENGTH_SHORT).show();
                                                                    it.putExtra("nombre", user1.getNombre());
                                                                    it.putExtra("apellido", user1.getApellido());
                                                                    it.putExtra("email", user1.getEmail());
                                                                    startActivity(it);
                                                                }
                                                                else {
                                                                    Toast.makeText(LoginBienestarActivity.this,"Usted es un usuario estandar...",
                                                                            Toast.LENGTH_SHORT).show();
                                                                    FirebaseAuth.getInstance().signOut();
                                                                }
                                                            }
                                                        }
                                                    }else{
                                                        Log.w("Main", "Error getting documents", task.getException());
                                                    }
                                                }
                                            });
                                }
                            }else{
                                Toast.makeText(LoginBienestarActivity.this, "Verifique correo y contrase??a...", Toast.LENGTH_SHORT).show();
                            }
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                        }
                    });
        }
    };

}