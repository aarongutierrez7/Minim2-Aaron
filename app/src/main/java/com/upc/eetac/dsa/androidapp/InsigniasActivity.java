package com.upc.eetac.dsa.androidapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import models.Insignias;
import models.User;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InsigniasActivity extends AppCompatActivity {

    Retrofit retrofit;
    API service;
    List<Insignias> insigniasList = new ArrayList<>();

    //Para crear el recycler
    private RecyclerView recyclerView;
    private MyAdapter2 myAdapter2;
    private RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insignias);


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
        getInsignias();
    }

    private void getInsignias()
    {
        try{
            Call<List<Insignias>> insignias = service.getinsigniasInfo();
            NotifyUser("Server nombre" + "pepe" + "nombre");
            insignias.enqueue(new Callback<List<Insignias>>() {
                @Override
                public void onResponse(Call<List<Insignias>> call, Response<List<Insignias>> response) {

                    if (response.isSuccessful()) {
                        insigniasList = response.body();

                        // non empty response, Mapping Json via Gson...
                        NotifyUser("Server Response Ok");
                        myAdapter2 = new MyAdapter2(insigniasList);
                        recyclerView.setAdapter(myAdapter2);
                    }
                    else{
                        NotifyUser("Request failed");
                    }
                }

                @Override
                public void onFailure(Call<List<Insignias>> call, Throwable t) {
                    NotifyUser("Request failed");

                }
            });


        }catch(Exception e){
            NotifyUser("Exception: " + e.toString());
        }

    }

    private void NotifyUser(String MSG){
        Toast toast = Toast.makeText(InsigniasActivity.this,MSG,Toast.LENGTH_SHORT);
        toast.show();
    }

}