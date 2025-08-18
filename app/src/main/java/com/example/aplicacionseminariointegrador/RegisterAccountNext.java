package com.example.aplicacionseminariointegrador;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.aplicacionseminariointegrador.auxiliarclases.DataCharged;
import com.example.aplicacionseminariointegrador.auxiliarclases.LanguageSelected;
import com.google.android.material.textfield.TextInputLayout;

public class RegisterAccountNext extends AppCompatActivity {

    Button btnNextRegisterSuccessfull, btnBackRegisterStep1;
    TextView txtViewPhoneNumber, txtViewEmailAddress;
    TextInputLayout txtILPhoneNumber, txtILEmailAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register_account_next);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnNextRegisterSuccessfull = findViewById(R.id.btnNextRegisterSuccessfull);
        btnBackRegisterStep1 = findViewById(R.id.btnBackRegisterStep1);

        txtViewPhoneNumber = findViewById(R.id.txtViewPhoneNumber);
        txtViewEmailAddress = findViewById(R.id.txtViewEmailAddress);

        txtILPhoneNumber = findViewById(R.id.txtILPhoneNumber);
        txtILEmailAddress = findViewById(R.id.txtILEmailAddress);

        if(LanguageSelected.languageSelected == 0) {
            btnNextRegisterSuccessfull.setText("NEXT");
            btnBackRegisterStep1.setText("BACK");
            txtViewPhoneNumber.setText("Phone number:");
            txtViewEmailAddress.setText("Email address:");
            txtILPhoneNumber.setHint("Enter your phone number");
            txtILEmailAddress.setHint("Enter your email address");
        } else {
            btnNextRegisterSuccessfull.setText("SIGUIENTE");
            btnBackRegisterStep1.setText("VOLVER");
            txtViewPhoneNumber.setText("Número de telefono:");
            txtViewEmailAddress.setText("Dirección de email:");
            txtILPhoneNumber.setHint("Ingrese su número de telefono");
            txtILEmailAddress.setHint("Ingrese su dirección de email");
        }

        if(DataCharged.phoneNumber.compareTo("") != 0 && DataCharged.emailAddress.compareTo("")  != 0) {
            txtILPhoneNumber.getEditText().setText(DataCharged.phoneNumber + "");
            txtILEmailAddress.getEditText().setText(DataCharged.emailAddress + "");
        } else {
            txtILPhoneNumber.getEditText().setText("");
            txtILEmailAddress.getEditText().setText("");
        }

        btnNextRegisterSuccessfull.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backNext_Activity(v);
            }
        });

        btnBackRegisterStep1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backLeft_Activity(v);
            }
        });
    }

    private void backNext_Activity(View v) {
        Intent nextActivity = new Intent(this, RegisterSucessful.class);
        startActivity(nextActivity);
    }

    private void backLeft_Activity(View v) {
        DataCharged.phoneNumber = txtILPhoneNumber.getEditText().getText().toString();
        DataCharged.emailAddress = txtILEmailAddress.getEditText().getText().toString();

        Intent nextActivity = new Intent(this, RegisterAccount.class);
        startActivity(nextActivity);
    }
}