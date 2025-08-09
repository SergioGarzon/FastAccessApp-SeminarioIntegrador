package com.example.aplicacionseminariointegrador;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.example.aplicacionseminariointegrador.auxiliarclases.LanguageSelected;

public class StartApplication extends AppCompatActivity {

    TextView txtFastAccessApp;
    Button btnIdLogin2, btnIdRegister;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_application);

        txtFastAccessApp = findViewById(R.id.lblFastAccessAppPrincipalTitle);

        btnIdLogin2 = (Button) findViewById(R.id.btnIdLogin2);
        btnIdRegister = (Button) findViewById(R.id.btnIdRegister);

        if(LanguageSelected.languageSelected == 0) {
            btnIdLogin2.setText("LOGIN");
            btnIdRegister.setText("REGISTER");
        }
        else {
            btnIdLogin2.setText("ACCESO");
            btnIdRegister.setText("REGISTRO");
        }


        animation();
    }

    public void changeActivityLogin(View view) {
        Intent nextActivity = new Intent(this, LoginActivity.class);
        startActivity(nextActivity);
    }

    public void changeActivityRegisterAcoount(View view) {
        Intent nextActivity = new Intent(this, SelectRegisterUser.class);
        startActivity(nextActivity);
    }

    public void changeActivityCredits(View view) {
        Intent nextActivity = new Intent(this, CreditsAplication.class);
        startActivity(nextActivity);
    }

    public void changeActivityOptions(View view) {
        Intent nextActivity = new Intent(this, OptionsActivity.class);
        startActivity(nextActivity);
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