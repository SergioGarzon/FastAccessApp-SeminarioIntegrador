package com.example.aplicacionseminariointegrador;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.view.View;
import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // Metodo para el boton Ingresar
    public void changeActivity(View view) {
        Intent nextActivity = new Intent(this, SecondMain.class);
        startActivity(nextActivity);
    }

    @SuppressLint("")
    public void closeApp(View view) {
        finishAffinity();
    }

}