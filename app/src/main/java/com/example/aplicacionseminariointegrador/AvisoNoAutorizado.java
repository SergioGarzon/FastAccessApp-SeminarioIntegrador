package com.example.aplicacionseminariointegrador;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;

public class AvisoNoAutorizado extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aviso_no_autorizado);
        Vibrator vibrator = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(1000);

    }

    @Override
    public void onBackPressed() {
        //Toast.makeText(StartApplication.this, "Action not allowed!", Toast.LENGTH_SHORT).show();
        return;
    }

    // Metodo para el boton Ingresar
    public void changeActivityMainBackLogin(View view) {
        Intent nextActivity = new Intent(this, LoginActivity.class);
        startActivity(nextActivity);
    }

}