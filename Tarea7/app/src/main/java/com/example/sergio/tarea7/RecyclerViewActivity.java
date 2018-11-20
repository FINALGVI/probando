package com.example.sergio.tarea7;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends Activity {

    public List<Evento> eventos;
    public RecyclerView rv;
    public TextView txtNombre, txtDescripcion;
    public Button btnNuevoEvento;
    public SQLiteDatabase db, dbr;
    public LinearLayout linearMain;
    public CardView cv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.recycler);

        rv=(RecyclerView)findViewById(R.id.rv);
        btnNuevoEvento = findViewById(R.id.nuevoEvento);
        txtNombre = findViewById(R.id.nombreEvento);
        txtDescripcion = findViewById(R.id.eventoDescripcion);
        linearMain = findViewById(R.id.linearLayout);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);

        //db = con.getWritableDatabase();
        //dbr = con.getReadableDatabase();

        initializeData();
        initializeAdapter();

        btnNuevoEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent pa = new Intent("com.example.intent.registro");
                startActivity(pa);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        initializeData();
        initializeAdapter();
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

    void initializeAdapter(){
        EventoAdapter adapter = new EventoAdapter(eventos);
        rv.setAdapter(adapter);
    }
}



