package com.upn.proyecto_final.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBase extends SQLiteOpenHelper {

    public DataBase(Context context){
        super(context,"final1.db",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =
                "CREATE TABLE cestacompras "+
                        " (idCliente TEXT NOT NULL, " +
                        " idOferta INTERGER NOT NULL, " +
                        " cantidad INTEGER NOT NULL);";
        db.execSQL(query);
        String query2 =
                "CREATE TABLE cliente "+
                        " (id TEXT PRIMARY KEY," +
                        " nombre TEXT NOT NULL, "+
                        " direccion TEXT NOT NULL, "+
                        " telefono TEXT NOT NULL, "+
                        " email TEXT NOT NULL, "+
                        " password TEXT NOT NULL);";
        db.execSQL(query2);
        String query3 =
                "CREATE TABLE oferta "+
                        " (id INTERGER PRIMARY KEY," +
                        " nombre TEXT NOT NULL, "+
                        " descripcion TEXT NOT NULL, "+
                        " precio DECIMAL NOT NULL);";

        db.execSQL(query3);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
