package com.example.aplicacionseminariointegrador;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.aplicacionseminariointegrador.auxiliarclases.LanguageSelected;


public class VisitorsRegister extends AppCompatActivity {

    Spinner spinnerDocumento;
    Button btnNextVisitorGenerateQR, btnCancelVisitorGenerate;
    TextView txtTitleRegisterVisitor, txtNameVisitorRegister, txtLastNameVisitorRegister, txtDocumentVisitorRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visitors_register);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        spinnerDocumento = findViewById(R.id.spinnerDocumento);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.tipo_documento, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDocumento.setAdapter(adapter);

        btnNextVisitorGenerateQR = findViewById(R.id.btnNextVisitorGenerateQR);
        btnCancelVisitorGenerate = findViewById(R.id.btnCancelVisitorGenerate);
        txtTitleRegisterVisitor = findViewById(R.id.txtTitleRegisterVisitor);
        txtNameVisitorRegister = findViewById(R.id.txtNameVisitorRegister);
        txtLastNameVisitorRegister = findViewById(R.id.txtLastNameVisitorRegister);
        txtDocumentVisitorRegister = findViewById(R.id.txtDocumentVisitorRegister);

        if(LanguageSelected.languageSelected == 0) {
            btnNextVisitorGenerateQR.setText("OK");
            btnCancelVisitorGenerate.setText("CANCEL");
            txtTitleRegisterVisitor.setText("VISITOR REGISTER");
            txtNameVisitorRegister.setText("Visitor's name:");
            txtLastNameVisitorRegister.setText("Visitor's lastname:");
            txtDocumentVisitorRegister.setText("Visitor's document:");
        } else {
            btnCancelVisitorGenerate.setText("CANCELAR");
            btnNextVisitorGenerateQR.setText("SIGUIENTE");
            txtTitleRegisterVisitor.setText("REGISTRAR VISITANTE");
            txtNameVisitorRegister.setText("Nombre del visitante:");
            txtLastNameVisitorRegister.setText("Apellido del visitante:");
            txtDocumentVisitorRegister.setText("Documento del visitante:");
        }

    }


}