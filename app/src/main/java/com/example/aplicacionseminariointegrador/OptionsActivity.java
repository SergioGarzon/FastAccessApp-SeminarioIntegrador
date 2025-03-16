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
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import com.example.aplicacionseminariointegrador.auxiliarclases.LanguageSelected;

public class OptionsActivity extends AppCompatActivity {

    CheckBox chkEnglish, chkSpanish;
    Button btnAcceptOption, btnCancelOption;
    TextView lblOptions, txtLblLanguage, txtLblSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        chkEnglish = findViewById(R.id.chkEnglishLanguage);
        chkSpanish = findViewById(R.id.chkSpanishLanguage);

        btnAcceptOption = findViewById(R.id.btnAcceptOptions);
        btnCancelOption = findViewById(R.id.btnCancelOptions);

        lblOptions = findViewById(R.id.txtLblOptions);
        txtLblLanguage =  findViewById(R.id.txtLblLanguage);
        txtLblSound = findViewById(R.id.txtLblSound);

        if(LanguageSelected.languageSelected == 0) {
            chkEnglish.setText("English");
            chkSpanish.setText("Spanish");
            btnAcceptOption.setText("OK");
            btnCancelOption.setText("CANCEL");
            lblOptions.setText("OPTIONS");
            txtLblLanguage.setText("Language:");
            txtLblSound.setText("Sound:");

            chkEnglish.setChecked(true);
            chkSpanish.setChecked(false);
        } else {

            chkEnglish.setText("Ingles");
            chkSpanish.setText("Español");
            btnAcceptOption.setText("ACEPTAR");
            btnCancelOption.setText("CANCELAR");
            lblOptions.setText("OPCIONES");
            txtLblLanguage.setText("Idioma:");
            txtLblSound.setText("Sonido:");

            chkEnglish.setChecked(false);
            chkSpanish.setChecked(true);
        }

        chkEnglish.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                chkSpanish.setChecked(false);
            }
        });

        chkSpanish.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                chkEnglish.setChecked(false);
            }
        });


        btnAcceptOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!chkSpanish.isChecked() && !chkEnglish.isChecked()) {

                    Vibrator vibrator = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                    vibrator.vibrate(1000);

                    AlertDialog.Builder dialog = new AlertDialog.Builder(OptionsActivity.this);

                    if(LanguageSelected.languageSelected == 0) {
                        dialog.setTitle("Warning!");
                        dialog.setMessage("Language not selected!");
                    } else {
                        dialog.setTitle("Alerta!");
                        dialog.setMessage("¡No has seleccionado el idioma!");
                    }

                    dialog.setIcon(android.R.drawable.ic_dialog_alert);
                    dialog.setPositiveButton("Ok",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                    dialog.setCancelable(true);
                    dialog.create().show();
                }
                else {
                    selectOption();
                }
            }
        });

        btnCancelOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeBackActivity();
            }
        });
    }

    public void selectOption() {

        if(chkSpanish.isChecked()) {
            LanguageSelected.languageSelected = 1;
        }

        if(chkEnglish.isChecked()) {
            LanguageSelected.languageSelected = 0;
        }

        changeBackActivity();
    }

    private void changeBackActivity() {
        Intent nextActivity = new Intent(this, StartApplication.class);
        startActivity(nextActivity);
    }
}