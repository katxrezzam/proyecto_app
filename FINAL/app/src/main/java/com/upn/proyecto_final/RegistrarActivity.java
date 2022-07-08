package com.upn.proyecto_final;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.upn.proyecto_final.entidad.Persona;

import java.util.UUID;

public class RegistrarActivity extends AppCompatActivity {

    EditText nombre, direccion, telefono, email, password;
    TextView lblLogin;
    Button btnRegistrar;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    Persona persona;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        openFireBase();
        auth = FirebaseAuth.getInstance();
        referenciar();
        registrarPersona();
        loginlbl();
    }

    private void openFireBase(){

        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        reference = firebaseDatabase.getReference();
    }

    private void registrarPersona(){
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                persona = new Persona(UUID.randomUUID().toString(),nombre.getText().toString(),direccion.getText().toString(), telefono.getText().toString(), email.getText().toString(), password.getText().toString());
                auth.createUserWithEmailAndPassword(persona.getEmail(), persona.getPassword());
                reference.child("Persona").child(persona.getId()).setValue(persona);

            }
        });
    }
    private void loginlbl(){
        lblLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistrarActivity.this, Login.class);
                startActivity(intent);
            }
        });
    }

    private void referenciar(){

        nombre = findViewById(R.id.registrarNombre);
        direccion = findViewById(R.id.registrarDireccion);
        telefono = findViewById(R.id.registrarTelefono);
        email = findViewById(R.id.registrarEmail);
        password = findViewById(R.id.registrarPassword);
        lblLogin = findViewById(R.id.lblLogin);
        btnRegistrar = findViewById(R.id.btnRegistrar);



    }
}