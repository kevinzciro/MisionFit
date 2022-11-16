package com.ciclo4.misionfit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.ciclo4.misionfit.config.Adapter;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    Context contextLogin;
    TextView link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        contextLogin = getApplicationContext();
//        link =(TextView)findViewById(R.id.linkBienestar);
//        link.setClickable(true);
//        link.setMovementMethod(LinkMovementMethod.getInstance());
//        link.setText(Html.fromHtml("text"));

    }




}