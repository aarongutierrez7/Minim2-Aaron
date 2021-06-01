package com.upc.eetac.dsa.androidapp;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import models.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EliminarActivity extends AppCompatActivity {

    EditText usernameEliminar;
    EditText contraseñaEliminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);


        usernameEliminar=findViewById(R.id.usernameEliminar);
        contraseñaEliminar=findViewById(R.id.contraseñaEliminar);

    }


    public void deleteUser (View view){

        Call<User> call = ClientAPI.getUserService().deleteUser(usernameEliminar.getText().toString());
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.code()==201){
                    Log.i("","El código es: "+response.code());
                }

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                call.cancel();
                Log.d("Error","Failure");
            }
        });
    }
}