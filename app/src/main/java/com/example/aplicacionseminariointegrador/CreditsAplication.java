package com.example.aplicacionseminariointegrador;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class CreditsAplication extends AppCompatActivity {

    ImageButton imgBtnSergioLinkedIn, imgBtnFacundoLinkedIn,imgBtnGenaroLinkedIn, imgBtnMatiasLinkedIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits_aplication);

        imgBtnSergioLinkedIn = (ImageButton) findViewById(R.id.imgBtnLinkedinSergio);

        imgBtnSergioLinkedIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://www.linkedin.com/in/sergio-gabriel-garzon/"); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        imgBtnFacundoLinkedIn = (ImageButton) findViewById(R.id.imgBtnLinkedinFacundo);

        imgBtnFacundoLinkedIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://www.linkedin.com/in/facundo-rago/"); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        imgBtnGenaroLinkedIn = (ImageButton) findViewById(R.id.imgBtnLinkedInGenaro);

        imgBtnGenaroLinkedIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://www.linkedin.com/in/genaro-paredes-6b5785238/"); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        imgBtnMatiasLinkedIn = (ImageButton) findViewById(R.id.imgBtnLinkedinMatias);

        imgBtnMatiasLinkedIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://www.linkedin.com/in/matias-leonel-farach-b2482528a/"); // missing 'http://' will cause crashed
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