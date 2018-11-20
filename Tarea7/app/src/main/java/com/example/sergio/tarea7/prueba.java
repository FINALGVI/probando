package com.example.sergio.tarea7;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class prueba extends Activity {

    public TextView txtTitulo, FechaP, HoraP, tituloDescripcion, contentDescripcion;
    public CardView cvs, cvs2, cvs3, cvs4;
    public ImageView imgTitulo;
    public SQLiteDatabase db;
    public Button btnEditar, btnBorrar;
    public String convertir, mandarTitulo, mandarFecha, mandarHora, mandarDescripcion;
    public List<Evento> eventos;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prueba);

        txtTitulo = findViewById(R.id.txtTitulo);
        FechaP = findViewById(R.id.FechaP);
        HoraP = findViewById(R.id.HoraP);
        tituloDescripcion = findViewById(R.id.tituloDescripcion);
        contentDescripcion = findViewById(R.id.contentDescripcion);
        cvs = findViewById(R.id.cvs);
        cvs2 = findViewById(R.id.cvs2);
        cvs3 = findViewById(R.id.cvs3);
        cvs4 = findViewById(R.id.cvs4);
        imgTitulo = findViewById(R.id.imagenEvento);
        btnEditar = findViewById(R.id.btnEditar);
        btnBorrar = findViewById(R.id.btnEliminar);


        Bundle var1 = getIntent().getExtras();
        txtTitulo.setText(var1.getString("nombre"));
        convertir = var1.getString("nombre");

        BDD con = new BDD(getApplicationContext(), "Eventos", null, 5);
        db = con.getWritableDatabase();

        Cursor c = db.rawQuery("SELECT  nombreEvento, eventoFecha, eventoHora, eventoDescripcion FROM Eventos WHERE nombreEvento = '" + convertir + "'", null);

        while (c.moveToNext()) {
            txtTitulo.setText(c.getString(0));
            mandarTitulo = c.getString(0);
            FechaP.setText(c.getString(1));
            mandarFecha = c.getString(1);
            HoraP.setText(c.getString(2));
            mandarHora = c.getString(2);
            contentDescripcion.setText(c.getString(3));
            mandarDescripcion = c.getString(3);
        }

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pa = new Intent("com.example.intent.editar");
                Bundle mandalo = new Bundle();
                mandalo.putString("tituloEvento", mandarTitulo);
                mandalo.putString("fechaEvento", mandarFecha);
                mandalo.putString("horaEvento", mandarHora);
                mandalo.putString("contenido", mandarDescripcion);
                mandalo.putString("referencia", convertir);
                pa.putExtras(mandalo);
                startActivity(pa);
            }
        });

        btnBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                db.execSQL("DELETE FROM eventos WHERE nombreEvento = '" + convertir + "'");
                initializeData();
                finish();
            }
        });
    }

    void initializeData(){
        BDD con = new BDD(getApplicationContext(), "Eventos", null, 5);
        db = con.getWritableDatabase();
        //dbr = con.getReadableDatabase();
        eventos = new ArrayList<>();
        Cursor c = db.rawQuery("SELECT  nombreEvento, eventoDescripcion FROM Eventos", null);

        while (c.moveToNext())
        {
            eventos.add(new Evento(c.getString(0), c.getString(1)));
        }
        db.close();
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}
