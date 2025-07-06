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
import java.util.Hashtable;
import java.util.Map;

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

        if(txtUsername.getEditText().getText().toString().compareTo("") == 0 || txtPassword.getEditText().getText().toString().compareTo("") == 0) {
            Vibrator vibrator = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(1000);

            AlertDialog.Builder dialog = new AlertDialog.Builder(this);

            if(LanguageSelected.languageSelected == 0) {
                dialog.setMessage("Username and/or password is required");
            } else {
                dialog.setMessage("Falta ingresar usuario y/o contraseña");
            }

            dialog.setTitle("Error!");
            dialog.setPositiveButton("Ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
            dialog.setCancelable(true);
            dialog.create().show();

            if(txtUsername.getEditText().getText().toString().isEmpty())
                txtUsername.requestFocus();
            else
                txtPassword.requestFocus();

        } else {
            readUsers();
        }
    }

    private void readUsers() {

        String urlNueva = "http://192.168.0.22:8080/fastaccessapp/login.php";

        StringRequest respuesta;
        respuesta = new StringRequest(Request.Method.POST, urlNueva, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equalsIgnoreCase("ERROR1")) {
                    Toast.makeText(LoginActivity.this, "LOS DATOS ESTAN INCOMPLETOS...!!", Toast.LENGTH_SHORT).show();
                    txtUsername.requestFocus();
                } else if (response.equalsIgnoreCase("ERROR2")) {
                    userNotFound();
                } else {
                    String idUsuario = response.substring(0, response.indexOf("/"));
                    String nombreUsuario = response.substring(response.indexOf("/") + 1, response.indexOf("+"));
                    String valor = response.substring(response.indexOf("+") + 1, response.length());

                    switch (Integer.parseInt(valor)) {
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
                            menuAplicacion(nombreUsuario); // Menu usuario administrador
                            LanguageSelected.sesion = 3;
                            break;
                        case 4:
                            menuSecurity("");
                            LanguageSelected.sesion = 4;
                            break;
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse (VolleyError error) { // ¡Aquí está la corrección!
                Toast.makeText(LoginActivity.this, "ERROR AL INICIAR SESION", Toast.LENGTH_SHORT).show();
                txtUsername.getEditText().setText("");
                txtPassword.getEditText().setText("");
                txtUsername.requestFocus();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new Hashtable<>();
                params.put("name_user", txtUsername.getEditText().getText().toString());
                params.put("password_user", txtPassword.getEditText().getText().toString()); // Agregar password_user
                return params;
            }
        };

        RequestQueue requestQueue1 = Volley.newRequestQueue(LoginActivity.this);
        requestQueue1.add(respuesta);
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

        txtUsername.requestFocus();
        txtPassword.requestFocus();
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

    private void menuSecurity(String usuario) {
        /*Intent nextActivity5 = new Intent(this, activity_menu_application_security.class);
        startActivity(nextActivity5);*/
    }

}