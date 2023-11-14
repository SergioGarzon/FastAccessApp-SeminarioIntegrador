package com.example.aplicacionseminariointegrador;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aplicacionseminariointegrador.auxiliarclases.LanguageSelected;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class LectorQR extends AppCompatActivity {

    Button btnEnabledScan, btnCancelScan;
    EditText editTextResultScanQR;

    TextView txtTitleCodeQRScan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lector_qr);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        btnEnabledScan = findViewById(R.id.btnEnabledScan);
        btnCancelScan = findViewById(R.id.btnCancelScan);
        editTextResultScanQR = findViewById(R.id.editTextResultScanQR);
        txtTitleCodeQRScan = findViewById(R.id.txtTitleCodeQRScan);

        editTextResultScanQR.setEnabled(false);

        if(LanguageSelected.languageSelected == 0) {
            editTextResultScanQR.setText("Scanned code");
            btnEnabledScan.setText("SCAN QR");
            btnCancelScan.setText("CANCEL");
            txtTitleCodeQRScan.setText("QR SCAN");
        } else {
            editTextResultScanQR.setText("Código Escaneado");
            btnEnabledScan.setText("ESCANEAR QR");
            btnCancelScan.setText("CANCELAR");
            txtTitleCodeQRScan.setText("ESCANEO DE QR");
        }
        
        btnEnabledScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator integrator = new IntentIntegrator(LectorQR.this);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE);
                integrator.setPrompt("Lector QR visitante");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(true);
                integrator.setBarcodeImageEnabled(true);
                integrator.initiateScan();

            }
        });

        btnCancelScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backMenuApplicationApp(v);
            }
        });
    }

    private void backMenuApplicationApp(View v) {
        Intent nextActivity = new Intent(this, MenuAplication.class);
        startActivity(nextActivity);
    }

    protected void onActivityResult(int requestCode, int initResultCode, Intent data) {

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, initResultCode, data);

        if(result != null) {
            if(result.getContents() == null) {
                Toast.makeText(this, "Lectura cancelada", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, result.getContents(), Toast.LENGTH_SHORT).show();
                editTextResultScanQR.setText(result.getContents());
            }
        } else {
            super.onActivityResult(requestCode, initResultCode, data);
        }
    }



}