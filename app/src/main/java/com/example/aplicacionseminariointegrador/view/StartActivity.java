package com.example.aplicacionseminariointegrador.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;

import com.example.aplicacionseminariointegrador.model.LanguageSelected;
import com.example.aplicacionseminariointegrador.R;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        LanguageSelected.languageSelected = 1;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent nextActivity = new Intent(getApplicationContext(), StartApplication.class);
                startActivity(nextActivity);
            }
        }, 3000);


    }
}