package com.calculo_compras;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class LogoApp extends AppCompatActivity {

    private Timer timer = new Timer();
    private TimerTask timerTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo_app);
        getSupportActionBar().hide();

        timerTask = new TimerTask() {
            @Override
            public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                           passaProximaTela();
                        }
                    });
            }
        };
        timer.schedule(timerTask,3000);
    }

    private void passaProximaTela() {
        Intent intent = new Intent(getApplicationContext(),CalcularCompras.class);
        intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}