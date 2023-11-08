package com.example.aplicacionseminariointegrador;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.aplicacionseminariointegrador.auxiliarclases.LanguageSelected;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {

    Button btnLogin, btnCancelLogin;
    TextInputLayout txtUsername, txtPassword;
    TextView txtLblUsername, txtLblPassword, lblLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnCancelLogin = (Button) findViewById(R.id.btnCancelLogin);
        txtUsername = (TextInputLayout) findViewById(R.id.txtInputUsername);
        txtPassword = (TextInputLayout) findViewById(R.id.txtInputPassword);
        txtLblUsername = (TextView) findViewById(R.id.txtLblUsername);
        txtLblPassword = (TextView) findViewById(R.id.txtLblPass);
        lblLogin = (TextView) findViewById(R.id.lblLogin);

        if(LanguageSelected.languageSelected == 0) {
            btnLogin.setText("SIGN IN");
            btnCancelLogin.setText("CANCEL");
            txtLblUsername.setText("Username:");
            txtLblPassword.setText("Password:");
            lblLogin.setText("LOGIN");
        } else {
            btnLogin.setText("INICIAR SESION");
            btnCancelLogin.setText("CANCELAR");
            txtLblUsername.setText("Nombre de usuario:");
            txtLblPassword.setText("Contraseña");
            lblLogin.setText("ACCESO");
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {  validateUser(v);  }
        });

        btnCancelLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backStartActivity(v);
            }
        });
    }

    private void backStartActivity(View v) {
        Intent nextActivity = new Intent(this, StartApplication.class);
        startActivity(nextActivity);
    }

    private void validateUser(View v) {

        if(txtUsername.getEditText().getText().toString().equals("Admin") && txtPassword.getEditText().getText().toString().equals("1")) {
            Intent nextActivity = new Intent(this, MenuAplication.class);
            startActivity(nextActivity);
        }

        if(txtUsername.getEditText().getText().toString().equals("Otro") && txtPassword.getEditText().getText().toString().equals("1")) {
            Intent nextActivity = new Intent(this, AvisoNoAutorizado.class);
            startActivity(nextActivity);
        }

        if(txtUsername.getEditText().getText().toString().equals("Otro2") && txtPassword.getEditText().getText().toString().equals("1")) {
            Intent nextActivity = new Intent(this, UsuarioPendienteAprobacion.class);
            startActivity(nextActivity);
        }

        if(txtUsername.getEditText().getText().toString().compareTo("Otro") != 0 && txtUsername.getEditText().getText().toString().compareTo("Admin") != 0 && txtUsername.getEditText().getText().toString().compareTo("Otro2") != 0) {
            Vibrator vibrator = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(1000);

            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setMessage("User o password incorrect");
            dialog.setTitle("Error!");
            dialog.setPositiveButton("Ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
            dialog.setCancelable(true);
            dialog.create().show();
        }

    }

}