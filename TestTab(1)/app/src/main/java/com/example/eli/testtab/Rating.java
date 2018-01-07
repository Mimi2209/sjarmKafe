package com.example.eli.testtab;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import java.sql.SQLException;

/**
 * Created by Eli on 27/12/2017.
 */


public class Rating extends Fragment {
    GlobalState gs;
    int idCafeteria;
    String resultat;
    TextView tNameCafe;
    RatingBar ratGlobal, ratLimpieza, ratRapidez, ratTrato, ratAmbiente, ratPrecios, ratDiseño, ratAccesibilidad, ratAparcar;
    Valoracion valoraciones;

    static Rating newInstance(int num) {
        Rating r = new Rating();
        return r;
    }

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
        tNameCafe.setText(gs.getNom_cafeteria());
        ratGlobal.setRating(gs.getRating_cafeteria());

        Descarga nuevaDescarga = new Descarga();
        nuevaDescarga.execute();

    }

    //---------------------------------------------------------------------------
    @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
    public class Descarga extends AsyncTask<String, Integer, String> {

        @Override
        protected void onPreExecute() {
            idCafeteria = gs.getId_cafeteria(); // recupero id cafeteria de la variable global

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

            if (valoraciones !=null){
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


