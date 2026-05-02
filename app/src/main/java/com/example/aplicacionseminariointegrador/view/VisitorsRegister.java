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
import android.widget.EditText;
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
import com.google.android.material.textfield.TextInputLayout;

import java.util.HashMap;
import java.util.Map;


public class VisitorsRegister extends AppCompatActivity {

    Spinner spinnerDocumento;
    Button btnNextVisitorGenerateQR, btnCancelVisitorGenerate;
    TextView txtTitleRegisterVisitor, txtNameVisitorRegister, txtLastNameVisitorRegister, txtDocumentVisitorRegister,
            txtDateEntry, txtLicencePlate;
    EditText editTextTime;

    TextInputLayout txtInputLayoutNameVisitor, txtInputLayoutLastNameVisitor, txtInputLayoutNroDocument, txtInputLayoutLicencePlate;

    RequestQueue requestQueue;

    private static final String urlVisitor = "https://fastaccessapp.000webhostapp.com/proyectobdejemplo/visiter.php?";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visitors_register);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        requestQueue = Volley.newRequestQueue(this);

        spinnerDocumento = findViewById(R.id.spinnerDocumento);

        btnNextVisitorGenerateQR = findViewById(R.id.btnNextVisitorGenerateQR);
        btnCancelVisitorGenerate = findViewById(R.id.btnCancelVisitorGenerate);
        txtTitleRegisterVisitor = findViewById(R.id.txtTitleRegisterVisitor);
        txtNameVisitorRegister = findViewById(R.id.txtNameVisitorRegister);
        txtLastNameVisitorRegister = findViewById(R.id.txtLastNameVisitorRegister);
        txtDocumentVisitorRegister = findViewById(R.id.txtDocumentVisitorRegister);

        txtDateEntry = findViewById(R.id.txtDateEntry);
        txtLicencePlate = findViewById(R.id.txtLicencePlate);
        editTextTime = findViewById(R.id.editTextTime);
        txtInputLayoutNameVisitor = findViewById(R.id.txtInputLayoutNameVisitor);
        txtInputLayoutLastNameVisitor = findViewById(R.id.txtInputLayoutLastNameVisitor);
        txtInputLayoutNroDocument = findViewById(R.id.txtInputLayoutNroDocument);
        txtInputLayoutLicencePlate = findViewById(R.id.txtInputLayoutLicencePlate);


        if(LanguageSelected.languageSelected == 0) {
            btnNextVisitorGenerateQR.setText("OK");
            btnCancelVisitorGenerate.setText("CANCEL");
            txtTitleRegisterVisitor.setText("VISITOR REGISTER");
            txtNameVisitorRegister.setText("Visitor's name:");
            txtLastNameVisitorRegister.setText("Visitor's lastname:");
            txtDocumentVisitorRegister.setText("Visitor's document:");
            txtDateEntry.setText("Date and time  of \\n entry:");
            txtLicencePlate.setText("License plate:");

            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.document_type, android.R.layout.simple_spinner_dropdown_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerDocumento.setAdapter(adapter);
        } else {
            btnCancelVisitorGenerate.setText("CANCELAR");
            btnNextVisitorGenerateQR.setText("SIGUIENTE");
            txtTitleRegisterVisitor.setText("REGISTRAR VISITANTE");
            txtNameVisitorRegister.setText("Nombre del visitante:");
            txtLastNameVisitorRegister.setText("Apellido del visitante:");
            txtDocumentVisitorRegister.setText("Documento del visitante:");
            txtDateEntry.setText("Fecha y hora de \n ingreso:");
            txtLicencePlate.setText("Patente:");

            ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.tipo_documento, android.R.layout.simple_spinner_dropdown_item);
            adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerDocumento.setAdapter(adapter2);
        }

        btnCancelVisitorGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backMenuApplicationActivity(v);
            }
        });

        btnNextVisitorGenerateQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createUserValidation();
            }
        });

    }

    private boolean getValidateDataEntry() {
        boolean flag = true;

        if(txtInputLayoutNameVisitor.getEditText().getText().toString().equals("") ||
                txtInputLayoutLastNameVisitor.getEditText().getText().toString().equals("") ||
                txtDocumentVisitorRegister.getText().equals("") ||
                editTextTime.getText().equals("") ||
                txtInputLayoutLicencePlate.getEditText().getText().toString().equals("")) {
            flag = false;
        }

        return flag;
    }

    private void createUserValidation() {
        if(getValidateDataEntry()) {
            registerVisitor(txtInputLayoutNameVisitor.getEditText().getText().toString(),
                            txtInputLayoutLastNameVisitor.getEditText().getText().toString(),
                            txtInputLayoutNroDocument.getEditText().getText().toString(),
                            txtInputLayoutLicencePlate.getEditText().getText().toString());
        } else {
            Vibrator vibrator = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(1000);

            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setMessage("Falta rellenar algunos campos");
            dialog.setTitle("Error!");
            dialog.setPositiveButton("Ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
            dialog.setCancelable(true);
            dialog.create().show();
        }
    }

    private void backMenuApplicationActivity(View v) {
        switch (LanguageSelected.sesion) {/*
            case 0:
                Intent nextActivity = new Intent(this, UserInformationLogin.class);
                startActivity(nextActivity);
                break;
            case 1:
                Intent nextActivity2 = new Intent(this, AvisoNoAutorizado.class);
                startActivity(nextActivity2);
                break;*/
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

    private void nextActivityQRGenerator() {
        Intent nextActivity = new Intent(this, GeneradorQR.class);
        startActivity(nextActivity);
    }

    private void registerVisitor(final String nameVisitor, final String lastnameVisitor, final String documentNumber, final String licencePlate) {
        StringRequest stringRequest = new StringRequest(

                Request.Method.POST,
                urlVisitor,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        nextActivityQRGenerator();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(VisitorsRegister.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                }
        ){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("nameVisitor", nameVisitor);
                params.put("lastnameVisitor", lastnameVisitor);
                params.put("IdTipoDocumento", "DNI");
                params.put("nroDocumento", documentNumber);
                params.put("dateEntry", "");
                params.put("licencePlate", licencePlate);
                return params;
            }
        };

        requestQueue.add(stringRequest);
    }


}