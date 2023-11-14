package com.example.aplicacionseminariointegrador;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.aplicacionseminariointegrador.auxiliarclases.LanguageSelected;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    Button btnLogin, btnCancelLogin;
    TextInputLayout txtUsername, txtPassword;
    TextView txtLblUsername, txtLblPassword, lblLogin;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        requestQueue = Volley.newRequestQueue(this);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnCancelLogin = (Button) findViewById(R.id.btnCancelLogin);
        txtUsername = (TextInputLayout) findViewById(R.id.txtInputUsername);
        txtPassword = (TextInputLayout) findViewById(R.id.txtInputPassword);
        txtLblUsername = (TextView) findViewById(R.id.txtLblUsername);
        txtLblPassword = (TextView) findViewById(R.id.txtLblPass);
        lblLogin = (TextView) findViewById(R.id.lblLogin);

        if(LanguageSelected.languageSelected == 0) {
            btnLogin.setText("SIGN IN");
            btnCancelLogin.setText("CANCEL");
            txtLblUsername.setText("Username:");
            txtLblPassword.setText("Password:");
            lblLogin.setText("LOGIN");
        } else {
            btnLogin.setText("INICIAR SESION");
            btnCancelLogin.setText("CANCELAR");
            txtLblUsername.setText("Nombre de usuario:");
            txtLblPassword.setText("Contraseña:");
            lblLogin.setText("ACCESO");
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {  validateUser(v);  }
        });

        btnCancelLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backStartActivity(v);
            }
        });
    }

    private void backStartActivity(View v) {
        Intent nextActivity = new Intent(this, StartApplication.class);
        startActivity(nextActivity);
    }

    private void validateUser(View v) {

        if(txtUsername.getEditText().getText().toString().compareTo("") == 0 &&  txtPassword.getEditText().getText().toString().compareTo("") == 0) {
            Vibrator vibrator = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(1000);

            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setMessage("User o password incorrect");
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
            readUsers();
        }

    }

    private void readUsers() {
        String urlnueva="https://fastaccessapp.000webhostapp.com/proyectobdejemplo/consultarUsuarios.php?nombreUsuario=" + txtUsername.getEditText().getText().toString() + "&passwordUser=" + txtPassword.getEditText().getText().toString();
        JsonObjectRequest _jsonobjectrequest = new JsonObjectRequest(
                Request.Method.GET,
                urlnueva,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        String nombreUsuario, accesoValor;

                        try {
                            nombreUsuario = response.getString("nombreUsuario");
                            accesoValor = response.getString("accesoValor");

                            switch (Integer.parseInt(accesoValor)) {
                                case 0:
                                    usuarioPendiente();
                                    LanguageSelected.sesion = 0;
                                    break;
                                case 1:
                                    usuarioNoAutorizado();
                                    LanguageSelected.sesion = 1;
                                    break;
                                case 2:
                                    menuResidente(nombreUsuario);
                                    LanguageSelected.sesion = 2;
                                    break;
                                case 3:
                                    menuAplicacion(nombreUsuario);
                                    LanguageSelected.sesion = 3;
                                    break;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        userNotFound();
                    }
                }
        );

        requestQueue.add(_jsonobjectrequest);
    }

    private void userNotFound() {
        Vibrator vibrator = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(1000);

        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setMessage("User not found!");
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

    private void usuarioPendiente() {
        Intent nextActivity = new Intent(this, UsuarioPendienteAprobacion.class);
        startActivity(nextActivity);
    }

    private void usuarioNoAutorizado() {
        Intent nextActivity2 = new Intent(this, AvisoNoAutorizado.class);
        startActivity(nextActivity2);
    }

    private void menuResidente(String usuario) {
        Intent nextActivity3 = new Intent(this, MenuApplicationResidente.class);
        nextActivity3.putExtra("Usuario", usuario);
        startActivity(nextActivity3);
    }

    private void menuAplicacion(String usuario) {
        Intent nextActivity4 = new Intent(this, MenuAplication.class);
        nextActivity4.putExtra("Usuario", usuario);
        startActivity(nextActivity4);
    }

}