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
    int mNum;
    ListView cafes;
    ArrayList<Cafeteria> misCafeterias = new ArrayList<Cafeteria>();
    String resultat;
    GlobalState gs;
    String sql;
    boolean sql_search;

  //  static Listing newInstance(int num) {
  //      Listing l = new Listing();

        // Supply num input as an argument.
    //    Bundle args = new Bundle();
    //    args.putInt("num", num);
    //    l.setArguments(args);

      //  return l;
   // }

//    @Override
 //   public void onCreate(Bundle savedInstanceState) {
  //      super.onCreate(savedInstanceState);
  //      mNum = getArguments() != null ? getArguments().getInt("num") : 1;
  //  }

//----------------------------------------------------------------------------


    @Override
    public void onStart() {
        super.onStart();
        if (this.isVisible()) {
            gs = (GlobalState) getActivity().getApplication();
            sql = gs.getSql_search();
            Toast.makeText(getActivity(), sql, Toast.LENGTH_SHORT).show();

            Descarga nuevaDescarga = new Descarga();
            if (sql.length() > 10) {
                sql_search = true;
            }
            nuevaDescarga.execute(sql);
        }
    }
//-------------------------------------------------------------------------
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.listing, container, false);
    }

    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);

        cafes = (ListView) getView().findViewById(R.id.cafeteria_list);

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
                if (sql_search) {
                    misCafeterias = baseDatos.verListCafeterias(urls[0]);
                    gs.setSql_search("");
                } else {
                    misCafeterias = baseDatos.verListCafeterias(1, 1); // obtiene cafeteria
                }

            } catch (SQLException se) {
                System.out.println("oops! No se puede conectar. Error: " + se.toString());

            } finally {
                return resultat;
            }
        }

        @Override
        protected void onPostExecute(String result) {
            //      Cafeteria[] cafeterias = misCafeterias.toArray(new Cafeteria[misCafeterias.size()]);
            //      MyAdapter adapter = new MyAdapter(getActivity(), cafeterias,"cafe");
            if (misCafeterias.size() == 0) {
                Bitmap foto = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888);
                misCafeterias.add(new Cafeteria("ERROR CONEXION a BBDD", "", "", 1, 1, 1, true, false, true, true, false, false, "17", true, 4, foto));
            }
            MyAdapter adapter = new MyAdapter(getActivity(), misCafeterias, "cafe", "");
            cafes.setAdapter(adapter);
            // carga de solo array list

            Toast.makeText(getActivity(), sql, Toast.LENGTH_SHORT).show();
        }
    }

}


