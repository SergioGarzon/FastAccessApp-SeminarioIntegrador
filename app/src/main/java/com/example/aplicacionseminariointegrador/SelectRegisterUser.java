package com.example.aplicacionseminariointegrador;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.aplicacionseminariointegrador.auxiliarclases.DataCharged;
import com.example.aplicacionseminariointegrador.auxiliarclases.LanguageSelected;

public class SelectRegisterUser extends AppCompatActivity {

    Button btnBackSelectOptionStart, btnResetAccount, btnCreateNewAccount;
    TextView lblSelectOption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_select_register_user);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnBackSelectOptionStart = findViewById(R.id.btnBackSelectOptionStart);
        btnResetAccount = findViewById(R.id.btnResetAccount);
        btnCreateNewAccount = findViewById(R.id.btnCreateNewAccount);
        lblSelectOption = findViewById(R.id.lblSelectOption);

        if(LanguageSelected.languageSelected == 0) {
            btnBackSelectOptionStart.setText("BACK");
            lblSelectOption.setText("Select an option");
            btnResetAccount.setText("Account recovery");
            btnCreateNewAccount.setText("Create new account");
        }
        else {
            btnBackSelectOptionStart.setText("VOLVER");
            lblSelectOption.setText("Seleccione una opción");
            btnResetAccount.setText("Recuperar cuenta");
            btnCreateNewAccount.setText("Crear nueva cuenta");
        }

    }

    public void changeBackStartActivity(View view) {
        Intent nextActivity = new Intent(this, StartApplication.class);
        startActivity(nextActivity);
    }

    public void changeRegisterAccountActivity(View view) {

        DataCharged.names = "";
        DataCharged.lastnames = "";
        DataCharged.nidSelected = 0;
        DataCharged.nidNumber = 0;
        DataCharged.phoneNumber = "";
        DataCharged.emailAddress = "";

        Intent nextActivity = new Intent(this, RegisterAccount.class);
        startActivity(nextActivity);
    }

}