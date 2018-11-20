package com.example.sergio.tarea7;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import static android.support.v4.content.ContextCompat.startActivity;

public class EventoAdapter extends RecyclerView.Adapter<EventoAdapter.EventoViewHolder> {

    public String agarrado;

    public static class EventoViewHolder extends RecyclerView.ViewHolder {

        public CardView cv;
        public View mView;
        public List<Evento> eventos;
        public TextView txtEvento;
        public TextView txtDescripcion;
        private RecyclerView rv;
        public int position;

        EventoViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            txtEvento = (TextView)itemView.findViewById(R.id.nombreEvento);
            txtDescripcion = (TextView)itemView.findViewById(R.id.eventoDescripcion);
            rv = itemView.findViewById((R.id.rv));
            mView = itemView;
        }
    }

    List<Evento> eventos;

    EventoAdapter(List<Evento> eventos){
        this.eventos = eventos;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public EventoViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview, viewGroup, false);
        EventoViewHolder evh = new EventoViewHolder(v);

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = (int) v.getTag();
                Toast.makeText(v.getContext(),"Este es el evento número " + Integer.toString(position+1),Toast.LENGTH_SHORT).show();
                Intent pe = new Intent("com.example.intent.prueba");
                Bundle var = new Bundle();
                var.putString("nombre", String.valueOf(eventos.get(position).eventoNombre));
                pe.putExtras(var);
                v.getContext().startActivity(pe);
            }
        });
        return evh;
    }

    @Override
    public void onBindViewHolder(EventoViewHolder eventoViewHolder, int i) {
        eventoViewHolder.txtEvento.setText(eventos.get(i).eventoNombre);
        eventoViewHolder.txtDescripcion.setText(eventos.get(i).descripcionEvento);
        eventoViewHolder.cv.setTag(i);
    }

    @Override
    public int getItemCount() {
        return eventos.size();
    }
}