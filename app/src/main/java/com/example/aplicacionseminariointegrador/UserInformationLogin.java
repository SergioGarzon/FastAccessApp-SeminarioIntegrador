package com.example.aplicacionseminariointegrador;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aplicacionseminariointegrador.auxiliarclases.LanguageSelected;

public class UserInformationLogin extends AppCompatActivity {
    ImageView imgActivityInfo;
    Button btnBackLoginWaitingAutorization;
    TextView txtUserWaitingAutorization, txtInfoUser;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_information_login);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        btnBackLoginWaitingAutorization = findViewById(R.id.btnBackLoginWaitAutorization);
        txtUserWaitingAutorization = findViewById(R.id.txtUserWaitAutorization);
        imgActivityInfo = findViewById(R.id.imgInformation);
        txtInfoUser = findViewById(R.id.txtInfoUser);

        Intent intent = getIntent();
        String valor = intent.getStringExtra("valor");
        String infoUserEnglish = "";
        String infoUserSpanish = "";
        String messageUserEnglish = "";
        String messageUserSpanish = "";

        switch(Integer.parseInt(valor)) {
            case 0:
                imgActivityInfo.setImageResource(R.drawable.userwaitingconfirmation);
                infoUserEnglish = "Information";
                infoUserSpanish = "Información";
                messageUserEnglish = "User waiting autorization!";
                messageUserSpanish = "¡Usuario a la espera de la autorización!";
                txtInfoUser.setTextSize(45f);
                txtInfoUser.setTextColor(Color.rgb(14, 164, 175));
                break;
            case 1:
                imgActivityInfo.setImageResource(R.drawable.usernoautorized);
                infoUserEnglish = "Error";
                infoUserSpanish = "Error";
                messageUserEnglish = "User no autorized";
                messageUserSpanish = "Usuario no autorizado";
                txtInfoUser.setTextSize(45f);
                txtInfoUser.setTextColor(Color.RED);
                break;
            case 2:
                imgActivityInfo.setImageResource(R.drawable.alertsymbol);
                infoUserEnglish = "A problem has ocurred!";
                infoUserSpanish = "¡Un problema ha ocurrido!";
                messageUserEnglish = "User requested the cancellation of his account";
                messageUserSpanish = "El usuario solicito la cancelación de su cuenta";
                txtInfoUser.setTextSize(30f);
                txtInfoUser.setTextColor(Color.YELLOW);
                break;
        }

        if(LanguageSelected.languageSelected == 0) {
            btnBackLoginWaitingAutorization.setText("BACK LOGIN");
            txtInfoUser.setText(infoUserEnglish);
            txtUserWaitingAutorization.setText(messageUserEnglish);
        } else {
            btnBackLoginWaitingAutorization.setText("VOLVER AL ACCESO");
            txtInfoUser.setText(infoUserSpanish);
            txtUserWaitingAutorization.setText(messageUserSpanish);
        }

        btnBackLoginWaitingAutorization.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeActivityBackLoginInvalidAutorization(v);
            }
        });
    }

    private void changeActivityBackLoginInvalidAutorization(View v) {
        Intent nextActivity = new Intent(this, LoginActivity.class);
        startActivity(nextActivity);
    }

    @Override
    public void onBackPressed() {
        //Toast.makeText(StartApplication.this, "Action not allowed!", Toast.LENGTH_SHORT).show();
        return;
    }
}