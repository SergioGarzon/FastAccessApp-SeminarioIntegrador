package com.example.aplicacionseminariointegrador.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.aplicacionseminariointegrador.R;
import com.example.aplicacionseminariointegrador.databinding.FragmentCreditsViewBinding;
import com.example.aplicacionseminariointegrador.databinding.FragmentRegisterViewBinding;


public class FragmentRegisterView extends Fragment {

    private FragmentRegisterViewBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentRegisterViewBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        chargeSpinnerDocumentType();

        binding.btnCancelCreateAccount.setOnClickListener(v -> { getParentFragmentManager().popBackStack(); });

        binding.btnRegisterUser.setOnClickListener(v -> {
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.main_container, new FragmentRegisterSecondView())
                    .addToBackStack(null)
                    .commit();
        });

        return view;
    }

    private void chargeSpinnerDocumentType() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.tipo_documento, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spnTypeDocument.setAdapter(adapter);
    }
}