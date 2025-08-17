package com.example.aplicacionseminariointegrador;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.aplicacionseminariointegrador.auxiliarclases.LanguageSelected;

public class ResetPassword extends AppCompatActivity {

    Button btnCancelPasswordChange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_reset_password);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnCancelPasswordChange = findViewById(R.id.btnCancelPasswordChange);

        if(LanguageSelected.languageSelected == 0) {
            btnCancelPasswordChange.setText("CANCEL");
        } else {
            btnCancelPasswordChange.setText("CANCELAR");
        }

        btnCancelPasswordChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backLogin_Activity(v);
            }
        });
    }

    private void backLogin_Activity(View v) {
        Intent nextActivity = new Intent(this, LoginActivity.class);
        startActivity(nextActivity);
    }
}