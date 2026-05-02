package com.example.aplicacionseminariointegrador;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import com.example.aplicacionseminariointegrador.auxiliarclases.LanguageSelected;
import com.example.aplicacionseminariointegrador.databinding.ActivityCreditsAplicationBinding;

public class CreditsAplication extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);

        ActivityCreditsAplicationBinding binding = ActivityCreditsAplicationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String textCredits, textBtnCredits, url;

        textCredits = (LanguageSelected.languageSelected == 0) ? "Fast Access App is a project development by UTN FRC's students:" :
                "Fast Access App es un proyecto desarrollado por estudiantes de la UTN FRC:";

        textBtnCredits = (textCredits.contains("development")) ? "OK" : "ACEPTAR";

        binding.lblTxtCredits.setText(textCredits);
        binding.btnCreditsOk.setText(textBtnCredits);

        binding.imgBtnLinkedinFacundo.setOnClickListener(v -> { openBrowser("facundo-rago/"); });
        binding.imgBtnLinkedinSergio.setOnClickListener(v -> { openBrowser("sergio-gabriel-garzon/"); });
        binding.imgBtnLinkedinMatias.setOnClickListener(v -> { openBrowser("matias-leonel-farach-b2482528a/"); });
        binding.imgBtnLinkedInGenaro.setOnClickListener(v -> { openBrowser("genaro-paredes-6b5785238/"); });

        binding.btnCreditsOk.setOnClickListener( v -> { finish(); });
    }

    // Metodo para abrir Navegador
    private void openBrowser(String urn) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/in/" + urn)));
    }

    // Metodo para el boton Ingresar
    public void changeActivity(View view) {
        finish();
    }
}