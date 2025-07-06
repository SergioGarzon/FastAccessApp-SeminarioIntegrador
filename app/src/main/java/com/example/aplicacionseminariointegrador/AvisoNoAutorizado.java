package com.example.aplicacionseminariointegrador;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.aplicacionseminariointegrador.auxiliarclases.LanguageSelected;

public class AvisoNoAutorizado extends AppCompatActivity {

    Button btnBackLogin;
    TextView txtUserNoAutorized;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aviso_no_autorizado);

        btnBackLogin = (Button) findViewById(R.id.btnInvalidBackLogin);
        txtUserNoAutorized = (TextView) findViewById(R.id.txtLblUserNoAutorized);

        if(LanguageSelected.languageSelected == 0) {
            txtUserNoAutorized.setText("User no autorized");
            btnBackLogin.setText("BACK LOGIN");
        } else {
            txtUserNoAutorized.setText("Usuario no autorizado");
            btnBackLogin.setText("VOLVER AL ACCESO");
        }

        Vibrator vibrator = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(1000);
    }

    // Metodo para el boton Ingresar
    public void changeActivityMainBackLogin(View view) {
        Intent nextActivity = new Intent(this, LoginActivity.class);
        startActivity(nextActivity);
    }

}