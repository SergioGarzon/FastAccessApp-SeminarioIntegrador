package com.example.aplicacionseminariointegrador;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.aplicacionseminariointegrador.auxiliarclases.LanguageSelected;
import com.google.android.material.textfield.TextInputLayout;

public class MailOrWhatsapp extends AppCompatActivity {

    Button btnFinish;
    TextView txtSendQR;
    ImageButton imgBtnEmail, imgBtnWhatsapp;
    TextInputLayout txtInputLayoutPhoneNumberWhatsapp, txtInputLayoutEmailAddress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail_or_whatsapp);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        btnFinish = findViewById(R.id.btnFinish);
        txtSendQR = findViewById(R.id.txtSendQR);

        imgBtnEmail = findViewById(R.id.imgBtnEmail);
        imgBtnWhatsapp = findViewById(R.id.imgBtnWhatsapp);

        txtInputLayoutPhoneNumberWhatsapp = findViewById(R.id.txtInputLayoutPhoneNumberWhatsapp);
        txtInputLayoutEmailAddress = findViewById(R.id.txtInputLayoutEmailAddress);

        if(LanguageSelected.languageSelected == 0) {
            btnFinish.setText("FINISH");
            txtSendQR.setText("SENDING QR CODE");
        } else {
            btnFinish.setText("FINALIZAR");
            txtSendQR.setText("ENVIO DE CODIGO QR");
        }

        imgBtnWhatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessageWhatsapp();
            }
        });

        imgBtnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessageEmail(v);
            }
        });

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backActivityMenuStart(v);
            }
        });
    }

    private void sendMessageWhatsapp() {
        String number = txtInputLayoutPhoneNumberWhatsapp.getEditText().getText().toString();

        if(number.equals("")) {
            Vibrator vibrator = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(1000);

            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setMessage("Phone number not entry");
            dialog.setTitle("Error!");
            dialog.setPositiveButton("Ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
            dialog.setCancelable(true);
            dialog.create().show();
        } else {
            Uri uri = Uri.parse("https://api.whatsapp.com/send?phone=" + number);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }
    }

    private void sendMessageEmail(View v) {

        String emailAddress = txtInputLayoutEmailAddress.getEditText().getText().toString();

        if(emailAddress.equals("")) {
            Vibrator vibrator = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(1000);

            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setMessage("Email address not entry");
            dialog.setTitle("Error!");
            dialog.setPositiveButton("Ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
            dialog.setCancelable(true);
            dialog.create().show();
        } else {
            Intent intent = getPackageManager().getLaunchIntentForPackage("com.google.android.gm");

            if(intent != null) {
                startActivity(intent);
            }
        }
    }

    private void backActivityMenuStart(View v) {

        switch (LanguageSelected.sesion) {
            case 0:
                Intent nextActivity = new Intent(this, UsuarioPendienteAprobacion.class);
                startActivity(nextActivity);
                break;
            case 1:
                Intent nextActivity2 = new Intent(this, AvisoNoAutorizado.class);
                startActivity(nextActivity2);
                break;
            case 2:
                Intent nextActivity3 = new Intent(this, MenuApplicationResidente.class);
                nextActivity3.putExtra("Usuario", "nuevamente");
                startActivity(nextActivity3);
                break;
            case 3:
                Intent nextActivity4 = new Intent(this, MenuAplication.class);
                nextActivity4.putExtra("Usuario", "nuevamente");
                startActivity(nextActivity4);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        //Toast.makeText(StartApplication.this, "Action not allowed!", Toast.LENGTH_SHORT).show();
        return;
    }

}