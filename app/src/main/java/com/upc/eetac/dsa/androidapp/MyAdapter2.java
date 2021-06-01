package com.upc.eetac.dsa.androidapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import models.Insignias;
import models.User;

public class MyAdapter2 extends RecyclerView.Adapter<MyAdapter2.ViewHolder> {

    Insignias insigniasAdapter;

    public MyAdapter2(Insignias insigniasAdapter) {
        this.insigniasAdapter = insigniasAdapter;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        //false es que no es el raiz
        View v =
                inflater.inflate(R.layout.row_layout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtInsignia.setText(insigniasAdapter.nomInsignia);
        Picasso.get().load(insigniasAdapter.fotoInsignia).into(holder.imagen);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imagen;
        public TextView txtInsignia;
        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            txtInsignia = v.findViewById(R.id.firstLine);
            imagen = v.findViewById(R.id.icon);
        }
    }

}
