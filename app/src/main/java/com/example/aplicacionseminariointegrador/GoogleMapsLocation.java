package com.example.aplicacionseminariointegrador;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.webkit.WebView;

public class GoogleMapsLocation extends AppCompatActivity {


    WebView googleMapWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_maps_location);

        String iframe = "<iframe src=https://www.google.com/maps/embed?pb=!1m14!1m12!1m3!1d13621.82278821627!2d-64.18331629999999!3d-31.401568599999997!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!5e0!3m2!1ses-419!2sar!4v1697578808998!5m2!1ses-419!2sar width=400 height=300 style=border:0; loading=lazy referrerpolicy=no-referrer-when-downgrade></iframe>";
        googleMapWebView = (WebView) findViewById(R.id.googleMaps);
        googleMapWebView.getSettings().setJavaScriptEnabled(true);
        googleMapWebView.loadData(iframe, "text/html", "utf-8");
    }
}