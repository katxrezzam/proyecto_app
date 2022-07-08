package com.upn.proyecto_final;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.upn.proyecto_final.entidad.Oferta;

import java.util.List;

public class AdapterCarrito extends RecyclerView.Adapter<AdapterCarrito.MyViewHolder>{

    private Context context;
    private List<Oferta> listaCarrito;

    public AdapterCarrito(Context context, List<Oferta> listaCarrito){
        this.context = context;
        this.listaCarrito = listaCarrito;
    }

    @NonNull
    @Override
    public AdapterCarrito.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterCarrito.MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
