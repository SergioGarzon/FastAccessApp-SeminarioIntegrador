package com.example.aplicacionseminariointegrador;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aplicacionseminariointegrador.auxiliarclases.LanguageSelected;

public class AvisoUsuarioDadoDeBaja extends AppCompatActivity {

    Button btnCancelBackLogin;
    TextView LblUserCancellation, LblMessageAlert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aviso_usuario_dado_de_baja);

        btnCancelBackLogin = (Button) findViewById(R.id.btnCancelBackLogin);
        LblUserCancellation = (TextView) findViewById(R.id.txtLblUserCancellation);
        LblMessageAlert = (TextView) findViewById(R.id.txtLblMessageAlert);

        if(LanguageSelected.languageSelected == 0) {
            LblMessageAlert.setText("A problem occurred!");
            LblUserCancellation.setText("User requested the cancellation of his account");
            btnCancelBackLogin.setText("BACK LOGIN");
        } else {
            LblMessageAlert.setText("¡Ocurrio un problema!");
            LblUserCancellation.setText("El usuario solicito la cancelación de su cuenta");
            btnCancelBackLogin.setText("VOLVER AL ACCESO");
        }

    }

    // Metodo para el boton Ingresar
    public void changeActivityMainBackLogin(View view) {
        Intent nextActivity = new Intent(this, LoginActivity.class);
        startActivity(nextActivity);
    }
}