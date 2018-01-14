package com.example.eli.testtab;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class NewEventActivity extends AppCompatActivity {
    GlobalState gs;
    static int idCafeteria;
    static int idUsuario;
    static Bitmap pict_cafeteria;
    RatingBar ratGlobal;
    TextView tNameCafe;
    EditText eName, eDesc, eLocation;
    DatePicker eDate;
    TimePicker eTime;
    FloatingActionButton add;
    Evento miEvento;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_event);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        gs = (GlobalState) getApplication();
        tNameCafe = (TextView) findViewById(R.id.cafe);
        ratGlobal = (RatingBar) findViewById(R.id.rating);
        tNameCafe.setText(gs.getNom_cafeteria());
        idCafeteria=gs.getId_cafeteria();
        idUsuario=gs.getId_usr();
        ratGlobal.setRating(gs.getRating_cafeteria());
        pict_cafeteria=gs.getPict_cafeteria();
        ImageView image = (ImageView) findViewById(R.id.pic_cafeteria);
        image.setImageBitmap(pict_cafeteria);
        eName = (EditText) findViewById(R.id.event_name);
        eDesc = (EditText) findViewById(R.id.event_description);
        eLocation = (EditText) findViewById(R.id.event_location);
        eDate = (DatePicker) findViewById(R.id.fecha);
        eTime = (TimePicker) findViewById(R.id.hora);
        add = (FloatingActionButton) findViewById(R.id.addValoracion);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { // grabar
                Descarga nuevaDescarga = new Descarga();
                nuevaDescarga.execute();
            }
        });
    }
    //---------------------------------------------------------------------------
    public class Descarga extends AsyncTask<String, Integer, String> {

        //  String user = getIntent().getStringExtra("user");



        @Override
        protected void onPreExecute() {


        }

        @Override
        protected String doInBackground(String... tipo) {

            try {
                GestionBBDD baseDatos = new GestionBBDD(); // conecta con servidor SQL
// DATOS QUE FALTAN !!!! // usuario y cafeteria



                String when = "From : "+String.valueOf(eDate.getDayOfMonth())+ "-"+String.valueOf(eDate.getMonth())+ "-"+String.valueOf(eDate.getYear())
                +" at "+ String.valueOf(eTime.getCurrentHour())+":"+ String.valueOf(eTime.getCurrentMinute());

                String to =  " to : " +String.valueOf(eDate.getDayOfMonth())+ "-"+String.valueOf(eDate.getMonth())+ "-"+String.valueOf(eDate.getYear())
                        +" at "+ String.valueOf(eTime.getCurrentHour())+":"+ String.valueOf(eTime.getCurrentMinute()) ;
                miEvento = new Evento(idUsuario,idCafeteria,eName.getText().toString(),eDesc.getText().toString(),eLocation.getText().toString(),when,to);

                baseDatos.insertEvento(miEvento); // obtiene valoracion

            } catch (SQLException se) {
                System.out.println("oops! No se puede conectar. Error: " + se.toString());
            } finally {

                //         estatus=tNameCafe.getText().toString()+"/"+tAddress.getText().toString()+"/"+ tDescrip.getText().toString()+2+"/"+ cTables.isChecked()+"/"+ cTerrace.isChecked()+"/"+rRating2.getNumStars() ;
                return "";
            }
        }

        @Override
        protected void onPostExecute(String result) {

            Toast.makeText(getApplicationContext(), " Evento Added fine !! ", Toast.LENGTH_SHORT).show();


        }
    }
}
