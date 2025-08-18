package com.example.aplicacionseminariointegrador;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.aplicacionseminariointegrador.auxiliarclases.LanguageSelected;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.HashMap;
import java.util.Map;

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
            textInputLayoutFirstName.setHint("Enter your names");
            textInputLayoutLastName.setHint("Enter your lastnames");
            txtlblDocumentType.setText("Document type:");
            textInputLayoutDocumentNumber.setHint("Enter your document number");

            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.document_type, android.R.layout.simple_spinner_dropdown_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spnTypeDocument.setAdapter(adapter);
        } else {
            btnCancelCreateAccount.setText("VOLVER");
            btnRegisterUser.setText("SIGUIENTE");
            txtLblNamesSurnames.setText("Nombres:");
            txtLblCreateAccount.setText("CREAR CUENTA");
            textInputLayoutFirstName.setHint("Ingrese sus nombres");
            textInputLayoutLastName.setHint("Ingrese sus apellidos");
            txtlblDocumentType.setText("Tipo de documento:");
            textInputLayoutDocumentNumber.setHint("Ingrese su número de documento");

            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.tipo_documento, android.R.layout.simple_spinner_dropdown_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spnTypeDocument.setAdapter(adapter);
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
        Intent nextActivity = new Intent(this, SelectRegisterUser.class);
        startActivity(nextActivity);
    }

    private void nextRegisterStep(View v) {
        Intent nextActivity = new Intent(this, RegisterAccountNext.class);
        startActivity(nextActivity);
    }

}