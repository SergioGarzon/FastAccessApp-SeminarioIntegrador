package com.example.aplicacionseminariointegrador;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.aplicacionseminariointegrador.auxiliarclases.LanguageSelected;

public class MenuApplicationResidente extends AppCompatActivity {

    Button btnRegisterVisitorResident, btnLogoutResident, btnCallEmergencyResident;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_application_residente);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        btnRegisterVisitorResident = findViewById(R.id.btnRegisterVisitorResident);
        btnLogoutResident = findViewById(R.id.btnLogoutResident);
        btnCallEmergencyResident = findViewById(R.id.btnCallEmergencyResident);

        Intent intent = getIntent();
        String user = intent.getStringExtra("Usuario");

        if(LanguageSelected.languageSelected == 0) {
            btnRegisterVisitorResident.setText("REGISTER VISITOR");
            btnLogoutResident.setText("LOGOUT");
            btnCallEmergencyResident.setText("CALL EMERGENCY");
        } else {
            btnRegisterVisitorResident.setText("REGISTRAR VISITANTE");
            btnLogoutResident.setText("CERRAR SESION");
            btnCallEmergencyResident.setText("LLAMAR A EMERGENCIAS");
        }

        btnRegisterVisitorResident.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerVisitorActivity(v);
            }
        });

        btnLogoutResident.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logoutSessionResident(v);
            }
        });

        btnCallEmergencyResident.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makePhoneCall(v);
            }
        });
    }

    private void makePhoneCall(View v) {
        Intent nextActivity = new Intent(this, EmergencyCall.class);
        startActivity(nextActivity);
    }

    private void registerVisitorActivity(View v) {
        Intent nextActivity = new Intent(this, VisitorsRegister.class);
        startActivity(nextActivity);
    }

    private void logoutSessionResident(View v) {
        Intent nextActivity = new Intent(this, StartApplication.class);
        startActivity(nextActivity);
    }

    @Override
    public void onBackPressed() {
        //Toast.makeText(StartApplication.this, "Action not allowed!", Toast.LENGTH_SHORT).show();
        return;
    }
}