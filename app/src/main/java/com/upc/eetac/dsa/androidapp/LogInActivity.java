package com.upc.eetac.dsa.androidapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import models.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import androidx.appcompat.app.AppCompatActivity;

public class LogInActivity extends AppCompatActivity {

    UserService userAPI;
    EditText uname;
    EditText pswrd;
    Button signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //userAPI = ClientAPI.getUserService(UserService.class);
        uname = (EditText) findViewById(R.id.user);
        pswrd = (EditText) findViewById(R.id.password);

        signUpButton=(Button)findViewById(R.id.register);
        ProgressBarDialog loadingPB = new ProgressBarDialog(LogInActivity.this);

        signUpButton.setOnClickListener(new View.OnClickListener(){
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
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    public void sendLogin(View view) {
        uname = (EditText) findViewById(R.id.user);
        pswrd = (EditText) findViewById(R.id.password);
        String username = uname.getText().toString();
        String password = pswrd.getText().toString();

        Call<User> call = userAPI.loginUser(new User(username, password, ""));
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.d("TAG", response.code() + "");
                if (response.code() == 201) {
                    User usuario = response.body();
                    String pswrd = usuario.getPassword();
                    String uname = usuario.getUsername();
                    Integer id = usuario.getIdUser();
                    Log.d("Usuario", uname + " " + pswrd + " " + id);
                } else {
                    Log.d("Error", "Login failed");
                    Toast toast = Toast.makeText(getApplicationContext(), "Login failed! Please try again", Toast.LENGTH_LONG);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            toast.show();
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                call.cancel();
                Log.d("Error", "Failure");
            }
        });

    }
}
