package com.example.eli.testtab;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Array;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by Eli on 27/12/2017.
 */


public class Listing extends Fragment {
    ListView cafes;
    ArrayList<Cafeteria> misCafeterias = new ArrayList<Cafeteria>();
    ArrayList<Cafeteria> misCafeteriasBak = new ArrayList<Cafeteria>();
    String resultat;
    GlobalState gs;
    String sql;
    boolean sql_search;
    MyAdapter adapter;
 //-------------------------------------------------------------------------
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.listing, container, false);
    }

    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);
        gs = (GlobalState) getActivity().getApplication();
        cafes = (ListView) getView().findViewById(R.id.cafeteria_list);
        Descarga_listing nuevaDescarga_listing = new Descarga_listing();
        nuevaDescarga_listing.execute();

    }

    //---------------------------------------------------------------------------
    public class Descarga_listing extends AsyncTask<String, Integer, String> {

        @Override
        protected void onPreExecute() {
            Toast.makeText(getActivity(), "Loading, please wait", Toast.LENGTH_SHORT).show();

        }

        @Override
        protected String doInBackground(String... urls) {

            try {
                GestionBBDD baseDatos = new GestionBBDD(); // conecta con servidor SQL

                    misCafeterias = baseDatos.verListCafeterias(gs.getLatitut(), gs.getLongitut()); // obtiene cafeteria


            } catch (SQLException se) {
                System.out.println("oops! No se puede conectar. Error: " + se.toString());

            } finally {
                return resultat;
            }
        }

        @Override
        protected void onPostExecute(String result) {
             if (misCafeterias.size() == 0 || misCafeterias==null) {
                if(misCafeteriasBak.size()> 1){
                    misCafeterias=misCafeteriasBak;
                }else {
                    Bitmap foto = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888);
                    misCafeterias.add(new Cafeteria("ERROR CONEXION a BBDD, vuelva a intentarlo mas tarde", "", "", 1, 1, 1, true, false, true, true, false, false, "17", true, 4, foto));
                }
                }else {
                misCafeteriasBak = misCafeterias;
            }
            adapter = new MyAdapter(getActivity(), misCafeterias, "cafe", "");
            cafes.setAdapter(adapter);
            // carga de solo array list

            Toast.makeText(getActivity(), sql, Toast.LENGTH_SHORT).show();
        }
    }

}


