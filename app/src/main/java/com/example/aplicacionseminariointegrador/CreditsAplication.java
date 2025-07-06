package com.example.aplicacionseminariointegrador;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import com.example.aplicacionseminariointegrador.auxiliarclases.LanguageSelected;

public class CreditsAplication extends AppCompatActivity {

    ImageButton imgBtnSergioLinkedIn, imgBtnFacundoLinkedIn,imgBtnGenaroLinkedIn, imgBtnMatiasLinkedIn;
    TextView lblCredits;

    Button btnCreditsOk;

    LanguageSelected languageSelected;

    String url = "https://www.linkedin.com/in/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits_aplication);

        lblCredits = (TextView) findViewById(R.id.lblTxtCredits);

        btnCreditsOk = (Button) findViewById(R.id.btnCreditsOk);

        if(LanguageSelected.languageSelected == 0) {
            lblCredits.setText("Fast Access App is a project development by UTN FRC's students:");
            btnCreditsOk.setText("OK");
        }
        else {
            lblCredits.setText("Fast Access App es un proyecto desarrollado por estudiantes de la UTN FRC:");
            btnCreditsOk.setText("ACEPTAR");
        }

        imgBtnSergioLinkedIn = (ImageButton) findViewById(R.id.imgBtnLinkedinSergio);

        imgBtnSergioLinkedIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(url +"sergio-gabriel-garzon/"); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        imgBtnFacundoLinkedIn = (ImageButton) findViewById(R.id.imgBtnLinkedinFacundo);

        imgBtnFacundoLinkedIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(url + "facundo-rago/"); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        imgBtnGenaroLinkedIn = (ImageButton) findViewById(R.id.imgBtnLinkedInGenaro);

        imgBtnGenaroLinkedIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(url + "genaro-paredes-6b5785238/"); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        imgBtnMatiasLinkedIn = (ImageButton) findViewById(R.id.imgBtnLinkedinMatias);

        imgBtnMatiasLinkedIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(url +"matias-leonel-farach-b2482528a/"); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
    }

    // Metodo para el boton Ingresar
    public void changeActivity(View view) {
        Intent nextActivity = new Intent(this, StartApplication.class);
        startActivity(nextActivity);
    }
}