package com.ciclo4.misionfit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ciclo4.misionfit.config.Adapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    Context contextLogin;
    Button btnLogin;
    TextView link;
    EditText txtCorreo, txtContrasena;
    FirebaseAuth au;

    private final View.OnClickListener linkListener = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            Intent intentBienestar = new Intent(contextLogin, LoginBienestarActivity.class);
            startActivity(intentBienestar);
            finish();
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        au = FirebaseAuth.getInstance();

        txtCorreo = findViewById(R.id.txtCorreo);
        txtContrasena = findViewById(R.id.txtContrasena);

        contextLogin = getApplicationContext();

        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(ev);

        link = findViewById(R.id.linkBienestar);
        link.setOnClickListener(linkListener);
    }

    View.OnClickListener ev = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String correo = txtCorreo.getText().toString();
            String contrasena = txtContrasena.getText().toString();
            au.signInWithEmailAndPassword(correo,contrasena)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {
                                Toast.makeText(LoginActivity.this, "Acceso Concedido", Toast.LENGTH_SHORT).show();
                                if(task.isSuccessful()) {
                                    FirebaseUser user = au.getCurrentUser();
                                    Log.d("MainActivity", "Usuario:" + user.getEmail());
                                    Toast.makeText(LoginActivity.this, "Acceso Concedido", Toast.LENGTH_SHORT).show();
                                    Intent it = new Intent(LoginActivity.this, HomeActivity.class);
                                    startActivity(it);
                                }
                            }else{
                                Toast.makeText(LoginActivity.this, "Error", Toast.LENGTH_SHORT).show();
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