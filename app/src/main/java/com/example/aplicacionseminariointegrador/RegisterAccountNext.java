package com.example.aplicacionseminariointegrador;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.aplicacionseminariointegrador.auxiliarclases.LanguageSelected;

public class RegisterAccountNext extends AppCompatActivity {

    Button btnNextRegisterSuccessfull, btnBackRegisterStep1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register_account_next);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnNextRegisterSuccessfull = findViewById(R.id.btnNextRegisterSuccessfull);
        btnBackRegisterStep1 = findViewById(R.id.btnBackRegisterStep1);

        if(LanguageSelected.languageSelected == 0) {
            btnNextRegisterSuccessfull.setText("NEXT");
            btnBackRegisterStep1.setText("BACK");
        } else {
            btnNextRegisterSuccessfull.setText("SIGUIENTE");
            btnBackRegisterStep1.setText("VOLVER");
        }

        btnNextRegisterSuccessfull.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backNext_Activity(v);
            }
        });

        btnBackRegisterStep1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backLeft_Activity(v);
            }
        });
    }

    private void backNext_Activity(View v) {
        Intent nextActivity = new Intent(this, RegisterSucessful.class);
        startActivity(nextActivity);
    }

    private void backLeft_Activity(View v) {
        Intent nextActivity = new Intent(this, RegisterAccount.class);
        startActivity(nextActivity);
    }
}