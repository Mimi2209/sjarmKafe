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
    int mNum;
    ListView cafes;
    ArrayList<Cafeteria>misCafeterias= new ArrayList<Cafeteria>();
    String resultat;


    static Listing newInstance(int num) {
        Listing l = new Listing();

        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putInt("num", num);
        l.setArguments(args);

        return l;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNum = getArguments() != null ? getArguments().getInt("num") : 1;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.listing, container, false);

        return view;
    }

    @Override
        public void onActivityCreated(Bundle state) {
            super.onActivityCreated(state);

            cafes = (ListView) getView().findViewById(R.id.cafeteria_list);
            Descarga nuevaDescarga = new Descarga();
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
                misCafeterias= baseDatos.verListCafeterias(1, 1); // obtiene cafeteria
            } catch (SQLException se) {
                System.out.println("oops! No se puede conectar. Error: " + se.toString());

            } finally {
                return resultat;
            }
        }

        @Override
        protected void onPostExecute(String result) {
            Cafeteria[] cafeterias = misCafeterias.toArray(new Cafeteria[misCafeterias.size()]);
            MyAdapter adapter = new MyAdapter(getActivity(), cafeterias,"cafe");
            cafes.setAdapter(adapter);

        }
    }
}


