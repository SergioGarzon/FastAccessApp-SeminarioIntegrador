package com.example.aplicacionseminariointegrador;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.aplicacionseminariointegrador.auxiliarclases.LanguageSelected;

import java.util.Hashtable;
import java.util.Map;

public class AccessHistory extends AppCompatActivity {

    TextView txtLblHistoryAccessData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_access_history);

        txtLblHistoryAccessData = findViewById(R.id.txtHistoryAccessData);

        extraerHistorial();
    }

    private void extraerHistorial() {
        String urlNueva = "http://192.168.0.22:8080/fastaccessapp/history.php";

        StringRequest respuesta;
        respuesta = new StringRequest(Request.Method.POST, urlNueva, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equalsIgnoreCase("ERROR")) {
                    Toast.makeText(AccessHistory.this, "ERROR", Toast.LENGTH_SHORT).show();
                } else {
                    txtLblHistoryAccessData.setText(response.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse (VolleyError error) {
                //Toast.makeText(LoginActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
            }
        }){/*
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new Hashtable<>();
                params.put("id_usuario", String.valueOf(LanguageSelected.idUser));
                return params;
            }*/
        };

        RequestQueue requestQueue1 = Volley.newRequestQueue(AccessHistory.this);
        requestQueue1.add(respuesta);

    }


}