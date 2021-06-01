package com.upc.eetac.dsa.androidapp;

import java.util.List;

import models.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UserService {

    @POST("auth/registrarUsuario")
    Call<User> addUser(@Body User user);

    @POST("auth/iniciarSesion")
    Call<User> loginUser(@Body User user);

    @GET ("user/{username}")
    Call<User> getUser(@Path("username") String username);

    @GET ("user/listaUsuarios")
    Call<User> getUserList();

    @DELETE("user/borrarUsuario")
    Call<User> deleteUser(@Body String user);
/*
    @PATCH("/dsaApp/{userID}/UpdateMonedas")
    Call<User> updateCoins(@Path("userID") String userID,@Body User user);

     */

    @PUT("")
    Call<Void> updateUser(@Body User usuario);

}
