package com.example.aplicacionseminariointegrador;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RegisterAccount extends AppCompatActivity {

    Button btnCancelRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_account);

        btnCancelRegister = (Button) findViewById(R.id.btnCancelCreateAccount);
        btnCancelRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backStart_Activity(v);
            }
        });
    }

    private void backStart_Activity(View v) {
        Intent nextActivity = new Intent(this, StartApplication.class);
        startActivity(nextActivity);
    }
}