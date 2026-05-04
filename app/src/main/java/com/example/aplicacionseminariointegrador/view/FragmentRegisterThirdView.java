package com.example.aplicacionseminariointegrador.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.aplicacionseminariointegrador.databinding.FragmentRegisterThirdViewBinding;


public class FragmentRegisterThirdView extends Fragment {

    private FragmentRegisterThirdViewBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRegisterThirdViewBinding.inflate(inflater, container, false);
        View view = binding.getRoot();


        binding.btnCancelCreateAccount3.setOnClickListener(v -> { getParentFragmentManager().popBackStack(); });

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null; // Buena práctica para evitar fugas de memoria
    }
}