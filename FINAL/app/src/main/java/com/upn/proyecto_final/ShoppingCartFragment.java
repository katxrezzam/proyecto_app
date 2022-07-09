package com.upn.proyecto_final;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.telephony.RadioAccessSpecifier;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.upn.proyecto_final.entidad.CarritoCompra;
import com.upn.proyecto_final.entidad.Oferta;
import com.upn.proyecto_final.entidad.Persona;
import com.upn.proyecto_final.repository.CarritoCompraRepository;
import com.upn.proyecto_final.repository.ClienteRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ShoppingCartFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShoppingCartFragment extends Fragment {

    RecyclerView recyclerView;
    AdapterCarrito adapter;
    List<CarritoCompra> carritoCompras;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ShoppingCartFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ShoppingCartFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ShoppingCartFragment newInstance(String param1, String param2) {
        ShoppingCartFragment fragment = new ShoppingCartFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shopping_cart, container, false);

        CarritoCompraRepository repositoryCarrito = new CarritoCompraRepository(getContext());
        repositoryCarrito.openDB();
        ClienteRepository repositoryCliente = new ClienteRepository(getContext());
        repositoryCliente.AbrirDB();

        Persona persona = repositoryCliente.buscarClientePorEmail(Login.userLogged.getEmail());
        List<CarritoCompra> compras = repositoryCarrito.carritoByUser(persona.getId());


        recyclerView = view.findViewById(R.id.rvCarrito);
        adapter = new AdapterCarrito(getContext(),compras);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return  view;
    }
}