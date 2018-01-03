package com.example.eli.testtab;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import java.sql.SQLException;

/**
 * Created by Eli on 27/12/2017.
 */

    public class Add extends Fragment  {


    Cafeteria miCafeteria;
    String resultat;
    CheckBox cTerrace, cTables, cWifi, cShop, cMeals, cXpress, cDogs;
    EditText tNameCafe, tAddress, tHorario, tDescrip ;
    RatingBar rRating2;
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            FloatingActionButton fab = (FloatingActionButton) getActivity().findViewById(R.id.addCafeteria);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Add.Descarga nuevaDescarga = new Add.Descarga();
                    nuevaDescarga.execute();
                }
            });
            return inflater.inflate(R.layout.add, container, false);
        }


            @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);

        tNameCafe = (EditText) getView().findViewById(R.id.cafe);
        tAddress = (EditText) getView().findViewById(R.id.cafe_address);
        tHorario = (EditText) getView().findViewById(R.id.horario);
        tDescrip = (EditText) getView().findViewById(R.id.cafe_descript);
        cTerrace = (CheckBox) getView().findViewById(R.id.Terrace);
        cTables = (CheckBox) getView().findViewById(R.id.Tables);
        cWifi = (CheckBox) getView().findViewById(R.id.Wifi);
        cShop = (CheckBox) getView().findViewById(R.id.shop);
        cMeals = (CheckBox) getView().findViewById(R.id.Meals);
        cXpress = (CheckBox) getView().findViewById(R.id.Xpress);
        cDogs = (CheckBox) getView().findViewById(R.id.Dogs);
        rRating2 = (RatingBar) getView().findViewById(R.id.rating2);

      // executar amb el boto de +
        //  Add.Descarga nuevaDescarga = new Add.Descarga();
      //  nuevaDescarga.execute();

    }
    //---------------------------------------------------------------------------
    public class Descarga extends AsyncTask<String, Integer, String> {

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected String doInBackground(String... urls) {

            try {
                GestionBBDD baseDatos = new GestionBBDD(); // conecta con servidor SQL
// DATOS QUE FALTAN !!!! //
                // falta tipo de cafe
                // falta long/ltg
                // Falta captura foto
                float latitut=1;
                float longitut=1;
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.tacita); // eliminar cuando activemos camara
                miCafeteria= new Cafeteria(tNameCafe.getText().toString(),tAddress.getText().toString(), tDescrip.getText().toString(),2, longitut, latitut,cTables.isChecked(), cTerrace.isChecked(),cWifi.isChecked(),cMeals.isChecked(),cShop.isChecked(),cDogs.isChecked(),tHorario.getText().toString(), cXpress.isChecked(),rRating2.getNumStars() ,bitmap);
                 baseDatos.insertCafeteria(miCafeteria); // obtiene cafeteria
            } catch (SQLException se) {
                System.out.println("oops! No se puede conectar. Error: " + se.toString());

            } finally {
                return resultat;
            }
        }

        @Override
        protected void onPostExecute(String result) {

        }
    }
    }
