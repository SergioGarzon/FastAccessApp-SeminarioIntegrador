package com.example.aplicacionseminariointegrador;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.aplicacionseminariointegrador.auxiliarclases.LanguageSelected;

public class RegisterSucessful extends AppCompatActivity {

    Button btnUserCreated;
    TextView lblMessageUserCreated;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_sucessful);

        btnUserCreated = (Button) findViewById(R.id.bntUserCreatedRegsister);
        lblMessageUserCreated = (TextView) findViewById(R.id.lblMessageUserCreated);

        if(LanguageSelected.languageSelected == 0) {
            btnUserCreated.setText("OK");
            lblMessageUserCreated.setText("User created sucessfully. You must wait to be autorized!");
        } else {
            btnUserCreated.setText("ACEPTAR");
            lblMessageUserCreated.setText("Usuario creado exitosamente. Debe esperar a que se autorice");
        }

        btnUserCreated.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeBackActivityStartDisplay();
            }
        });
    }

    private void changeBackActivityStartDisplay() {
        Intent nextActivity = new Intent(this, StartApplication.class);
        startActivity(nextActivity);
    }


}