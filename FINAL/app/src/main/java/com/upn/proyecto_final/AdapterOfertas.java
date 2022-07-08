package com.upn.proyecto_final;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.upn.proyecto_final.entidad.Oferta;

import java.util.List;

public class AdapterOfertas extends RecyclerView.Adapter<AdapterOfertas.MyViewHolder> {

    private Context context;
    private List<Oferta> listaOferta;

    public AdapterOfertas(Context context, List<Oferta> listaOferta){
        this.context = context;
        this.listaOferta = listaOferta;
    }

    @NonNull
    @Override
    public AdapterOfertas.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(this.context);

        View view = inflater.inflate(R.layout.filaofertas, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterOfertas.MyViewHolder holder, int position) {
        holder.filaNombre.setText(listaOferta.get(position).getNombre());
        holder.filaDescripcion.setText(listaOferta.get(position).getDescripcion());
        holder.filaPrecio.setText("S/."+listaOferta.get(position).getPrecio());
    }

    @Override
    public int getItemCount() {
        return listaOferta.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView filaNombre, filaDescripcion, filaPrecio;
        ImageButton filaComprar;

        public MyViewHolder(@NonNull View itemView) {

            super(itemView);

            filaNombre = itemView.findViewById(R.id.filaNombre);
            filaDescripcion = itemView.findViewById(R.id.filaDescripcion);
            filaPrecio = itemView.findViewById(R.id.filaPrecio);
            filaComprar = itemView.findViewById(R.id.filaComprar);
        }
    }
}
