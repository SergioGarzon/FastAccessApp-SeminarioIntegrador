package com.example.aplicacionseminariointegrador;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.aplicacionseminariointegrador.auxiliarclases.LanguageSelected;

import org.w3c.dom.Text;

public class RegisterAccount extends AppCompatActivity {

    Button btnCancelCreateAccount, btnRegisterUser;
    TextView txtLblCreateAccount, txtLblNameSurnames,
            txtLblUsernameCreateAccount, txtLblPasswordCreateAccount, txtLblSelectRole;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_account);

        btnCancelCreateAccount = (Button) findViewById(R.id.btnCancelCreateAccount);
        btnRegisterUser = (Button) findViewById(R.id.btnRegisterUser);

        txtLblCreateAccount = (TextView) findViewById(R.id.txtLblCreateAccount);
        txtLblNameSurnames = (TextView) findViewById(R.id.txtLblNamesSurnames);
        txtLblUsernameCreateAccount = (TextView) findViewById(R.id.txtLblUsernameCreateAccount);
        txtLblPasswordCreateAccount = (TextView) findViewById(R.id.txtLblPasswordCreateAccount);
        txtLblSelectRole = (TextView) findViewById(R.id.txtLblSelectRole);

        if(LanguageSelected.languageSelected == 0) {
            btnCancelCreateAccount.setText("CANCEL");
            btnRegisterUser.setText("REGISTER");
            txtLblCreateAccount.setText("CREATE ACCOUNT");
            txtLblNameSurnames.setText("Name and surnames:");
            txtLblUsernameCreateAccount.setText("Username:");
            txtLblPasswordCreateAccount.setText("Password:");
            txtLblSelectRole.setText("Select Role:");
        } else {
            btnCancelCreateAccount.setText("CANCELAR");
            btnRegisterUser.setText("REGISTRARSE");
            txtLblCreateAccount.setText("CREAR CUENTA");
            txtLblNameSurnames.setText("Nombre y apellido:");
            txtLblUsernameCreateAccount.setText("Nombre de usuario:");
            txtLblPasswordCreateAccount.setText("Contraseña:");
            txtLblSelectRole.setText("Seleccionar rol:");
        }

        btnCancelCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backStart_Activity(v);
            }
        });

        btnRegisterUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextRegisterSucessfully();
            }
        });
    }

    private void backStart_Activity(View v) {
        Intent nextActivity = new Intent(this, StartApplication.class);
        startActivity(nextActivity);
    }

    private void nextRegisterSucessfully() {
        Intent nextActivity = new Intent(this, RegisterSucessful.class);
        startActivity(nextActivity);
    }
}