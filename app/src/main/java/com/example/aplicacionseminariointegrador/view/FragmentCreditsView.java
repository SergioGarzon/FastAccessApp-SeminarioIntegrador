package com.example.aplicacionseminariointegrador.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.aplicacionseminariointegrador.R;
import com.example.aplicacionseminariointegrador.model.LanguageSelected;
import com.example.aplicacionseminariointegrador.databinding.FragmentCreditsViewBinding;

public class FragmentCreditsView extends Fragment {
    private FragmentCreditsViewBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentCreditsViewBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        String textCredits, textBtnCredits, url;

        textCredits = (LanguageSelected.languageSelected == 0) ? getString(R.string.credits_description) :
                getString(R.string.creditos_descripcion);

        textBtnCredits = (textCredits.equals(getString(R.string.credits_description))) ? getString(R.string.text_ok) : getString(R.string.text_aceptar);

        binding.lblTxtCredits.setText(textCredits);
        binding.btnCreditsOk.setText(textBtnCredits);

        binding.imgBtnLinkedinFacundo.setOnClickListener(v -> { openBrowser(getString(R.string.text_browser_url1)); });
        binding.imgBtnLinkedinSergio.setOnClickListener(v -> { openBrowser(getString(R.string.text_browser_url2)); });
        binding.imgBtnLinkedinMatias.setOnClickListener(v -> { openBrowser(getString(R.string.text_browser_url3)); });
        binding.imgBtnLinkedInGenaro.setOnClickListener(v -> { openBrowser(getString(R.string.text_browser_url4)); });

        binding.btnCreditsOk.setOnClickListener( v -> { getParentFragmentManager().popBackStack(); });

        return view;
    }

    private void openBrowser(String urn) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.text_browser_url0) + urn)));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null; // Buena práctica para evitar fugas de memoria
    }
}