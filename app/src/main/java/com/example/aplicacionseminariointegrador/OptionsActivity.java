package com.example.aplicacionseminariointegrador;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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

        chkEnglish = (CheckBox) findViewById(R.id.chkEnglishLanguage);
        chkSpanish = (CheckBox) findViewById(R.id.chkSpanishLanguage);

        btnAcceptOption = (Button) findViewById(R.id.btnAcceptOptions);
        btnCancelOption = (Button) findViewById(R.id.btnCancelOptions);

        lblOptions = (TextView) findViewById(R.id.txtLblOptions);
        txtLblLanguage = (TextView) findViewById(R.id.txtLblLanguage);
        txtLblSound = (TextView) findViewById(R.id.txtLblSound);

        if(LanguageSelected.languageSelected == 0) {
            chkEnglish.setText("English");
            chkSpanish.setText("Spanish");
            btnAcceptOption.setText("OK");
            btnCancelOption.setText("CANCEL");
            lblOptions.setText("OPTIONS");
            txtLblLanguage.setText("Language:");
            txtLblSound.setText("Sound:");
        } else {
            chkEnglish.setText("Ingles");
            chkSpanish.setText("Español");
            btnAcceptOption.setText("ACEPTAR");
            btnCancelOption.setText("CANCELAR");
            lblOptions.setText("OPCIONES");
            txtLblLanguage.setText("Idioma:");
            txtLblSound.setText("Sonido:");
        }

        btnAcceptOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectOption();
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