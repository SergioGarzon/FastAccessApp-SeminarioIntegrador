package com.example.aplicacionseminariointegrador;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class CreditsAplication extends AppCompatActivity {

    ImageButton getImgBtnSergioLinkedIn, imgBtnFacundoLinkedIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits_aplication);

        getImgBtnSergioLinkedIn = (ImageButton) findViewById(R.id.imgBtnLinkedinSergio);

        getImgBtnSergioLinkedIn.setOnClickListener(new View.OnClickListener() {
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

    }

    // Metodo para el boton Ingresar
    public void changeActivity(View view) {
        Intent nextActivity = new Intent(this, StartApplication.class);
        startActivity(nextActivity);
    }
}