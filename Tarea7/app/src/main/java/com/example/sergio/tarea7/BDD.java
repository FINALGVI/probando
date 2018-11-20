package com.example.sergio.tarea7;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BDD extends SQLiteOpenHelper {

    public String crearTabla = "Create table Eventos(idEvento integer Primary Key autoincrement, nombreEvento text, eventoFecha text, eventoHora text, eventoDescripcion text)";

    public BDD(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(crearTabla);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE if exists Eventos");
        db.execSQL(crearTabla);

    }
}
