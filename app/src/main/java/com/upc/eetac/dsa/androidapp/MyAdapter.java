package com.upc.eetac.dsa.androidapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;


import models.User;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    User userAdapter;
    public ImageView imagen;

    public MyAdapter(User users) {
        this.userAdapter = users;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        //false es que no es el raiz
        View v =
                inflater.inflate(R.layout.activity_perfil, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //final User repositorio = user.get(position);
        holder.txtUser.setText(userAdapter.name);
        holder.txtUserName.setText(userAdapter.username);
        Picasso.get().load(userAdapter.avatar).into(holder.imagen);

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imagen;
        public TextView txtUser;
        public TextView txtUserName;
        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            txtUser = v.findViewById(R.id.nomText);
            txtUserName = v.findViewById(R.id.userNameText);
            imagen = v.findViewById(R.id.imagen);

        }
    }

}
