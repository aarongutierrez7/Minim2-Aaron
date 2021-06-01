package com.upc.eetac.dsa.androidapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

import models.User;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PerfilActivity extends AppCompatActivity {


    Retrofit retrofit;
    API service;
    User user;

    //Para crear el recycler
    private RecyclerView recyclerView;
    private MyAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);


        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //Attaching Interceptor to a client
        OkHttpClient client = new OkHttpClient().newBuilder().addInterceptor(interceptor).build();
        retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:8080/dsaApp/")
                // .addConverterFactory(new NullOnEmptyConverterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        service = retrofit.create(API.class);
        getPerfil();
    }

    private void getPerfil()
    {
        try{
            Call<User> users = service.getperfilInfo("aaronguti");
            NotifyUser("Server nombre" + "pepe" + "nombre");
            users.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {

                    if (response.isSuccessful()) {
                        user = (User) response.body();

                        // non empty response, Mapping Json via Gson...
                        NotifyUser("Server Response Ok");
                        mAdapter = new MyAdapter(user);
                        recyclerView.setAdapter(mAdapter);
                    }
                    else{
                        NotifyUser("Request failed");
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    NotifyUser("Request failed");
                }
            });


        }catch(Exception e){
            NotifyUser("Exception: " + e.toString());
        }

    }

    private void NotifyUser(String MSG){
        Toast toast = Toast.makeText(PerfilActivity.this,MSG,Toast.LENGTH_SHORT);
        toast.show();
    }

}