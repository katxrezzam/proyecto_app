package com.upn.proyecto_final;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.upn.proyecto_final.entidad.Oferta;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StartMenuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StartMenuFragment extends Fragment {

    RecyclerView recyclerView;
    List<Oferta> ofertas;

    AdapterOfertas adapterOfertas;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public StartMenuFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StartMenuFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StartMenuFragment newInstance(String param1, String param2) {
        StartMenuFragment fragment = new StartMenuFragment();
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
        View view = inflater.inflate(R.layout.fragment_start_menu, container, false);
        crearOfertas();
        recyclerView = view.findViewById(R.id.rvOfertas);
        adapterOfertas = new AdapterOfertas(getContext(),ofertas);
        recyclerView.setAdapter(adapterOfertas);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return  view;
    }

    private void crearOfertas(){

        ofertas = new ArrayList<>();

        String descripcion = "1 Pollo a la brasa + papas fritas+ ensalada + arroz chaufa + gaseosa de 1.5 Lt";

        Oferta oferta1 = new Oferta(1L,"Combinado Familiar",descripcion, 50.0);

        ofertas.add(oferta1);

        descripcion = "1/2 pollo + papas fritas + ensalada + 2 gaseosas de 500 ml";

        Oferta oferta2 = new Oferta(2L,"1/2 pollo + papas + ensalada + 2 gaseosas",descripcion,35.0);

        ofertas.add(oferta2);

        descripcion = "1/4 pollo + papas fritas + ensalada";

        Oferta oferta3 = new Oferta(3L,"1/4 pollo + papas + ensalada",descripcion,35.0);

        ofertas.add(oferta3);

        descripcion = "1 Pollo + papas fritas + ensalada + gaseosa de 1.5Lt";

        Oferta oferta4 = new Oferta(4L,"Pollo Oferta",descripcion,35.0);

        ofertas.add(oferta4);
    }
}