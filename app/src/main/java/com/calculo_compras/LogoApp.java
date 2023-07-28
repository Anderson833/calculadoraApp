package com.calculo_compras;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class LogoApp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo_app);
        getSupportActionBar().hide();
    }
}