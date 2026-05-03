package com.example.aplicacionseminariointegrador.view;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.aplicacionseminariointegrador.R;
import com.example.aplicacionseminariointegrador.databinding.FragmentLoginViewBinding;
import com.example.aplicacionseminariointegrador.model.LanguageSelected;

public class FragmentLoginView extends Fragment {

    private FragmentLoginViewBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentLoginViewBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        if(LanguageSelected.languageSelected == 0) {
            binding.btnLogin.setText("SIGN IN");
            binding.btnCancelLogin.setText("CANCEL");
            binding.btnForgotPassword.setText("Forgot Password?");
            binding.txtLblUsername.setText("Username:");
            binding.txtLblPass.setText("Password:");
            binding.lblLogin.setText("LOGIN");
            binding.txtInputUsername.setHint("Enter your username");
            binding.txtInputPassword.setHint("Enter your password");
        } else {
            binding.btnLogin.setText("INICIAR SESION");
            binding.btnCancelLogin.setText("CANCELAR");
            binding.btnForgotPassword.setText("¿Olvidate la contraseña?");
            binding.txtLblUsername.setText("Nombre de usuario:");
            binding.txtLblPass.setText("Contraseña:");
            binding.lblLogin.setText("ACCESO");
            binding.txtInputUsername.setHint("Ingresa tu nombre de usuario");
            binding.txtInputPassword.setHint("Ingresa tu contraseña");
        }

        binding.btnCancelLogin.setOnClickListener(v -> { getParentFragmentManager().popBackStack(); });
        binding.btnLogin.setOnClickListener(v -> { validateUser(v); });

        return view;
    }

    private void validateUser(View v) {

        if(binding.txtInputUsername.getEditText().getText().toString().compareTo("") == 0 || binding.txtInputPassword.getEditText().getText().toString().compareTo("") == 0) {
            Vibrator vibrator = (Vibrator) getContext().getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(1000);

            AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());

            String messagePassUser= (LanguageSelected.languageSelected == 0) ?
                "Username and/or password is required": "Falta ingresar usuario y/o contraseña";
            dialog.setMessage(messagePassUser);

            dialog.setTitle("Error!");
            dialog.setPositiveButton("Ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
            dialog.setCancelable(true);
            dialog.create().show();

            if(binding.txtInputUsername.getEditText().getText().toString().isEmpty())
                binding.txtInputUsername.requestFocus();
            else
                binding.txtInputPassword.requestFocus();

        } else {
            //readUsers();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null; // Buena práctica para evitar fugas de memoria
    }
}