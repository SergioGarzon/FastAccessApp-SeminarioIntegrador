package com.example.aplicacionseminariointegrador;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
public class StartActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Thread thread = new Thread() {

            @Override
            public void run() {
                try {
                    sleep(2000);
                    Intent newActivity = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(newActivity);
                }
                catch(InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        thread.start();
    }
}