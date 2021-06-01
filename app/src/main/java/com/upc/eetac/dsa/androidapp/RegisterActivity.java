package com.upc.eetac.dsa.androidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import models.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    EditText username;
    EditText email;
    EditText password;
    ProgressBar pb;
    int counter = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //startProgressBar();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username=findViewById(R.id.user);
        email=findViewById(R.id.emailAddress);
        password=findViewById(R.id.password);


    }

    /*private void startProgressBar() {

        pb = (ProgressBar)findViewById(R.id.progressBar);

        final Timer t = new Timer();
        TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                counter = counter + 2;
                pb.setProgress(counter);

                if (counter==100){
                    t.cancel();
                }
            }
        };

        t.schedule(tt,0,100);
    }*/

    public void registrar(View view){

        Intent intent = new Intent(this, RegisterActivity.class);


        User usuario = new User(username.getText().toString(), email.getText().toString(), password.getText().toString());
        Call<models.User> call = ClientAPI.getUserService().addUser(usuario);

        call.enqueue(new Callback<models.User>() {
            @Override
            public void onResponse(Call<models.User> call, Response<models.User> response) {
                Log.i("", "Codigo del servidor: "+ response.code());
                models.User usuarioServidor = response.body();
                Log.i("","Usuario enviado: " + usuarioServidor);
                if (response.code() ==201)
                {
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<models.User> call, Throwable t) {

            }
        });
    }

    /*
    UserService userAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        setTheme(R.style.Theme_AndroidApp);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        userAPI = ClientAPI.getUserService().create(UserService.class);

    }

    public void sendRegister(View view) {
        EditText uname = (EditText) findViewById(R.id.user);
        EditText pswrd = (EditText) findViewById(R.id.password);
        EditText emailAddress = (EditText) findViewById(R.id.emailAddress);
        String usuario = uname.getText().toString();
        String password = pswrd.getText().toString();
        String email = emailAddress.getText().toString();
        Call<User> call = userAPI.addUser(new User(usuario, password, email));
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.d("TAG", response.code() + "");
                if (response.code() == 201) {
                    User usuario = response.body();
                    String pswrd = usuario.getPassword();
                    String uname = usuario.getUsername();
                    Log.d("Usuario", uname + " " + pswrd);
                    Toast toast = Toast.makeText(getApplicationContext(), "Registration succesfull! Go back and sign in", Toast.LENGTH_LONG);
                    toast.show();
                } else {
                    Log.d("Error", "Register failed");
                    Toast toast = Toast.makeText(getApplicationContext(), "Register failed! Please try again", Toast.LENGTH_LONG);
                    toast.show();
                }

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                call.cancel();
                Log.d("Error", "Failure");

            }
        });

    }


    */

}
