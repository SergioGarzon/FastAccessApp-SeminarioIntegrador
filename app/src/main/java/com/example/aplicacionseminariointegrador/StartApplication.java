package com.example.aplicacionseminariointegrador;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

public class StartApplication extends AppCompatActivity {

    TextView txtFastAccessApp;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_application);

        txtFastAccessApp = findViewById(R.id.lblFastAccessAppPrincipalTitle);

        animation();
    }

    public void changeActivityApplication(View view) {
        Intent nextActivity = new Intent(this, MainActivity.class);
        startActivity(nextActivity);
    }


    public void changeSecondMain(View view) {
        Intent nextActivity2 = new Intent(this, SecondMain.class);
        startActivity(nextActivity2);
    }

    @Override
    public void onBackPressed() {
        //Toast.makeText(StartApplication.this, "Action not allowed!", Toast.LENGTH_SHORT).show();
        return;
    }

    public void animation() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.animationvisible);
        animation.setRepeatCount(Animation.INFINITE);
        txtFastAccessApp.setAnimation(animation);
    }
}