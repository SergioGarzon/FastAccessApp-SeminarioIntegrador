package com.example.aplicacionseminariointegrador.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.aplicacionseminariointegrador.model.DataCharged;
import com.example.aplicacionseminariointegrador.model.LanguageSelected;
import com.example.aplicacionseminariointegrador.R;
import com.google.android.material.textfield.TextInputLayout;

public class RegisterAccount extends AppCompatActivity {

    Button btnCancelCreateAccount, btnRegisterUser;
    Spinner spnTypeDocument;

    TextView txtLblCreateAccount, txtLblNamesSurnames, txtlblDocumentType;

    TextInputLayout textInputLayoutFirstName, textInputLayoutLastName, textInputLayoutDocumentNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_account);

        btnCancelCreateAccount = findViewById(R.id.btnCancelCreateAccount);
        btnRegisterUser = findViewById(R.id.btnRegisterUser);
        spnTypeDocument = findViewById(R.id.spnTypeDocument);
        txtLblCreateAccount =  findViewById(R.id.txtLblCreateAccount);
        txtLblNamesSurnames =  findViewById(R.id.txtLblNamesSurnames);
        textInputLayoutFirstName = findViewById(R.id.textInputLayoutFirstName);
        textInputLayoutLastName = findViewById(R.id.textInputLayoutLastName);
        txtlblDocumentType = findViewById(R.id.txtlblDocumentType);
        textInputLayoutDocumentNumber = findViewById(R.id.textInputLayoutDocumentNumber);

        if(LanguageSelected.languageSelected == 0) {
            btnCancelCreateAccount.setText("BACK");
            btnRegisterUser.setText("NEXT");
            txtLblNamesSurnames.setText("Names:");
            txtLblCreateAccount.setText("CREATE ACCOUNT");

            txtlblDocumentType.setText("Document type:");

            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.document_type, android.R.layout.simple_spinner_dropdown_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spnTypeDocument.setAdapter(adapter);

            if (DataCharged.names.equals("") && DataCharged.lastnames.equals("") && DataCharged.nidNumber == 0) {
                textInputLayoutFirstName.setHint("Enter your names");
                textInputLayoutLastName.setHint("Enter your lastnames");
                textInputLayoutDocumentNumber.setHint("Enter your document number");
            } else {
                textInputLayoutFirstName.getEditText().setText(DataCharged.names + "");
                textInputLayoutLastName.getEditText().setText(DataCharged.lastnames + "");
                textInputLayoutDocumentNumber.getEditText().setText(DataCharged.nidNumber + "");
                spnTypeDocument.setSelection(DataCharged.nidSelected);
            }
        } else {
            btnCancelCreateAccount.setText("VOLVER");
            btnRegisterUser.setText("SIGUIENTE");
            txtLblNamesSurnames.setText("Nombres:");
            txtLblCreateAccount.setText("CREAR CUENTA");
            textInputLayoutDocumentNumber.setHint("Ingrese su número de documento");

            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.tipo_documento, android.R.layout.simple_spinner_dropdown_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spnTypeDocument.setAdapter(adapter);

            if (DataCharged.names.equals("") && DataCharged.lastnames.equals("") && DataCharged.nidSelected == 0 && DataCharged.nidNumber == 0) {
                textInputLayoutFirstName.setHint("Ingrese sus nombres");
                textInputLayoutLastName.setHint("Ingrese sus apellidos");
                txtlblDocumentType.setText("Tipo de documento:");
            } else {
                textInputLayoutFirstName.getEditText().setText(DataCharged.names + "");
                textInputLayoutLastName.getEditText().setText(DataCharged.lastnames + "");
                textInputLayoutDocumentNumber.getEditText().setText(DataCharged.nidNumber + "");
                spnTypeDocument.setSelection(DataCharged.nidSelected);
            }

        }

        btnCancelCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backStart_Activity(v);
            }
        });

        btnRegisterUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextRegisterStep(v);
            }
        });
    }

    private void backStart_Activity(View v) {
        DataCharged.names = "";
        DataCharged.lastnames = "";
        DataCharged.nidSelected = 0;
        DataCharged.nidNumber = 0;

        Intent nextActivity = new Intent(this, SelectRegisterUser.class);
        startActivity(nextActivity);
    }

    private void nextRegisterStep(View v) {

        if(textInputLayoutFirstName.getEditText().getText().toString().compareTo("") != 0)
            DataCharged.names = textInputLayoutFirstName.getEditText().getText().toString();

        if(textInputLayoutLastName.getEditText().getText().toString().compareTo("") != 0)
            DataCharged.lastnames = textInputLayoutLastName.getEditText().getText().toString();

        if(spnTypeDocument.getSelectedItemPosition() != 0)
            DataCharged.nidSelected = spnTypeDocument.getSelectedItemPosition();

        if(textInputLayoutDocumentNumber.getEditText().getText().toString().compareTo("") != 0)
            DataCharged.nidNumber = Integer.parseInt(textInputLayoutDocumentNumber.getEditText().getText().toString());

        Intent nextActivity = new Intent(this, RegisterAccountNext.class);
        startActivity(nextActivity);
    }

}