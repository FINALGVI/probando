package com.example.sergio.tarea7;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class registro extends Activity {

    public TextView txtNuevoEvento, txtFecha, txtNombre, txtFechaDisplay, txtDescripcion, txtHora, txtHoraDisplay;
    public EditText editNombre, editDescripcion;
    public Button btnFecha, btnAceptar, btnHora;
    public ImageView imgHeader;
    public CardView cv0, cv1, cv2, cv3, cv4;
    public SQLiteDatabase db;
    public int dia, mes, año,  hora, minutos;
    public String fechadef, horadef;
    public List<Evento> eventos;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro);

        txtDescripcion = findViewById(R.id.eventoDescripcion);
        txtFecha = findViewById(R.id.agregarFecha);
        txtFechaDisplay = findViewById(R.id.txtFechaDisplay);
        txtNombre = findViewById(R.id.nombreEvento);
        txtNuevoEvento = findViewById(R.id.nuevoEvento);
        txtHora = findViewById(R.id.txtHora);
        txtHoraDisplay = findViewById(R.id.txtHoraDisplay);
        editNombre = findViewById(R.id.editNombre);
        editDescripcion = findViewById(R.id.editDescripcion);
        btnAceptar = findViewById(R.id.aceptarEvento);
        btnFecha = findViewById(R.id.agregarFecha);
        btnHora = findViewById(R.id.agregarHora);
        imgHeader = findViewById(R.id.imagenEvento);
        cv0 = findViewById(R.id.cv);
        cv1 = findViewById(R.id.cv2);
        cv2 = findViewById(R.id.cv3);
        cv3 = findViewById(R.id.cv4);
        cv4 = findViewById(R.id.cv5);


        BDD con = new BDD(this.getApplicationContext(), "Eventos", null, 5);
        db = con.getWritableDatabase();

        btnFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c= Calendar.getInstance();
                dia=c.get(Calendar.DAY_OF_MONTH);
                mes=c.get(Calendar.MONTH);
                año=c.get(Calendar.YEAR);

                DatePickerDialog datePickerDialog = new DatePickerDialog(registro.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        txtFechaDisplay.setText(dayOfMonth+"/"+(monthOfYear+1)+"/"+year);
                    }
                }
                        ,año, mes, dia);
                datePickerDialog.show();
                fechadef = Integer.toString(dia) + "/" + Integer.toString(mes) + "/" + Integer.toString(año);
            }
        });

        btnHora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c2= Calendar.getInstance();
                hora=c2.get(Calendar.HOUR_OF_DAY);
                minutos=c2.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(registro.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        txtHoraDisplay.setText(hourOfDay+":"+minute);
                    }
                },hora,minutos,false);
                timePickerDialog.show();
                horadef = Integer.toString(hora) + ":" + Integer.toString(minutos);
            }
        });

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.execSQL("INSERT INTO Eventos(nombreEvento, eventoFecha, eventoHora, eventoDescripcion) VALUES ('" + editNombre.getText().toString() +"', '" + fechadef + "', '" + horadef + "', '" + editDescripcion.getText().toString() + "')");
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


}
