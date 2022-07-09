package com.upn.proyecto_final.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.upn.proyecto_final.database.DataBase;
import com.upn.proyecto_final.entidad.CarritoCompra;
import com.upn.proyecto_final.entidad.Persona;

import java.security.spec.ECField;
import java.util.ArrayList;
import java.util.List;

public class CarritoCompraRepository {

    DataBase dataBase;
    SQLiteDatabase sqLiteDatabase;
    Context context;

    public CarritoCompraRepository(Context context){
        this.context = context;
        this.dataBase = new DataBase(context);
    }
    public void openDB(){
        sqLiteDatabase = dataBase.getWritableDatabase();
    }
    public String eliminar(int id_oferta, String id_cliente){
        String msj = "";

        String[] args = new String[2];

        args[0] = Integer.toString(id_oferta);
        args[1] = id_cliente;

        try{
            long resu = sqLiteDatabase.delete("cestacompras","idoferta=? and idcliente=?",args);
            if(resu == -1){
                msj = "Error al eliminar";
            }else{
                msj = "Producto eliminado del carrito de compras";
            }
        }
        catch (Exception e){

        }
        return  msj;
    }
    public String RegistarCarritoCompra(CarritoCompra carritoCompra){

        String Mensaje = "";
        try{
            ContentValues contentValues = new ContentValues();

            contentValues.put("cantidad",carritoCompra.getCantidad());
            contentValues.put("idOferta",carritoCompra.getIdOferta());
            contentValues.put("idCliente",carritoCompra.getIdCliente());

            long resultado = sqLiteDatabase.insert("cestacompras",null,contentValues);
            if(resultado == -1){
                Mensaje = "Error al Ingresar";
            }else{
                Mensaje = "producto añadido correctamente al carrito de compras";
            }
        }catch (Exception e){

        }
        return Mensaje;
    }
    public List<CarritoCompra> carritoByUser(String id_user){

        List<CarritoCompra> compras = new ArrayList<>();

        String[] args = new String[1];
        args[0] = id_user;

        try{
            Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM cestacompras WHERE idCliente =?",args);
            while (c.moveToNext()){
                CarritoCompra cesta = new CarritoCompra(c.getString(0),c.getInt(1),1);
                compras.add(cesta);
            }
        }
        catch (Exception e){
        }

        return  compras;
    }
    public int cantidadCarrito(String id_user){
        String[] args = new String[1];
        args[0] = id_user;
        int result = 0;
        Cursor c=null;
        try{
            c = sqLiteDatabase.rawQuery("SELECT * FROM cestacompras WHERE idCliente =?",args);
            result = c.getColumnCount();
            if(result < 0){
                result = 0;
            }
        }finally {
            if (c!= null)
                c.close();
        }
        return result;
    }
}
