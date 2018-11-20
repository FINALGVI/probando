package com.example.sergio.tarea7;

import java.util.ArrayList;
import java.util.List;

public class Evento {
    int eventoId;
    String eventoNombre, descripcionEvento;

    public Evento (String eventoNombre, String descripcionEvento) {
        this.eventoNombre = eventoNombre;
        this.descripcionEvento = descripcionEvento;
    }

    public String getEventoNombre() {
        return eventoNombre;
    }

    public void setEventoNombre(String eventoNombre) {
        this.eventoNombre = eventoNombre;
    }

    public String getDescripcionEvento() {
        return descripcionEvento;
    }

    public void setDescripcionEvento(String descripcionEvento) {
        this.descripcionEvento = descripcionEvento;
    }
}


