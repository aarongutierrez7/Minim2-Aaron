package com.upc.eetac.dsa.androidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

public class Dashboard extends AppCompatActivity {

    Button perfilButton;
    Button insigniasButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        perfilButton=(Button)findViewById(R.id.perfil);
        insigniasButton=(Button)findViewById(R.id.insignias);

        ProgressBarDialog loadingPB = new ProgressBarDialog(Dashboard.this);

        perfilButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                loadingPB.startPBDialog();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadingPB.dismissPBDialog();
                    }
                }, 4000);
                Intent intent = new Intent(getApplicationContext(), PerfilActivity.class);
                startActivity(intent);
            }
        });

        insigniasButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                loadingPB.startPBDialog();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadingPB.dismissPBDialog();
                    }
                }, 4000);
                Intent intent = new Intent(getApplicationContext(), InsigniasActivity.class);
                startActivity(intent);
            }
        });

    }

}