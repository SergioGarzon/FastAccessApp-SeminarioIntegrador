package com.example.aplicacionseminariointegrador.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.aplicacionseminariointegrador.R;
import com.example.aplicacionseminariointegrador.databinding.FragmentRegisterSecondViewBinding;

public class FragmentRegisterSecondView extends Fragment {

    private FragmentRegisterSecondViewBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentRegisterSecondViewBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        binding.btnCancelCreateAccount2.setOnClickListener(v -> { getParentFragmentManager().popBackStack(); });


        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null; // Buena práctica para evitar fugas de memoria
    }
}