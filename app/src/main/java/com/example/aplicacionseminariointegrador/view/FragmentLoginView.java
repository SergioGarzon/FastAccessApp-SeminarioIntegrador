package com.example.aplicacionseminariointegrador.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.aplicacionseminariointegrador.databinding.FragmentLoginViewBinding;

public class FragmentLoginView extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        FragmentLoginViewBinding binding = FragmentLoginViewBinding.inflate(inflater, container, false);
        View view = binding.getRoot();



        return view;
    }
}