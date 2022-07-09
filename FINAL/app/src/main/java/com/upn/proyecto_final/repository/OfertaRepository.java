package com.upn.proyecto_final.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.upn.proyecto_final.database.DataBase;
import com.upn.proyecto_final.entidad.Oferta;
import com.upn.proyecto_final.entidad.Persona;

import java.util.ArrayList;
import java.util.List;

public class OfertaRepository {

    DataBase dataBase;
    SQLiteDatabase sqLiteDatabase;
    Context context;

    public OfertaRepository(Context context){
        this.context = context;
        this.dataBase = new DataBase(context);
    }
    public void openDB(){
        sqLiteDatabase = dataBase.getWritableDatabase();
    }
    public void registrarOferta(Oferta oferta){
        String Mensaje = "";
        try{
            ContentValues contentValues = new ContentValues();

            contentValues.put("id",oferta.getId());
            contentValues.put("nombre",oferta.getNombre());
            contentValues.put("descripcion",oferta.getDescripcion());
            contentValues.put("precio",oferta.getPrecio());

            long resultado = sqLiteDatabase.insert("oferta",null,contentValues);
            if(resultado == -1){
                Mensaje = "Error al Ingresar";
            }else{
                Mensaje = "producto añadido correctamente al carrito de compras";
            }
        }catch (Exception e){

        }
    }
    public Oferta buscarPorId(int id){

        Oferta oferta = null;
        String[] args = new String[1];
        args[0] = Integer.toString(id);

        try{
            Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM oferta WHERE id =?",args);
            while (c.moveToNext()){
                oferta = new Oferta(c.getInt(0)
                        , c.getString(1)
                        , c.getString(2)
                        , c.getDouble(3));
            }
        }
        catch (Exception e){
        }

        return  oferta;
    }
        public List<Oferta> listar(){

            List<Oferta> ofertas = new ArrayList<>();

        try{
            Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM oferta",null);
            while (c.moveToNext()){
                Oferta oferta = new Oferta(c.getInt(0),
                        c.getString(1),
                        c.getString(2),
                        c.getDouble(3));
                ofertas.add(oferta);
            }
        }
        catch (Exception e){
        }

        return  ofertas;
    }

}
