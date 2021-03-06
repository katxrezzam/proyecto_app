package com.upn.proyecto_final;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.upn.proyecto_final.entidad.Oferta;
import com.upn.proyecto_final.entidad.Persona;
import com.upn.proyecto_final.repository.ClienteRepository;
import com.upn.proyecto_final.repository.OfertaRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FoodMenuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FoodMenuFragment extends Fragment {

    RecyclerView recyclerView;
    public static List<Oferta> menu;
    //List<Persona> personas;
    AdapterFoodMenu adapter;

    //
    FirebaseAuth auth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    FirebaseUser user;
    //

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FoodMenuFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FoodMenuFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FoodMenuFragment newInstance(String param1, String param2) {
        FoodMenuFragment fragment = new FoodMenuFragment();
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
        View view = inflater.inflate(R.layout.fragment_food_menu, container, false);


        ClienteRepository repository = new ClienteRepository(getContext());
        repository.AbrirDB();

        OfertaRepository repositoryOferta = new OfertaRepository(getContext());

        menu = new ArrayList<>();

        menu.add(repositoryOferta.buscarPorId(5));
        menu.add(repositoryOferta.buscarPorId(6));
        menu.add(repositoryOferta.buscarPorId(7));
        menu.add(repositoryOferta.buscarPorId(8));
        menu.add(repositoryOferta.buscarPorId(9));

        Persona persona = repository.buscarClientePorEmail(Login.userLogged.getEmail());

        recyclerView = view.findViewById(R.id.rvMenu);
        adapter = new AdapterFoodMenu(getContext(), Login.platos, persona);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));



        return  view;
    }

}