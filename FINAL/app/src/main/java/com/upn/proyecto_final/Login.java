package com.upn.proyecto_final;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Login extends AppCompatActivity {

    EditText email, password;
    Button btnRegistrar, btnLogin;
    FirebaseAuth auth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

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
                    user = auth.getCurrentUser();
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
}