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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.aplicacionseminariointegrador.auxiliarclases.LanguageSelected;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class NotificationsUsersAccount extends AppCompatActivity {

    RequestQueue requestQueueRegisterAccount;
    GridView _grid;
    Button btnExitUserAccount, btnModifyUser;
    TextView txtUsuariosRegistrados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications_users_account);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        requestQueueRegisterAccount = Volley.newRequestQueue(this);

        btnExitUserAccount = findViewById(R.id.btnExitUserAccount);
        btnModifyUser = findViewById(R.id.btnModifyUser);
        txtUsuariosRegistrados = findViewById(R.id.txtUsuariosRegistrados);

        if(LanguageSelected.languageSelected == 0) {
            btnExitUserAccount.setText("CANCEL");
            btnModifyUser.setText("MODIFY");
            txtUsuariosRegistrados.setText("REGISTERED USERS");
        } else {
            btnExitUserAccount.setText("CANCELAR");
            btnModifyUser.setText("MODIFICAR");
            txtUsuariosRegistrados.setText("USUARIOS REGISTRADOS");
        }

        btnExitUserAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backActivityMenuStart(v);
            }
        });

        btnModifyUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeActivityModify();
            }
        });

        _grid = findViewById(R.id.grid1);

        readUsers();
    }

    private void changeActivityModify() {
        Vibrator vibrator = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(1000);

        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setMessage("Function not available!");
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
    private void readUsers() {
        String urlnueva="https://fastaccessapp.000webhostapp.com/proyectobdejemplo/consultarDatosCuentas.php";



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

                            String[] datos = {nombreUsuario, accesoValor};
                            _grid.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, datos));


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );

        requestQueueRegisterAccount.add(_jsonobjectrequest);

    }

    private void backActivityMenuStart(View v) {

        switch (LanguageSelected.sesion) {
            case 0:
                Intent nextActivity = new Intent(this, UsuarioPendienteAprobacion.class);
                startActivity(nextActivity);
                break;
            case 1:
                Intent nextActivity2 = new Intent(this, AvisoNoAutorizado.class);
                startActivity(nextActivity2);
                break;
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

}