package com.example.sergio.tarea7;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;



public class MainActivity extends Activity {

    Button nuevoEvento;
    TextView eventoNombre, eventoDescripcion;
    ImageView fotoEvento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nuevoEvento = findViewById(R.id.nuevoEvento);
        eventoNombre = findViewById(R.id.nombreEvento);
        eventoDescripcion = findViewById(R.id.eventoDescripcion);
        fotoEvento = findViewById(R.id.imagenEvento);

        eventoNombre.setText("Emma Wilson");
        eventoDescripcion.setText("23 years old");

    }
}
