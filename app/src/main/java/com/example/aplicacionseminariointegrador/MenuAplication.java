package com.example.aplicacionseminariointegrador;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.EditText;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MenuAplication extends AppCompatActivity {

    ImageButton btnCalledPolice, btnGoogleMaps;

    private static final int REQUEST_CALL = 1;
    private EditText mEditTextNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout. activity_menu_aplication);

        btnCalledPolice = (ImageButton) findViewById(R.id.imgBtnPolice);

        btnCalledPolice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makePhoneCall();
            }
        });

        btnGoogleMaps = (ImageButton) findViewById(R.id.imgBtnSecurityGuard);
        btnGoogleMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToSecurityGuard();
            }
        });

    }

    private void goToSecurityGuard() {
        Intent nextActivity = new Intent(this, GoogleMapsLocation.class);
        startActivity(nextActivity);
    }



    private void makePhoneCall() {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:911"));
        startActivity(intent);

        Log.d("Eror", "No llama");
    }





    // Metodo para el boton Ingresar a los creditos.
    public void changeActivityPresentation(View view) {
        //Intent nextActivity = new Intent(this, StartActivity.class);
        //startActivity(nextActivity);
    }

}