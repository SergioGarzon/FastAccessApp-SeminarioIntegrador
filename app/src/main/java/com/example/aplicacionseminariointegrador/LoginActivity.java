package com.example.aplicacionseminariointegrador;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.aplicacionseminariointegrador.auxiliarclases.LanguageSelected;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
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
        } else {
            readUsers();
        }

    }

    private void readUsers() {
/*
        String urlnueva="http://localhost:8080/fastaccessapp/login.php?name_user=" + txtUsername.getEditText().getText().toString() + "&password_user=" + txtPassword.getEditText().getText().toString();
        JsonObjectRequest _jsonobjectrequest = new JsonObjectRequest(
                Request.Method.GET,
                urlnueva,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        String nombreUsuario, accesoValor;

                        try {
                            nombreUsuario = response.getString("name_user");
                            accesoValor = response.getString("value_access");

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
*/


        String urlNueva = "http://localhost:8080/fastaccessapp/login.php";
        String name_user = txtUsername.getEditText().getText().toString().trim();
        String password_user = txtPassword.getEditText().getText().toString().trim();

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Cargando...");
        progressDialog.show();

        try {
            Thread.sleep(2000);
        } catch (Exception e) {}

        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlNueva,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("login");

                            if (success.equals("1")) {
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject object = jsonArray.getJSONObject(i);
                                    int idUsuario = object.getInt("id_usuario"); // Usar getInt()
                                    String objusername = object.getString("name_user");
                                    String passwordUser = object.getString("password_user");
                                    int valueAccess = object.getInt("value_access"); // Usar getInt()
                                    int idPerson = object.getInt("id_person"); // Usar getInt()

                                    switch (valueAccess) {
                                        case 0:
                                            usuarioPendiente();
                                            LanguageSelected.sesion = 0;
                                            break;
                                        case 1:
                                            usuarioNoAutorizado();
                                            LanguageSelected.sesion = 1;
                                            break;
                                        case 2:
                                            menuResidente(objusername);
                                            LanguageSelected.sesion = 2;
                                            break;
                                        case 3:
                                            menuAplicacion(objusername); // Menu usuario administrador
                                            LanguageSelected.sesion = 3;
                                            break;
                                    }
                                }
                            } else {
                                userNotFound();
                            }

                            progressDialog.dismiss();

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(LoginActivity.this, "Error JSON: " + e.toString(), Toast.LENGTH_LONG).show();
                            progressDialog.dismiss();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LoginActivity.this, "Error Volley: " + error.toString(), Toast.LENGTH_LONG).show();
                        progressDialog.dismiss();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("name_user", name_user);
                params.put("password_user", password_user); // Agregar password_user
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


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