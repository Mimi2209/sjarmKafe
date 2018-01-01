package com.example.eli.testtab;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            return inflater.inflate(R.layout.search, container, false);
        }
    }
    /* ----- carga arrayList Autores con contenido Select ----------------------
//    GestionBBDD baseDatos = new GestionBBDD();
    TextView Tnom, adreça, tCafe;
    String nom, resultat;
    String address;
    String descripcion;
    int tip_cafe;
    Boolean mesas;
    Boolean terraza;
    Boolean wifi;
    Boolean comida;
    Boolean tienda;
    Boolean perros;
    String horario;
    Boolean servicio_expres;
    Integer valoracion;
    Cafeteria cafeteria;
    Cafeteria miCafeteria=null;
    private static String Classe = "com.mysql.jdbc.Driver";
    private static String datosConexion="jdbc:mysql://e80760-mysql.services.easyname.eu/";
    private static String baseDatos = "u125322db1";
    private static String usuario = "u125322db1";
    private static String pass="Proyecto2018";
    private Connection con;
    Descarga nuevaDescarga; //async Task


    @Nullable
    @Override


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {




        return inflater.inflate(R.layout.search, container, false);
    }

    public void onActivityCreated(Bundle state) {

        View V = getView();
        super.onActivityCreated(state);

        Tnom = (TextView) getView().findViewById(R.id.nom_Cafeteria);
        adreça = (TextView) getView().findViewById(R.id.address_Cafeteria);
        tCafe = (TextView) getView().findViewById(R.id.tCafe_Cafeteria);
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
                miCafeteria=baseDatos.verCafeteria(3); // obtiene cafeteria
            } catch (SQLException se) {
                System.out.println("oops! No se puede conectar. Error: " + se.toString());
                resultat = "SQLex";
            } finally {
                return resultat;
            }
        }

        @Override
        protected void onPostExecute(String result) {
            Tnom.setText(miCafeteria.getNombre_cafeteria());
            adreça.setText(miCafeteria.getAddress());
            //     tv.setText(nom);
            //     localitzacio.setText(address);

        }
    }
}*/
