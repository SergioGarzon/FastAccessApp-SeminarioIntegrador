package com.example.aplicacionseminariointegrador;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.aplicacionseminariointegrador.auxiliarclases.LanguageSelected;

public class UsuarioPendienteAprobacion extends AppCompatActivity {

    Button btnBackLoginWaitingAutorization;
    TextView txtUserWaitingAutorization;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_pendiente_aprobacion);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        btnBackLoginWaitingAutorization = findViewById(R.id.btnBackLoginWaitAutorization);
        txtUserWaitingAutorization = findViewById(R.id.txtUserWaitAutorization);

        if(LanguageSelected.languageSelected == 0) {
            btnBackLoginWaitingAutorization.setText("BACK LOGIN");
            txtUserWaitingAutorization.setText("User waiting autorization!");
        } else {
            btnBackLoginWaitingAutorization.setText("VOLVER AL ACCESO");
            txtUserWaitingAutorization.setText("¡Usuario a la espera de la autorización!");
        }

        btnBackLoginWaitingAutorization.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeActivityBackLoginInvalidAutorization(v);
            }
        });

    }

    private void changeActivityBackLoginInvalidAutorization(View v) {
        Intent nextActivity = new Intent(this, LoginActivity.class);
        startActivity(nextActivity);
    }

    @Override
    public void onBackPressed() {
        //Toast.makeText(StartApplication.this, "Action not allowed!", Toast.LENGTH_SHORT).show();
        return;
    }
}