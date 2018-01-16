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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class NewEventActivity extends AppCompatActivity {
    GlobalState gs;
    static int idCafeteria;
    static int idUsuario;
    static Bitmap pict_cafeteria;
    RatingBar ratGlobal;
    TextView tNameCafe;
    EditText eName, eDesc, eLocation;
    DatePicker eDateF,eDateT ;
    Date desde, hasta;
    TimePicker eTimeF,eTimeT;
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

        eDateF = (DatePicker) findViewById(R.id.fecha_in);
        eTimeF = (TimePicker) findViewById(R.id.hora_in);
        eDateT = (DatePicker) findViewById(R.id.fecha_fin);
        eTimeT = (TimePicker) findViewById(R.id.hora_fin);
        add = (FloatingActionButton) findViewById(R.id.insertEvent);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { // grabar
// valido datos
                int fechaF=eDateF.getYear()*10000+(eDateF.getMonth()+1)*100+eDateF.getDayOfMonth();
                int fechaT=eDateT.getYear()*10000+(eDateT.getMonth()+1)*100+eDateT.getDayOfMonth();
                DateFormat df = new SimpleDateFormat("yyyyMMdd");
                String now = df.format(Calendar.getInstance().getTime());
                int avui= Integer.parseInt(now);

                if (fechaF>fechaT || fechaF < avui || eName.getText().length()<1)
                {
                    Toast.makeText(getApplicationContext(), " check the following issues: field What is empty / date FROM have to be greather than  TODAY and less than TO", Toast.LENGTH_SHORT).show();
                          }else {
                    Descarga nuevaDescarga = new Descarga();
                    nuevaDescarga.execute();
                }
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



                String when = String.valueOf(eDateF.getDayOfMonth())+ "-"+String.valueOf(eDateF.getMonth()+1)+ "-"+String.valueOf(eDateF.getYear())
                + " "+String.valueOf(eTimeF.getCurrentHour())+":"+ String.valueOf(eTimeF.getCurrentMinute());

                String to =  String.valueOf(eDateT.getDayOfMonth())+ "-"+String.valueOf(eDateT.getMonth()+1)+ "-"+String.valueOf(eDateT.getYear())
                       +" "+ String.valueOf(eTimeT.getCurrentHour())+":"+ String.valueOf(eTimeT.getCurrentMinute()) ;
                miEvento = new Evento(idCafeteria,idUsuario,eName.getText().toString(),eDesc.getText().toString(),eLocation.getText().toString(),when,to);

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

            finish();
        }
    }
}
