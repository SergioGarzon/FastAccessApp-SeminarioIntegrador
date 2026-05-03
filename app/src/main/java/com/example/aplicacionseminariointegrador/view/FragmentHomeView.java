package com.example.aplicacionseminariointegrador.view;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.aplicacionseminariointegrador.BuildConfig;
import com.example.aplicacionseminariointegrador.model.LanguageSelected;
import com.example.aplicacionseminariointegrador.R;
import com.example.aplicacionseminariointegrador.databinding.FragmentHomeViewBinding;

public class FragmentHomeView extends Fragment {

    private FragmentHomeViewBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentHomeViewBinding.inflate(inflater, container, false);
        View view = binding.getRoot();


        if(LanguageSelected.languageSelected == 0) {
            binding.btnIdLogin.setText(R.string.btn_login);
            binding.btnIdRegister.setText(R.string.btn_register);
        }
        else {
            binding.btnIdLogin.setText(R.string.btn_acceso);
            binding.btnIdRegister.setText(R.string.btn_registro);
        }

        animation();

        //binding.txtPruebaConfig.setText(BuildConfig.API_URL + "/fastaccessapp/history.php");

        binding.btnCreditsInformation.setOnClickListener(v -> {
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.main_container, new FragmentCreditsView())
                    .addToBackStack(null)
                    .commit();
        });

        binding.btnIdLogin.setOnClickListener(v -> { getParentFragmentManager().beginTransaction()
                .replace(R.id.main_container, new FragmentLoginView())
                .addToBackStack(null)
                .commit();
        });

        binding.btnConfiguration.setOnClickListener(v -> { getParentFragmentManager().beginTransaction()
                .replace(R.id.main_container, new FragmentOptionsView())
                .addToBackStack(null)
                .commit();
        });


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
