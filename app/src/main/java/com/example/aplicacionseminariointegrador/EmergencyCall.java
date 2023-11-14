package com.example.aplicacionseminariointegrador;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.aplicacionseminariointegrador.auxiliarclases.LanguageSelected;

public class EmergencyCall extends AppCompatActivity {

    ImageButton btnEmergency, btnPolice, btnSecurity, btnFireFighters;
    Button btnBackEmergencyMenuApplication;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_call);

        btnEmergency = (ImageButton) findViewById(R.id.btnImgMedicalEmergency);
        btnPolice = (ImageButton) findViewById(R.id.imgBtnCallPolice);
        btnSecurity = (ImageButton) findViewById(R.id.imgBtnSecurityGuardCountry);
        btnFireFighters = (ImageButton) findViewById(R.id.imgBtnFireFigther);
        btnBackEmergencyMenuApplication = (Button) findViewById(R.id.btnBackEmergencyMenuApplication);

        if(LanguageSelected.languageSelected == 0) {
            btnBackEmergencyMenuApplication.setText("BACK");
        } else {
            btnBackEmergencyMenuApplication.setText("VOLVER");
        }

        btnEmergency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makePhoneAndCall("107");
            }
        });

        btnPolice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makePhoneAndCall("911");
            }
        });

        btnFireFighters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makePhoneAndCall("101");
            }
        });

        btnSecurity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makePhoneAndCall("3517416569");
            }
        });

        btnBackEmergencyMenuApplication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backMenuApplication(v);
            }
        });

    }

    private void backMenuApplication(View v) {
        switch (LanguageSelected.sesion) {
            case 0:
                Intent nextActivity = new Intent(this, UsuarioPendienteAprobacion.class);
                startActivity(nextActivity);
                break;
            case 1:
                Intent nextActivity2 = new Intent(this, AvisoNoAutorizado.class);
                startActivity(nextActivity2);
                break;
            case 2:
                Intent nextActivity3 = new Intent(this, MenuApplicationResidente.class);
                nextActivity3.putExtra("Usuario", "nuevamente");
                startActivity(nextActivity3);
                break;
            case 3:
                Intent nextActivity4 = new Intent(this, MenuAplication.class);
                nextActivity4.putExtra("Usuario", "nuevamente");
                startActivity(nextActivity4);
                break;
        }
    }
    private void makePhoneAndCall(String tel) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + tel));
        startActivity(intent);

        Log.d("Eror", "No llama");
    }
}