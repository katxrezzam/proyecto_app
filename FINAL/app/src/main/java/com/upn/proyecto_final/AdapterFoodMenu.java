package com.upn.proyecto_final;

import android.content.Context;
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

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.upn.proyecto_final.entidad.CarritoCompra;
import com.upn.proyecto_final.entidad.Oferta;
import com.upn.proyecto_final.entidad.Persona;
import com.upn.proyecto_final.repository.CarritoCompraRepository;
import com.upn.proyecto_final.repository.ClienteRepository;

import java.util.List;

public class AdapterFoodMenu extends RecyclerView.Adapter<AdapterFoodMenu.MyViewHolder> {

    private Context context;
    private List<Oferta> menulista;
    //private List<Persona> personas;
    Persona persona;
    //

    public AdapterFoodMenu(Context context, List<Oferta> menulista, Persona persona){
        this.context = context;
        this.menulista = menulista;
        this.persona = persona;
    }
    @NonNull
    @Override
    public AdapterFoodMenu.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(this.context);
        View view = inflater.inflate(R.layout.filamenu, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterFoodMenu.MyViewHolder holder, int position) {
        holder.nombre.setText(menulista.get(position).getNombre());
        holder.descripcion.setText(menulista.get(position).getDescripcion());
        holder.precio.setText("S/."+menulista.get(position).getPrecio());
        int id = menulista.get(position).getId();
        //String idCliente = personas.get(position).getId();

        holder.btnOrdenar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CarritoCompra carritoCompra = new CarritoCompra(persona.getId(), id, 1);
                CarritoCompraRepository repository = new CarritoCompraRepository(context);
                repository.openDB();

                String msj = repository.RegistarCarritoCompra(carritoCompra);

                Toast.makeText(context, msj,Toast.LENGTH_LONG).show();

            }
        });
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
