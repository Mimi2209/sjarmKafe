package com.example.eli.testtab;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class NewRatingActivity extends AppCompatActivity {

    Valoracion miVal;
    TextView tNameCafe;
    RatingBar ratGlobal, ratLimpieza, ratRapidez, ratTrato, ratAmbiente, ratPrecios, ratDiseño, ratAccesibilidad, ratAparcar;
    FloatingActionButton add;
    String resultat;
    EditText coment, comment_des;
    GlobalState gs;
    static Bitmap pict_cafeteria;
    static int idCafeteria;
    static int idUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_rating);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        gs = (GlobalState) getApplication();



        tNameCafe = (TextView) findViewById(R.id.cafe);
        ratGlobal = (RatingBar) findViewById(R.id.rating);
        ratLimpieza = (RatingBar) findViewById(R.id.ratingLimpieza);
        ratRapidez = (RatingBar) findViewById(R.id.ratingRapidez);
        ratTrato = (RatingBar) findViewById(R.id.ratingTrato);
        ratAmbiente = (RatingBar) findViewById(R.id.ratingAmbiente);
        ratPrecios = (RatingBar) findViewById(R.id.ratingPrecios);
        ratDiseño = (RatingBar) findViewById(R.id.ratingDiseño);
        ratAccesibilidad = (RatingBar) findViewById(R.id.ratingAccesib);
        ratAparcar = (RatingBar) findViewById(R.id.ratingAparcar);
        add = (FloatingActionButton) findViewById(R.id.addValoracion);
        coment =(EditText) findViewById(R.id.coment);
        comment_des = (EditText) findViewById(R.id.comment_descript);
        tNameCafe.setText(gs.getNom_cafeteria());
        idCafeteria=gs.getId_cafeteria();
        idUsuario=gs.getId_usr();
        pict_cafeteria=gs.getPict_cafeteria();
        ImageView image = (ImageView) findViewById(R.id.pic_cafeteria);
        image.setImageBitmap(pict_cafeteria);

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


                int valGlobal = (int) ratGlobal.getRating();
                int valLimpieza = (int) ratLimpieza.getRating();
                int valRapidez = (int) ratRapidez.getRating();
                int valTrato = (int) ratTrato.getRating();
                int valAmbiente = (int) ratAmbiente.getRating();
                int valPrecios = (int) ratPrecios.getRating();
                int valDiseño = (int) ratDiseño.getRating();
                int valAcces = (int) ratAccesibilidad.getRating();
                int valAparcar = (int) ratAparcar.getRating();
                String comment = coment.getText().toString();
                String descript = comment_des.getText().toString();
                Date data = Calendar.getInstance().getTime();
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                String formattedDate = df.format(Calendar.getInstance().getTime());
                miVal = new Valoracion(idUsuario,idCafeteria,valGlobal,valLimpieza,valRapidez,valTrato,valAmbiente,valPrecios,valDiseño,valAcces,valAparcar,comment,descript,formattedDate);

                baseDatos.insertValoracion(miVal); // obtiene valoracion

            } catch (SQLException se) {
                System.out.println("oops! No se puede conectar. Error: " + se.toString());
            } finally {

                //         estatus=tNameCafe.getText().toString()+"/"+tAddress.getText().toString()+"/"+ tDescrip.getText().toString()+2+"/"+ cTables.isChecked()+"/"+ cTerrace.isChecked()+"/"+rRating2.getNumStars() ;
                return resultat;
            }
        }

        @Override
        protected void onPostExecute(String result) {

            Toast.makeText(getApplicationContext(), " Valoracion Insertada correctamente !! ", Toast.LENGTH_SHORT).show();

            ratGlobal.setRating(Float.parseFloat(null));
            ratLimpieza.setRating(Float.parseFloat(null));
            ratRapidez.setRating(Float.parseFloat(null));
            ratTrato.setRating(Float.parseFloat(null));
            ratAmbiente.setRating(Float.parseFloat(null));
            ratPrecios.setRating(Float.parseFloat(null));
            ratDiseño.setRating(Float.parseFloat(null));
            ratAccesibilidad.setRating(Float.parseFloat(null));
            ratAparcar.setRating(Float.parseFloat(null));

        }
    }


}
