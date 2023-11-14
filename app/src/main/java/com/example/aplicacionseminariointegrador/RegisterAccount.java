package com.example.aplicacionseminariointegrador;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
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
import com.google.android.material.textfield.TextInputLayout;

import java.util.HashMap;
import java.util.Map;

public class RegisterAccount extends AppCompatActivity {

    Button btnCancelCreateAccount, btnRegisterUser;
    TextView txtLblCreateAccount, txtLblNameSurnames,
            txtLblUsernameCreateAccount, txtLblPasswordCreateAccount, txtLblSelectRole;

    TextInputLayout textInputLayoutNombre, txtInputLayoutPassword;

    Spinner spinnerRol;

    RequestQueue requestQueue;

    private static final String url1 = "https://fastaccessapp.000webhostapp.com/proyectobdejemplo/save.php?";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_account);

        requestQueue = Volley.newRequestQueue(this);

        btnCancelCreateAccount = findViewById(R.id.btnCancelCreateAccount);
        btnRegisterUser = findViewById(R.id.btnRegisterUser);

        txtLblCreateAccount =  findViewById(R.id.txtLblCreateAccount);
        txtLblNameSurnames =  findViewById(R.id.txtLblNamesSurnames);
        txtLblUsernameCreateAccount =  findViewById(R.id.txtLblUsernameCreateAccount);
        txtLblPasswordCreateAccount =  findViewById(R.id.txtLblPasswordCreateAccount);
        txtLblSelectRole = findViewById(R.id.txtLblSelectRole);

        textInputLayoutNombre = findViewById(R.id.textInputLayout2);
        txtInputLayoutPassword = findViewById(R.id.textInputLayout3);

        spinnerRol = findViewById(R.id.spinnerRol);

        if(LanguageSelected.languageSelected == 0) {
            btnCancelCreateAccount.setText("CANCEL");
            btnRegisterUser.setText("REGISTER");
            txtLblCreateAccount.setText("CREATE ACCOUNT");
            txtLblNameSurnames.setText("Name and surnames:");
            txtLblUsernameCreateAccount.setText("Username:");
            txtLblPasswordCreateAccount.setText("Password:");
            txtLblSelectRole.setText("Select Role:");

            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.array_name_english, android.R.layout.simple_spinner_dropdown_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerRol.setAdapter(adapter);
        } else {
            btnCancelCreateAccount.setText("CANCELAR");
            btnRegisterUser.setText("REGISTRARSE");
            txtLblCreateAccount.setText("CREAR CUENTA");
            txtLblNameSurnames.setText("Nombre y apellido:");
            txtLblUsernameCreateAccount.setText("Nombre de usuario:");
            txtLblPasswordCreateAccount.setText("Contraseña:");
            txtLblSelectRole.setText("Seleccionar rol:");

            ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.array_name, android.R.layout.simple_spinner_dropdown_item);
            adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerRol.setAdapter(adapter2);
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
                createUser(String.valueOf(textInputLayoutNombre.getEditText().getText().toString()),
                        String.valueOf(txtInputLayoutPassword.getEditText().getText().toString()));
            }
        });
    }

    private void backStart_Activity(View v) {
        Intent nextActivity = new Intent(this, StartApplication.class);
        startActivity(nextActivity);
    }

    private void nextRegisterSucessfully() {
        Intent nextActivity = new Intent(this, RegisterSucessful.class);
        startActivity(nextActivity);
    }

    private void createUser(final String nombreUser1, final String password1) {

        StringRequest stringRequest = new StringRequest(

                Request.Method.POST,
                url1,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        nextRegisterSucessfully();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(RegisterAccount.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                }
        ){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Id", "2");
                params.put("nombreUsuario", nombreUser1);
                params.put("passwordUser", password1);
                params.put("accesoValor", "0");
                params.put("IdPersona", "0");
                return params;
            }
        };

        requestQueue.add(stringRequest);
    }
}