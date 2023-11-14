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
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.aplicacionseminariointegrador.auxiliarclases.LanguageSelected;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ModifyUsers extends AppCompatActivity {

    Button btnCancelModifyUser, btnAnularModfyUser, btnAceptarModifyUser;
    TextView txtModfiyUserActivity, txtNumberIdModifyUsers;
    TextInputLayout txtInputLayoutIdModifyUsers;
    RequestQueue requestQueueModifyAccount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_users);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        btnCancelModifyUser = findViewById(R.id.btnCancelModifyUser);
        txtModfiyUserActivity = findViewById(R.id.txtModfiyUserActivity);
        txtNumberIdModifyUsers = findViewById(R.id.txtNumberIdModifyUsers);
        txtInputLayoutIdModifyUsers = findViewById(R.id.txtInputLayoutIdModifyUsers);
        btnAnularModfyUser = findViewById(R.id.btnAnularModfyUser);
        btnAceptarModifyUser = findViewById(R.id.btnAceptarModifyUser);

        requestQueueModifyAccount = Volley.newRequestQueue(this);

        if(LanguageSelected.languageSelected == 0) {
            btnCancelModifyUser.setText("CANCEL");
            txtModfiyUserActivity.setText("MODIFY USERS");
            txtNumberIdModifyUsers.setText("Enter the identification number (Id):");
            btnAnularModfyUser.setText("DENY");
            btnAceptarModifyUser.setText("ADMIT");
        } else {
            btnCancelModifyUser.setText("CANCELAR");
            txtModfiyUserActivity.setText("MODIFICAR USUARIOS");
            txtNumberIdModifyUsers.setText("Ingrese el numero identificatorio (Id):");
            btnAnularModfyUser.setText("DENEGAR");
            btnAceptarModifyUser.setText("ADMITIR");
        }

        btnCancelModifyUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeNotificationUserActivity();
            }
        });

        btnAceptarModifyUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateData();
            }
        });

        btnAnularModfyUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateData2();
            }
        });



    }

    private void validateData2() {
        if (txtInputLayoutIdModifyUsers.getEditText().getText().toString().equals("")) {
            Vibrator vibrator = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(1000);

            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setMessage("Id not entry");
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
            invalidateData(txtInputLayoutIdModifyUsers.getEditText().getText().toString());
        }
    }

    private void validateData() {
        if (txtInputLayoutIdModifyUsers.getEditText().getText().toString().equals("")) {
            Vibrator vibrator = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(1000);

            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setMessage("Id not entry");
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
            searchDataUsers(txtInputLayoutIdModifyUsers.getEditText().getText().toString());
        }
    }

    private void searchDataUsers(final String id) {
        String urlnueva="https://fastaccessapp.000webhostapp.com/proyectobdejemplo/actualizarDatosUsuario.php?";

        StringRequest stringRequest = new StringRequest(

                Request.Method.POST,
                urlnueva,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        changeNotificationUserActivity();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText( ModifyUsers.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                }
        ){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Id", id);
                return params;
            }
        };

        requestQueueModifyAccount.add(stringRequest);
    }

    private void changeNotificationUserActivity() {
        Intent nextActivity = new Intent(this, NotificationsUsersAccount.class);
        startActivity(nextActivity);
    }

    private void userNotFound(String message) {
        Vibrator vibrator = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(1000);

        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setMessage(message);
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

    private void invalidateData(final String id) {
        String urlnueva="https://fastaccessapp.000webhostapp.com/proyectobdejemplo/denegarUsuarios.php?";

        StringRequest stringRequest = new StringRequest(

                Request.Method.POST,
                urlnueva,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        changeNotificationUserActivity();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText( ModifyUsers.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                }
        ){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Id", id);
                return params;
            }
        };

        requestQueueModifyAccount.add(stringRequest);
    }
}