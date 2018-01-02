package com.example.eli.testtab;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

    /**
     * Created by Eli on 27/12/2017.
     */

    public class Search extends Fragment {
        ArrayList<Cafeteria> mis_cafeterias = new ArrayList<Cafeteria>();
        String  resultat;
        CheckBox terraza,wifi, shop;
        Descarga nuevaDescarga; //async Task
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            return inflater.inflate(R.layout.search, container, false);
        }
        public void onActivityCreated(Bundle state) {

            View V = getView();
            super.onActivityCreated(state);

            terraza = (CheckBox) getView().findViewById(R.id.Terrace);
            wifi = (CheckBox) getView().findViewById(R.id.Wifi);
            shop = (CheckBox) getView().findViewById(R.id.shop);

            nuevaDescarga = new Descarga();
            nuevaDescarga.execute();

        }

        public class Descarga extends AsyncTask<String, Integer, String> {

            @Override
            protected void onPreExecute() {

            }

            @Override
            protected String doInBackground(String... urls) {

                try {
                    GestionBBDD baseDatos = new GestionBBDD(); // conecta con servidor SQL
                    mis_cafeterias=baseDatos.verListCafeterias((float) 1.00000011, (float) 1.00000000); // listado cafeterias proximas
                } catch (SQLException se) {
                    System.out.println("oops! No se puede conectar. Error: " + se.toString());
                    resultat = "SQLex";
                } finally {
                    return resultat;
                }
            }

            @Override
            protected void onPostExecute(String result) {
                String sDistancia = String.valueOf(mis_cafeterias.get(1).getDistancia());
                terraza.setChecked(mis_cafeterias.get(1).isTerraza());
                wifi.setChecked(mis_cafeterias.get(3).isWifi());
                // para ver que distancia ha calculado...
                shop.setChecked(mis_cafeterias.get(3).isTienda());

                // imatge.setImageBitmap(miCafeteria.getImg());

            }
        }
    }

