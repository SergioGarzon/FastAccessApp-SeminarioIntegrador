package com.example.aplicacionseminariointegrador;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.example.aplicacionseminariointegrador.auxiliarclases.LanguageSelected;
import com.example.aplicacionseminariointegrador.databinding.ActivityEmergencyCallBinding;

public class EmergencyCall extends AppCompatActivity {
    private ActivityEmergencyCallBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);


        binding = ActivityEmergencyCallBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        int enabledAdmin = (LanguageSelected.idUser == 5) ? View.VISIBLE : View.INVISIBLE;
        binding.btnCambiarNumeros.setVisibility(enabledAdmin);

        if(LanguageSelected.languageSelected == 0) {
            binding.btnBackEmergencyMenuApplication.setText("BACK");
            binding.btnCambiarNumeros.setText("CHANGE NUMBERS");
            binding.txtDescripcionEmergencyCall.setText("You must press a button to call emergency services");
        } else {
            binding.btnBackEmergencyMenuApplication.setText("VOLVER");
            binding.btnCambiarNumeros.setText("CAMBIAR NUMEROS");
            binding.txtDescripcionEmergencyCall.setText("Debe pulsar un botón para llamar a los servicios de emergencia.");
        }

        binding.btnBackEmergencyMenuApplication.setOnClickListener(v -> { finish(); });
        binding.imgBtnCallPolice.setOnClickListener(v -> { makePhoneAndCall("911"); });
        binding.imgBtnFireFigther.setOnClickListener(v -> { makePhoneAndCall("101"); });
        binding.imgBtnSecurityGuardCountry.setOnClickListener(v -> { makePhoneAndCall("3517416569"); });
        binding.btnImgMedicalEmergency.setOnClickListener( v -> { makePhoneAndCall("107"); });
    }

    private void makePhoneAndCall(String tel) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + tel));
        startActivity(intent);

        Log.d("Eror", "No llama");
    }

}