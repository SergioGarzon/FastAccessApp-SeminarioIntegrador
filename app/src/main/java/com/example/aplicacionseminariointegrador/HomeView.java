package com.example.aplicacionseminariointegrador;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import com.example.aplicacionseminariointegrador.auxiliarclases.LanguageSelected;
import com.example.aplicacionseminariointegrador.databinding.FragmentHomeViewBinding;


public class HomeView extends Fragment {

    private FragmentHomeViewBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentHomeViewBinding.inflate(inflater, container, false);
        View view = binding.getRoot();


        if(LanguageSelected.languageSelected == 0) {
            binding.btnIdLogin.setText("LOGIN");
            binding.btnIdRegister.setText("REGISTER");
        }
        else {
            binding.btnIdLogin.setText("ACCESO");
            binding.btnIdRegister.setText("REGISTRO");
        }

        animation();

        binding.btnCreditsInformation.setOnClickListener(v -> {
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.main_container, new CreditsView())
                    .addToBackStack(null)
                    .commit();
        });

        binding.btnIdLogin.setOnClickListener(v -> { });

        return view;
    }

    public void animation() {
        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.animationvisible);
        animation.setRepeatCount(Animation.INFINITE);
        binding.lblFastAccessAppPrincipalTitle.setAnimation(animation);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null; // Buena práctica para evitar fugas de memoria
    }

}
