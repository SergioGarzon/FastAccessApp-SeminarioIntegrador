package com.example.aplicacionseminariointegrador;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.aplicacionseminariointegrador.auxiliarclases.LanguageSelected;
import com.example.aplicacionseminariointegrador.databinding.ActivityCreditsAplicationBinding;
import com.example.aplicacionseminariointegrador.databinding.FragmentCreditsViewBinding;

public class CreditsView extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        FragmentCreditsViewBinding binding = FragmentCreditsViewBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

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

        binding.btnCreditsOk.setOnClickListener( v -> { getParentFragmentManager().popBackStack(); });

        return view;
    }

    private void openBrowser(String urn) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/in/" + urn)));
    }
}