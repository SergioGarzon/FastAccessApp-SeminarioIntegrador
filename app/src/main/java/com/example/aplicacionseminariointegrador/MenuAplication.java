package com.example.aplicacionseminariointegrador;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.aplicacionseminariointegrador.auxiliarclases.LanguageSelected;

public class MenuAplication extends AppCompatActivity {

    Button btnEmergencyNewActivity, btnLogoutMenuApplication, btnMenuAccountProfiler,
            btnMenuChat, btnMenuNotification, btnMenuVisitorRegister, btnMenuPeopleHistory;
    TextView txtLblWelcomeFastAccessApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout. activity_menu_aplication);

        btnEmergencyNewActivity = findViewById(R.id.btnMenuCallEmergency);
        btnLogoutMenuApplication = findViewById(R.id.btnLogoutMenuApplication);
        btnMenuAccountProfiler = findViewById(R.id.btnMenuAccountProfiler);
        btnMenuChat = findViewById(R.id.btnMenuChat);
        btnMenuNotification = findViewById(R.id.btnMenuNotifications);
        btnMenuVisitorRegister = findViewById(R.id.btnMenuRegisterVisitors);
        btnMenuPeopleHistory = findViewById(R.id.btnMenuPeopleHistory);
        txtLblWelcomeFastAccessApp = findViewById(R.id.txtLblWelcomeFastAccessApp);

        Intent intent = getIntent();
        String user = intent.getStringExtra("Usuario");

        if(LanguageSelected.languageSelected == 0) {
            btnEmergencyNewActivity.setText("CALL EMERGENCY");
            btnMenuAccountProfiler.setText("MANAGER YOUR ACCOUNT");
            btnMenuChat.setText("CHAT");
            btnMenuNotification.setText("NOTIFICATIONS");
            btnMenuVisitorRegister.setText("VISITOR REGISTER");
            btnMenuPeopleHistory.setText("ACCESS HISTORY");
            txtLblWelcomeFastAccessApp.setText("Welcome " + user + " to");
            btnLogoutMenuApplication.setText("LOGOUT");
        } else {
            btnEmergencyNewActivity.setText("LLAMAR A EMERGENCIAS");
            btnMenuAccountProfiler.setText("ADMINISTRAR TU CUENTA");
            btnMenuChat.setText("CHATEAR CON LOS USUARIOS");
            btnMenuNotification.setText("NOTIFICACIONES");
            btnMenuVisitorRegister.setText("REGISTRAR VISITANTE");
            btnMenuPeopleHistory.setText("HISTORIAL DE ACCESOS");
            txtLblWelcomeFastAccessApp.setText("Bienvenido " + user + " a");
            btnLogoutMenuApplication.setText("CERRAR SESION");
        }

        btnEmergencyNewActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makePhoneCall(v);
            }
        });

        btnMenuAccountProfiler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lectorQR(v);
            }
        });

        btnLogoutMenuApplication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logoutSesion(v);
            }
        });

        btnMenuVisitorRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerVisitorActivity(v);
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

    private void logoutSesion(View v) {
        Intent nextActivity = new Intent(this, StartApplication.class);
        startActivity(nextActivity);
    }

    private void lectorQR(View v) {
        Intent nextActivity = new Intent(this, LectorQR.class);
        startActivity(nextActivity);
    }

    @Override
    public void onBackPressed() {
        //Toast.makeText(StartApplication.this, "Action not allowed!", Toast.LENGTH_SHORT).show();
        return;
    }

}