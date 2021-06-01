package com.upc.eetac.dsa.androidapp;

import java.util.List;

import models.Insignias;
import models.User;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface API {

    @GET("users/{Username}")
    Call<User> getperfilInfo (@Path("Username") String username);

    @GET("insignias")
    Call<List<Insignias>> getinsigniasInfo ();
}
