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
            binding.btnLogin.setText(getString(R.string.btn_login_access));
            binding.btnCancelLogin.setText(getString(R.string.btn_cancel));
            binding.btnForgotPassword.setText(getString(R.string.btn_forgot_password));
            binding.txtLblUsername.setText(R.string.txt_username);
            binding.txtLblPass.setText(getString(R.string.txt_password));
            binding.lblLogin.setText(getString(R.string.btn_login));
            binding.txtInputUsername.setHint(getString(R.string.txt_enter_username));
            binding.txtInputPassword.setHint(R.string.txt_enter_password);
        } else {
            binding.btnLogin.setText(getString(R.string.btn_iniciar_session));
            binding.btnCancelLogin.setText(getString(R.string.btn_cancelar));
            binding.btnForgotPassword.setText(getString(R.string.btn_olvidateclave));
            binding.txtLblUsername.setText(getString(R.string.txt_nombreusuario));
            binding.txtLblPass.setText(getString(R.string.txt_contrasenia));
            binding.lblLogin.setText(getString(R.string.btn_acceso));
            binding.txtInputUsername.setHint(getString(R.string.txt_ingreseusuario));
            binding.txtInputPassword.setHint(getString(R.string.txt_ingresecontrasena));
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
                getString(R.string.txt_datarequired): getString(R.string.txt_datosingresar);
            dialog.setMessage(messagePassUser);

            dialog.setTitle(getString(R.string.txt_error));
            dialog.setPositiveButton(getString(R.string.text_ok),
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