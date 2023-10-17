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
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {

    Button btnLogin, btnCancelLogin;
    TextInputLayout txtUsername, txtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = (Button) findViewById(R.id.btnLogin);

        btnCancelLogin = (Button) findViewById(R.id.btnCancelLogin);

        txtUsername = (TextInputLayout) findViewById(R.id.txtInputUsername);

        txtPassword = (TextInputLayout) findViewById(R.id.txtInputPassword);

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

        if(txtUsername.getEditText().getText().toString().compareTo("Otro") != 0 && txtUsername.getEditText().getText().toString().compareTo("Admin") != 0) {
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