package com.upc.eetac.dsa.androidapp;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import models.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditarActivity extends AppCompatActivity {

    EditText username;
    EditText correo;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);

        username=findViewById(R.id.usernameEditar);
        correo=findViewById(R.id.correoEditar);
        password=findViewById(R.id.passwordEditar);

    }

    public void editarUser (View view) {
        User usuario = new User(username.getText().toString(), correo.getText().toString(), password.getText().toString());
        Call <Void> call = ClientAPI.getUserService().updateUser(usuario);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

                if(response.code()==201){
                    Log.i("", "El código es: "+response.code());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });

    }
}
