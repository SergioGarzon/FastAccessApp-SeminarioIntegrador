package com.example.aplicacionseminariointegrador;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class ProfileAccount extends AppCompatActivity {

    Button btnCancelSaveProfile, btnVisibility;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_account);

        btnVisibility = (Button) findViewById(R.id.btnVisibility);

        btnVisibility.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditVisibilityBtn(v);
            }
        });
    }

    private void EditVisibilityBtn(View v) {
        Intent nextActivity = new Intent(this, EditVisibility.class);
        startActivity(nextActivity);
    }
}