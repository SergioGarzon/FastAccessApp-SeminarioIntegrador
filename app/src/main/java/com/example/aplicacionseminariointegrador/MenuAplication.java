package com.example.aplicacionseminariointegrador;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuAplication extends AppCompatActivity {

    Button btnEmergencyNewActivity, btnLogoutMenuApplication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout. activity_menu_aplication);

        btnEmergencyNewActivity = (Button) findViewById(R.id.btnEmergencyNewActivity);
        btnLogoutMenuApplication = (Button) findViewById(R.id.btnLogoutMenuApplication);

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


    }

    private void makePhoneCall(View v) {
        Intent nextActivity = new Intent(this, EmergencyCall.class);
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