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

        btnEmergencyNewActivity = (Button) findViewById(R.id.btnMenuCallEmergency);
        btnLogoutMenuApplication = (Button) findViewById(R.id.btnLogoutMenuApplication);
        btnMenuAccountProfiler = (Button) findViewById(R.id.btnMenuAccountProfiler);
        btnMenuChat = (Button) findViewById(R.id.btnMenuChat);
        btnMenuNotification = (Button) findViewById(R.id.btnMenuNotifications);
        btnMenuVisitorRegister = (Button) findViewById(R.id.btnMenuRegisterVisitors);
        btnMenuPeopleHistory = (Button) findViewById(R.id.btnMenuPeopleHistory);
        txtLblWelcomeFastAccessApp = (TextView) findViewById(R.id.txtLblWelcomeFastAccessApp);

        if(LanguageSelected.languageSelected == 0) {
            btnEmergencyNewActivity.setText("CALL EMERGENCY");
            btnMenuAccountProfiler.setText("MANAGER YOUR ACCOUNT");
            btnMenuChat.setText("CHAT");
            btnMenuNotification.setText("NOTIFICATIONS");
            btnMenuVisitorRegister.setText("VISITOR REGISTER");
            btnMenuPeopleHistory.setText("ACCESS HISTORY");
            txtLblWelcomeFastAccessApp.setText("Welcome to");
            btnLogoutMenuApplication.setText("LOGOUT");
        } else {
            btnEmergencyNewActivity.setText("LLAMAR A EMERGENCIAS");
            btnMenuAccountProfiler.setText("ADMINISTRAR TU CUENTA");
            btnMenuChat.setText("CHATEAR CON LOS USUARIOS");
            btnMenuNotification.setText("NOTIFICACIONES");
            btnMenuVisitorRegister.setText("REGISTRAR VISITANTE");
            btnMenuPeopleHistory.setText("HISTORIAL DE ACCESOS");
            txtLblWelcomeFastAccessApp.setText("Bienvenido a");
            btnLogoutMenuApplication.setText("CERRAR SESION");
        }

        btnEmergencyNewActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makePhoneCall(v);
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

    @Override
    public void onBackPressed() {
        //Toast.makeText(StartApplication.this, "Action not allowed!", Toast.LENGTH_SHORT).show();
        return;
    }

}