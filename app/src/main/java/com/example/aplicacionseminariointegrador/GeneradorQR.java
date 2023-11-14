package com.example.aplicacionseminariointegrador;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.content.pm.ActivityInfo;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.zxing.BarcodeFormat;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import android.graphics.Bitmap;

import com.example.aplicacionseminariointegrador.auxiliarclases.LanguageSelected;

public class GeneradorQR extends AppCompatActivity {

    Button btnGenerateQR, btnAceptarQR, btnCancelQRGenerator;
    ImageView imgViewQRCode;
    TextView txtViewQRGenerateCode;

    private static int valor = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generador_qr);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        btnGenerateQR = findViewById(R.id.btnGenerateQR);
        imgViewQRCode = findViewById(R.id.imgViewQRCode);
        txtViewQRGenerateCode = findViewById(R.id.txtViewQRGenerateCode);
        btnAceptarQR = findViewById(R.id.btnAceptarQR);
        btnCancelQRGenerator = findViewById(R.id.btnCancelQRGenerator);

        if(LanguageSelected.languageSelected == 0) {
            btnGenerateQR.setText("GENERATE QR");
            txtViewQRGenerateCode.setText("GENERATE QR");
            btnAceptarQR.setText("NEXT");
            btnCancelQRGenerator.setText("CANCEL");
        } else {
            btnGenerateQR.setText("GENERAR QR");
            txtViewQRGenerateCode.setText("GENERAR QR");
            btnAceptarQR.setText("SIGUIENTE");
            btnCancelQRGenerator.setText("CANCELAR");
        }

        btnGenerateQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    BarcodeEncoder barEndoded = new BarcodeEncoder();
                    Bitmap bitmap = barEndoded.encodeBitmap("DATO", BarcodeFormat.QR_CODE, 750, 750);
                    imgViewQRCode.setImageBitmap(bitmap);
                    valor = 1;
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        });

        btnCancelQRGenerator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backRegisterVisitor(v);
            }
        });

        btnAceptarQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextActivityQRCode(v);
            }
        });

    }

    private void nextActivityQRCode(View v) {

        if(valor == 0) {
            Vibrator vibrator = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(1000);

            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setMessage("QR Code not generated");
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
            Intent nextActivity = new Intent(this, MailOrWhatsapp.class);
            startActivity(nextActivity);
        }
    }

    private void backRegisterVisitor(View v) {
        Intent nextActivity = new Intent(this, VisitorsRegister.class);
        startActivity(nextActivity);
    }
}