package com.upn.proyecto_final.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.upn.proyecto_final.database.DataBase;
import com.upn.proyecto_final.entidad.Persona;

public class ClienteRepository {

    DataBase dataBase;
    SQLiteDatabase sqLiteDatabase;
    Context context;

    public ClienteRepository(Context context) {
        this.context = context;
        this.dataBase = new DataBase(context);
    }

    public void AbrirDB(){
        sqLiteDatabase = dataBase.getWritableDatabase();

    }
    public String RegistrarCliente(Persona persona){
        String Mensaje = "";
        try{
            ContentValues contentValues = new ContentValues();
            contentValues.put("id",persona.getId());
            contentValues.put("nombre",persona.getNombre());
            contentValues.put("direccion",persona.getDireccion());
            contentValues.put("telefono",persona.getTelefono());
            contentValues.put("email",persona.getEmail());
            contentValues.put("password",persona.getPassword());

            long resultado = sqLiteDatabase.insert("cliente",null,contentValues);
            if(resultado == -1){
                Mensaje = "Error al Ingresar";
            }else{
                Mensaje = "Cliente registrado correctamente";
            }
        }catch (Exception e){

        }
        return Mensaje;
    }
    public Persona buscarClientePorEmail(String email){
        Persona persona= null;
        String[] args = new String[1];
        args[0] = email;

        try{
            Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM cliente WHERE email =?",args);
             while (c.moveToNext()){
                 persona = new Persona(c.getString(0),c.getString(1),
                         c.getString(2),c.getString(3),c.getString(4),c.getString(5));
             }
        }
        catch (Exception e){
        }
        return persona;
    }
}
