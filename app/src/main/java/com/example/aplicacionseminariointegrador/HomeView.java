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


/*
*
    TextView txtFastAccessApp;
    Button btnIdLogin2, btnIdRegister;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_application);

        txtFastAccessApp = findViewById(R.id.lblFastAccessAppPrincipalTitle);

        btnIdLogin2 = (Button) findViewById(R.id.btnIdLogin2);
        btnIdRegister = (Button) findViewById(R.id.btnIdRegister);

        if(LanguageSelected.languageSelected == 0) {
            btnIdLogin2.setText("LOGIN");
            btnIdRegister.setText("REGISTER");
        }
        else {
            btnIdLogin2.setText("ACCESO");
            btnIdRegister.setText("REGISTRO");
        }


        animation();
    }

    public void changeActivityLogin(View view) {
        Intent nextActivity = new Intent(this, LoginActivity.class);
        startActivity(nextActivity);
    }

    public void changeActivityRegisterAcoount(View view) {
        Intent nextActivity = new Intent(this, SelectRegisterUser.class);
        startActivity(nextActivity);
    }

    public void changeActivityCredits(View view) {
        Intent nextActivity = new Intent(this, CreditsAplication.class);
        startActivity(nextActivity);
    }

    public void changeActivityOptions(View view) {
        Intent nextActivity = new Intent(this, OptionsActivity.class);
        startActivity(nextActivity);
    }

    @Override
    public void onBackPressed() {
        //Toast.makeText(StartApplication.this, "Action not allowed!", Toast.LENGTH_SHORT).show();
        return;
    }
*/