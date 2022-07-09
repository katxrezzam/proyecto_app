package com.upn.proyecto_final;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.common.api.Api;
import com.upn.proyecto_final.entidad.CarritoCompra;
import com.upn.proyecto_final.entidad.Oferta;
import com.upn.proyecto_final.entidad.Persona;
import com.upn.proyecto_final.repository.CarritoCompraRepository;
import com.upn.proyecto_final.repository.ClienteRepository;
import com.upn.proyecto_final.repository.OfertaRepository;

import java.util.ArrayList;
import java.util.List;

public class AdapterCarrito extends RecyclerView.Adapter<AdapterCarrito.MyViewHolder>{

    private Context context;
    private List<CarritoCompra> listaCarrito;
    private List<Oferta> ofertas;

    public AdapterCarrito(Context context, List<CarritoCompra> listaCarrito){
        this.context = context;
        this.listaCarrito = listaCarrito;
    }

    @NonNull
    @Override
    public AdapterCarrito.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(this.context);

        View view = inflater.inflate(R.layout.filashopping_cart, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterCarrito.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.nombre.setText(ofertas.get(position).getNombre());
        holder.precio.setText("S/."+ofertas.get(position).getPrecio());
        holder.descripcion.setText(ofertas.get(position).getDescripcion());

        holder.eliminar.setOnClickListener(v -> {
            AlertDialog.Builder ventana = new AlertDialog.Builder(context);
            ventana.setTitle("Confirmacion");
            ventana.setMessage("¿Desea eliminar el plato de la cesta?");
            ventana.setNegativeButton("No",null);
            ventana.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    CarritoCompraRepository repositoryCarrito = new CarritoCompraRepository(context);
                    repositoryCarrito.openDB();

                    ClienteRepository clienteRepository = new ClienteRepository(context);
                    clienteRepository.AbrirDB();

                    Persona persona = clienteRepository.buscarClientePorEmail(Login.userLogged.getEmail());

                    String msj = repositoryCarrito.eliminar(ofertas.get(position).getId(),persona.getId());
                    ofertas.remove(position);
                    AlertDialog.Builder v2 = new AlertDialog.Builder(context);
                    v2.setMessage(msj);
                    v2.setPositiveButton("ok",null);
                    v2.create().show();
                }
            });
            ventana.create().show();
        });
    }

    @Override
    public int getItemCount() {
        return listaCarrito.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView nombre, descripcion, precio;
        ImageButton eliminar;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            nombre = itemView.findViewById(R.id.carritoNombre);
            descripcion = itemView.findViewById(R.id.carritoDescripcion);
            precio = itemView.findViewById(R.id.carritoPrecio);
            nombre = itemView.findViewById(R.id.carritoNombre);
            eliminar = itemView.findViewById(R.id.filaEliminar);

            OfertaRepository repository= new OfertaRepository(context);
            repository.openDB();
            ofertas = new ArrayList<>();
            for(int i = 0 ; i < listaCarrito.size(); i ++){
                Oferta oferta = repository.buscarPorId(listaCarrito.get(i).getIdOferta());
                ofertas.add(oferta);
            }
        }

    }
}
