package com.upn.proyecto_final;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.upn.proyecto_final.entidad.Oferta;

import java.util.List;

public class AdapterFoodMenu extends RecyclerView.Adapter<AdapterFoodMenu.MyViewHolder> {

    private Context context;
    private List<Oferta> menulista;

    public AdapterFoodMenu(Context context, List<Oferta> menulista){
        this.context = context;
        this.menulista = menulista;
    }

    @NonNull
    @Override
    public AdapterFoodMenu.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(this.context);
        View view = inflater.inflate(R.layout.filaofertas, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterFoodMenu.MyViewHolder holder, int position) {
        holder.nombre.setText(menulista.get(position).getNombre());
        holder.descripcion.setText(menulista.get(position).getDescripcion());
        holder.precio.setText("S/."+menulista.get(position).getPrecio());
    }

    @Override
    public int getItemCount() {
        return menulista.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView nombre, descripcion, precio;
        Button btnOrdenar;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            nombre = itemView.findViewById(R.id.menuNombre);
            descripcion = itemView.findViewById(R.id.menuDescripcion);
            precio = itemView.findViewById(R.id.menuPrecio);
            btnOrdenar = itemView.findViewById(R.id.btnOrdenar);
        }
    }
}
