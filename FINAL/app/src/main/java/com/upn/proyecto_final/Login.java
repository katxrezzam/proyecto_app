package com.upn.proyecto_final;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.upn.proyecto_final.entidad.Oferta;
import com.upn.proyecto_final.entidad.Persona;
import com.upn.proyecto_final.repository.OfertaRepository;

import java.util.ArrayList;
import java.util.List;

public class Login extends AppCompatActivity {



    public static List<Oferta> platos;

    EditText email, password;
    Button btnRegistrar, btnLogin;
    FirebaseAuth auth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    public static FirebaseUser userLogged;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        crearPlatos();
        openFireBase();
        auth = FirebaseAuth.getInstance();
        asignar();
        botonRegistrar();
        botonLogin();
    }
    private void openFireBase(){

        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        reference = firebaseDatabase.getReference();
    }


    private void botonRegistrar(){

        btnRegistrar.setOnClickListener(v -> {
            Intent intent = new Intent(Login.this, RegistrarActivity.class);
            startActivity(intent);
        });

    }
    private void botonLogin(){
        btnLogin.setOnClickListener(v -> {
            auth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    userLogged = auth.getCurrentUser();

                }
            });

            Intent intent = new Intent(Login.this, MainActivity.class);
            startActivity(intent);
        });
    }
    private void asignar(){

        btnRegistrar = findViewById(R.id.loginRegistar);
        btnLogin = findViewById(R.id.btnLogin);
        email = findViewById(R.id.loginEmail);
        password = findViewById(R.id.loginPassword);

    }
    private void crearPlatos(){
        platos = new ArrayList<>();
        //1
        String descripcion = "1 Pollo a la brasa + papas fritas+ ensalada + arroz chaufa + gaseosa de 1.5 Lt";
        Oferta oferta1 = new Oferta(1,"Combinado Familiar",descripcion, 50.0);
        //2
        descripcion = "1/2 pollo + papas fritas + ensalada + 2 gaseosas de 500 ml";
        Oferta oferta2 = new Oferta(2,"1/2 pollo + papas + ensalada + 2 gaseosas",descripcion,35.0);
        //3
        descripcion = "1/4 pollo + papas fritas + ensalada";
        Oferta oferta3 = new Oferta(3,"1/4 pollo + papas + ensalada",descripcion,35.0);
        //4
        descripcion = "1 Pollo + papas fritas + ensalada + gaseosa de 1.5Lt";
        Oferta oferta4 = new Oferta(4,"Pollo Oferta",descripcion,35.0);

        //5
        descripcion = "1 Pollo a la brasa + papas fritas+ ensalada + Limonada 2L+ salsas";
        Oferta oferta5 = new Oferta(5,"Combinado Limonada",descripcion, 69.0);
        //6
        descripcion = "1/2 pollo + papas fritas + ensalada + 2 gaseosas de 500 ml";
        Oferta oferta6 = new Oferta(6,"Promo para dos",descripcion,39.0);
        //7
        descripcion = "1/2 pollo + papas fritas + 2 palitos de anticucho + 1 salchicha + 1 Chorizo + pancita + ensalada";
        Oferta oferta7 = new Oferta(7,"Parrilla Mix",descripcion,59.0);
        //8
        descripcion = "8 piezas broaster + papas fritas + ensalda de col";
        Oferta oferta8 = new Oferta(8,"PROMO BROASTER X 8",descripcion,50.0);
        //9
        descripcion = "1/2 pollo + papas fritas + ensalada + 2 palitos de anticucho";
        Oferta oferta9 = new Oferta(9,"PIQUEOS PARRILLEROS",descripcion,50.0);

        OfertaRepository repository = new OfertaRepository(Login.this);
        repository.openDB();

        repository.registrarOferta(oferta1);
        repository.registrarOferta(oferta2);
        repository.registrarOferta(oferta3);
        repository.registrarOferta(oferta4);
        repository.registrarOferta(oferta5);
        repository.registrarOferta(oferta6);
        repository.registrarOferta(oferta7);
        repository.registrarOferta(oferta8);
        repository.registrarOferta(oferta9);

        //
        platos = repository.listar();
    }
}