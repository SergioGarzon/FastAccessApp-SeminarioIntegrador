package com.example.aplicacionseminariointegrador;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Vibrator;
import android.view.View;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // Metodo para el boton Ingresar
    public void changeActivity(View view) {
        Intent nextActivity = new Intent(this, SecondMain.class);
        startActivity(nextActivity);
    }

    // Metodo para el boton Ingresar a los creditos.
    public void changeActivityCredits(View view) {

        Intent nextActivity = new Intent(this, CreditsAplication.class);
        startActivity(nextActivity);
    }

    // Metodo para el boton Ingresar al menu principal.
    public void changeActivityMenu(View view) {
        EditText userName = (EditText) findViewById(R.id.txtUserName);
        EditText password = (EditText) findViewById(R.id.txtPassword);

        if(userName.getText().toString().equals("Adm") && password.getText().toString().equals("1")) {
            Intent nextActivity = new Intent(this, MenuAplication.class);
            startActivity(nextActivity);
        } else {

            Vibrator vibrator = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(1000);

            userName.setText("Enter your user name");
            password.setText("");

            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Error");
            builder.setMessage("User or password incorrect!")
                    .setPositiveButton("", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    }).show();
        }
    }



}