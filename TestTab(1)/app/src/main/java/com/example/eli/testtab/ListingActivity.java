package com.example.eli.testtab;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Eli on 12/01/2018.
 */

public class ListingActivity extends AppCompatActivity {
    ListView cafes;
    ArrayList<Cafeteria> misCafeterias = new ArrayList<Cafeteria>();
    ArrayList<Cafeteria> misCafeteriasBak = new ArrayList<Cafeteria>();
    String resultat;
    GlobalState gs;
    String sql;
    ProgressDialog progreso;
    MyAdapter adapter;
    Context c;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listing);
        gs = (GlobalState) getApplication();
        cafes = (ListView) findViewById(R.id.cafeteria_list);
        c= this;
        Descarga_listing nuevaDescarga_listing = new Descarga_listing();
        nuevaDescarga_listing.execute(gs.getSql_search());
    //    Toast.makeText(c, "Searching,  please wait, filtering by criteria and distance "+gs.getDistancia_search()+" km", Toast.LENGTH_SHORT).show();
    }
    //---------------------------------------------------------------------------
    public class Descarga_listing extends AsyncTask<String, Integer, String> {

        @Override
        protected void onPreExecute() {
            progreso = new ProgressDialog(c);
            progreso.setTitle("Downloading...");
            progreso.setMessage("Searching,  please wait, filtering by criteria and distance "+gs.getDistancia_search()+" km");
            progreso.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progreso.setIndeterminate(false);
            progreso.setProgress(0);
            progreso.show();

        }

        @Override
        protected String doInBackground(String... urls) {

            try {
                GestionBBDD baseDatos = new GestionBBDD(); // conecta con servidor SQL
                    misCafeterias = baseDatos.verListCafeterias(urls[0],gs.getDistancia_search(),gs.getLongitut(),gs.getLatitut());
                    gs.setSql_search("");


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
            progreso.dismiss();
            if (misCafeterias.size() == 0 || misCafeterias==null) {
                if(misCafeteriasBak.size()> 1){
                    misCafeterias=misCafeteriasBak;
                }else {
                    Bitmap foto = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888);
                    misCafeterias.add(new Cafeteria("Not found according search criteria", "", "", 1, 1, 1, true, false, true, true, false, false, "17", true, 4, foto));
                }
            }else {
                misCafeteriasBak = misCafeterias;
            }
             adapter = new MyAdapter(c, misCafeterias, "cafe", "");
             cafes.setAdapter(adapter);
            // carga de solo array list
        }
    }
}
