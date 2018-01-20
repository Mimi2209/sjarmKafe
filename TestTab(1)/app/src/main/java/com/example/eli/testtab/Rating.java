package com.example.eli.testtab;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;

import static android.app.Activity.RESULT_OK;


/**
 * Created by Eli on 27/12/2017.
 */


public class Rating extends Fragment {

    final int USER_REQUEST = 1, NEW_RAT = 2;
    GlobalState gs;
    int idCafeteria;
    String resultat;
    TextView tNameCafe;
    RatingBar ratGlobal, ratLimpieza, ratRapidez, ratTrato, ratAmbiente, ratPrecios, ratDiseño, ratAccesibilidad, ratAparcar;
    Valoracion valoraciones;
    FloatingActionButton add;
    ProgressDialog progreso;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        gs = (GlobalState) getActivity().getApplication();
        //       Toast.makeText(getActivity().getApplicationContext(), " Cafe "+gs.getNom_cafeteria(), Toast.LENGTH_SHORT).show();
        return inflater.inflate(R.layout.rating, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);

        tNameCafe = (TextView) getView().findViewById(R.id.cafe);
        ratGlobal = (RatingBar) getView().findViewById(R.id.rating);
        ratLimpieza = (RatingBar) getView().findViewById(R.id.ratingLimpieza);
        ratRapidez = (RatingBar) getView().findViewById(R.id.ratingRapidez);
        ratTrato = (RatingBar) getView().findViewById(R.id.ratingTrato);
        ratAmbiente = (RatingBar) getView().findViewById(R.id.ratingAmbiente);
        ratPrecios = (RatingBar) getView().findViewById(R.id.ratingPrecios);
        ratDiseño = (RatingBar) getView().findViewById(R.id.ratingDiseño);
        ratAccesibilidad = (RatingBar) getView().findViewById(R.id.ratingAccesib);
        ratAparcar = (RatingBar) getView().findViewById(R.id.ratingAparcar);
        add = (FloatingActionButton) getView().findViewById(R.id.addValoracion);
        tNameCafe.setText(gs.getNom_cafeteria());
        ratGlobal.setRating(gs.getRating_cafeteria());

        Descarga nuevaDescarga = new Descarga();
        nuevaDescarga.execute();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                if (gs.getId_usr()>0) {  // Si el usuario ya se ha identificado, no lo vuelve a pedir
                    intent = new Intent(getActivity(),
                            NewRatingActivity.class);
                    startActivityForResult(intent,  NEW_RAT);
                }else{
                    intent = new Intent(getActivity(),
                            UsuarioActivity.class);
                    startActivityForResult(intent, USER_REQUEST);
                }

            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == USER_REQUEST) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                //          String returnValue = data.getStringExtra("user");//no deberiamos pasar el usuario que la crea?
                Toast.makeText(getActivity(), "Hello " + gs.getNom_usr() + " your id is :" + gs.getId_usr()
                        , Toast.LENGTH_LONG).show();

                Intent intent = new Intent(getActivity(),
                        NewRatingActivity.class);
                startActivityForResult(intent, NEW_RAT);

            }

        } else {
            if (requestCode == NEW_RAT) {

                getActivity().finish();
            }
        }
    }

    //---------------------------------------------------------------------------
    @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
    public class Descarga extends AsyncTask<String, Integer, String> {

        @Override
        protected void onPreExecute() {
            idCafeteria = gs.getId_cafeteria(); // recupero id cafeteria de la variable global
            progreso = new ProgressDialog(getActivity());
            progreso.setTitle("Downloading...");
            progreso.setMessage("Please wait while downloading data for this Cafe");
            progreso.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progreso.setIndeterminate(false);
            progreso.setProgress(0);
            progreso.show();

        }

        @Override
        protected String doInBackground(String... urls) {

            try {
                GestionBBDD baseDatos = new GestionBBDD(); // conecta con servidor SQL
                valoraciones = baseDatos.verValoracion(idCafeteria); // obtiene cafeteria
            } catch (SQLException se) {
                System.out.println("oops! No se puede conectar. Error: " + se.toString());

            } finally {
                return resultat;
            }
        }

        @Override
        protected void onPostExecute(String result) {
            progreso.dismiss();
            if (valoraciones != null) {
                ratLimpieza.setRating(valoraciones.getLimpieza());
                ratRapidez.setRating(valoraciones.getRapidez_servicio());
                ratTrato.setRating(valoraciones.getTrato());
                ratAmbiente.setRating(valoraciones.getAmbiente());
                ratPrecios.setRating(valoraciones.getPrecios());
                ratDiseño.setRating(valoraciones.getDisenyo());
                ratAccesibilidad.setRating(valoraciones.getAccesibilidad());
                ratAparcar.setRating(valoraciones.getFacil_aparcar());
            }
        }
    }
}


