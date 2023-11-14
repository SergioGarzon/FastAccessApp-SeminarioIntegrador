package com.example.aplicacionseminariointegrador;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.pm.ActivityInfo;
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
            btnAceptarQR.setText("OK");
            btnCancelQRGenerator.setText("CANCEL");
        } else {
            btnGenerateQR.setText("GENERAR QR");
            txtViewQRGenerateCode.setText("GENERAR QR");
            btnAceptarQR.setText("ACEPTAR");
            btnCancelQRGenerator.setText("CANCELAR");
        }


        btnGenerateQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    BarcodeEncoder barEndoded = new BarcodeEncoder();
                    Bitmap bitmap = barEndoded.encodeBitmap("DATO", BarcodeFormat.QR_CODE, 750, 750);
                    imgViewQRCode.setImageBitmap(bitmap);
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        });


    }
}