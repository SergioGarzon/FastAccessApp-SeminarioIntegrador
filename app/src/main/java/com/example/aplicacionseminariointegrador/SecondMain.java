package com.example.aplicacionseminariointegrador;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SecondMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_main);
    }

    // Metodo para el boton Ingresar
    public void changeActivity(View view) {
        Intent nextActivity = new Intent(this, MainActivity.class);
        startActivity(nextActivity);
    }
}