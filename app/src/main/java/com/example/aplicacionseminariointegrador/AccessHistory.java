package com.example.aplicacionseminariointegrador;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
import com.example.aplicacionseminariointegrador.databinding.ActivityAccessHistoryBinding;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

public class AccessHistory extends AppCompatActivity {

    private ActivityAccessHistoryBinding binding;

    private ArrayList<Integer> listaIds = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);

        binding = ActivityAccessHistoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String textAccessHistory = (LanguageSelected.languageSelected == 0) ? "ACCESS HISTORY" : "HISTORIAL DE ACCESOS";
        binding.txtAccessHistory.setText(textAccessHistory);

        String textoBoton = (LanguageSelected.languageSelected == 0) ? "BACK" : "VOLVER";
        binding.btnBackAccessHistory.setText(textoBoton);

        cargarDatosSpinner();
        extraerHistorial(LanguageSelected.idUser - 1);

        binding.btnBackAccessHistory.setOnClickListener(v -> { finish(); });

        binding.spnUsersNames.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int posicion, long l) {

                if (posicion == 0) {
                    binding.txtHistoryAccessData.setText("Seleccione un usuario");
                    return;
                }

                int idUsuario = listaIds.get(posicion);
                extraerHistorial(idUsuario);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void extraerHistorial(int id_usuario_extraer) {

        binding.txtHistoryAccessData.clearComposingText();
        binding.txtHistoryAccessData.setText("Cargando...");

        String urlNueva = "http://192.168.0.22:8080/fastaccessapp/history.php";

        StringRequest respuesta;
        respuesta = new StringRequest(Request.Method.POST, urlNueva, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equalsIgnoreCase("ERROR")) {
                    Toast.makeText(AccessHistory.this, "ERROR", Toast.LENGTH_SHORT).show();
                } else {

                    try {
                        if(response.compareTo("") != 0) {
                            // 1. Convertir la respuesta String a un JSONArray
                            JSONArray jsonArray = new JSONArray(response);

                            if(jsonArray.length() > 0) {
                                StringBuilder historialTexto = new StringBuilder();

                                int contador = jsonArray.length();

                                for (int i = 0; i < jsonArray.length(); i++) {
                                    try {
                                        historialTexto.append(contador + ") ")
                                                .append(jsonArray.getString(i).replace("T", " ").substring(0, jsonArray.getString(i).indexOf(".")))
                                                .append("\n");
                                    } catch (DateTimeParseException e) {
                                        historialTexto.append("ERROR " + e.getMessage()).append("\n");
                                    }

                                    contador--;
                                }

                                // 4. Mostrar en el TextView
                                binding.txtHistoryAccessData.setText(historialTexto.toString());
                            }
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(AccessHistory.this, "Error al procesar datos", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse (VolleyError error) {
                Toast.makeText(AccessHistory.this, "ERROR", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new Hashtable<>();
                params.put("id_usuario", String.valueOf(id_usuario_extraer + 1));

                return params;
            }
        };

        RequestQueue requestQueue1 = Volley.newRequestQueue(AccessHistory.this);
        requestQueue1.add(respuesta);
    }


    private void cargarDatosSpinner() {

        String urlNueva = "http://192.168.0.22:8080/fastaccessapp/getusers.php";

        StringRequest respuesta;
        respuesta = new StringRequest(Request.Method.POST, urlNueva, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        ArrayList<String> listaUsuarios = new ArrayList<>();

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject objeto = jsonArray.getJSONObject(i);
                            String nombre = objeto.getString("name_user");
                            listaUsuarios.add(nombre);
                        }

                        ArrayList<String> listaNombres = new ArrayList<>();

                        listaIds.clear();
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject objeto = jsonArray.getJSONObject(i);

                            listaIds.add(objeto.getInt("id_usuario")); // Llenas la global
                            listaNombres.add(objeto.getString("name_user"));
                        }

                        ArrayAdapter<String> adapter = new ArrayAdapter<>(AccessHistory.this,
                                android.R.layout.simple_spinner_item, listaUsuarios);

                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                        binding.spnUsersNames.setAdapter(adapter);
                        binding.spnUsersNames.setEnabled(true);
                        binding.spnUsersNames.setSelection(LanguageSelected.idUser - 1);


                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(AccessHistory.this, "Error al procesar datos", Toast.LENGTH_SHORT).show();
                    }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse (VolleyError error) {
                Toast.makeText(AccessHistory.this, "ERROR", Toast.LENGTH_SHORT).show();
            }
        }){
        };

        RequestQueue requestQueue1 = Volley.newRequestQueue(AccessHistory.this);
        requestQueue1.add(respuesta);
    }

}