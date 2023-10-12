package com.example.aplicacionseminariointegrador;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MenuAplication extends AppCompatActivity {

    ImageButton btnCalledPolice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_aplication);

        btnCalledPolice = (ImageButton) findViewById(R.id.imgBtnPolice);

        btnCalledPolice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("Tel:911"));

                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });
    }

    // Metodo para el boton Ingresar a los creditos.
    public void changeActivityPresentation(View view) {
        //Intent nextActivity = new Intent(this, StartActivity.class);
        //startActivity(nextActivity);
    }

    public void callPolice(View view) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:3517416569"));
        startActivity(intent);
    }
}