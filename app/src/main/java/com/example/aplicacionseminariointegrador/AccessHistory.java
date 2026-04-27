package com.example.aplicacionseminariointegrador;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
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
import java.util.Hashtable;
import java.util.Map;

public class AccessHistory extends AppCompatActivity {

    private ActivityAccessHistoryBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);

        binding = ActivityAccessHistoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String textAccessHistory = (LanguageSelected.languageSelected == 0) ? "ACCESS HISTORY" : "HISTORIAL DE ACCESOS";
        binding.txtAccessHistory.setText(textAccessHistory);

        extraerHistorial();

        binding.btnBackAccessHistory.setOnClickListener(v -> { finish(); });
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

                    try {
                        // 1. Convertir la respuesta String a un JSONArray
                        JSONArray jsonArray = new JSONArray(response);

                        StringBuilder historialTexto = new StringBuilder();

                        for (int i = 0; i < jsonArray.length(); i++) {
                            String texto2 = jsonArray.getString(i).replace("T", " ");

                            historialTexto.append(texto2).append("\n");


                            /*
                            try {
                                // 1. Parsear el String que viene de PHP (ISO con 'T' y milisegundos)
                                LocalDateTime fechaHora = LocalDateTime.parse(texto2, DateTimeFormatter.ISO_LOCAL_DATE_TIME);

                                // 2. Formatear a tu gusto: "dd/MM/yyyy hh:mm:ss"
                                // Nota: 'hh' es para formato 12h. Si quieres 24h usa 'HH'.
                                DateTimeFormatter nuevoFormato = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss a");

                                String fechaFinal = fechaHora.format(nuevoFormato);
                                historialTexto.append(fechaFinal).append("\n");

                            } catch (DateTimeParseException e) {

                            }*/
                        }



                        // 4. Mostrar en el TextView
                        binding.txtHistoryAccessData.setText(historialTexto.toString());

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
                params.put("id_usuario", String.valueOf(LanguageSelected.idUser));
                return params;
            }
        };

        RequestQueue requestQueue1 = Volley.newRequestQueue(AccessHistory.this);
        requestQueue1.add(respuesta);

    }


}