package com.example.eli.testtab;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;

/**
 * Created by Eli on 27/12/2017.
 */

    public class Info extends Fragment {
        int idCafeteria;
        Cafeteria miCafeteria;
        String resultat;
        CheckBox cTerrace, cTables, cWifi, cShop, cMeals, cXpress, cDogs;
        TextView tNameCafe, tAddress, tHorario, tDescrip ;
        RatingBar rRating2;
        GlobalState gs;

    static Info newInstance(int num) {
        Info i = new Info();

        return i;
    }
        @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       //      Toast.makeText(getActivity().getApplicationContext(), " Cafe "+idCafeteria, Toast.LENGTH_SHORT).show();
            return inflater.inflate(R.layout.info, container, false);
           }

    @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);
        gs = (GlobalState) getActivity().getApplication();
        idCafeteria = gs.getId_cafeteria();
        tNameCafe = (TextView) getView().findViewById(R.id.cafe);
        tAddress = (TextView) getView().findViewById(R.id.cafe_address);
        tHorario = (TextView) getView().findViewById(R.id.horario);
        tDescrip = (TextView) getView().findViewById(R.id.cafe_descript);
        cTerrace = (CheckBox) getView().findViewById(R.id.Terrace);
        cTables = (CheckBox) getView().findViewById(R.id.tables);
        cWifi = (CheckBox) getView().findViewById(R.id.Wifi);
        cShop = (CheckBox) getView().findViewById(R.id.shop);
        cMeals = (CheckBox) getView().findViewById(R.id.Meals);
        cXpress = (CheckBox) getView().findViewById(R.id.Xpress);
        cDogs = (CheckBox) getView().findViewById(R.id.Dogs);
        rRating2 = (RatingBar) getView().findViewById(R.id.rating2);

        tNameCafe.setText(gs.getNom_cafeteria());
        rRating2.setRating(gs.getRating_cafeteria());

        Info.Descarga nuevaDescarga = new Info.Descarga();
        nuevaDescarga.execute();

    }
    //---------------------------------------------------------------------------
    @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
    public class Descarga extends AsyncTask<String, Integer, String> {

        @Override
        protected void onPreExecute() {
                   }

        @Override
        protected String doInBackground(String... urls) {

            try {
                GestionBBDD baseDatos = new GestionBBDD(); // conecta con servidor SQL
                miCafeteria= baseDatos.verCafeteria(idCafeteria); // obtiene cafeteria
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
            if (miCafeteria != null) {
                tNameCafe.setText(miCafeteria.getNombre_cafeteria());
                tAddress.setText(miCafeteria.getAddress());
                tHorario.setText(miCafeteria.getHorario());
                tDescrip.setText(miCafeteria.getDescripcion());
                cTerrace.setChecked(miCafeteria.isTerraza());
                cTables.setChecked(miCafeteria.isMesas());
                cWifi.setChecked(miCafeteria.isWifi());
                cMeals.setChecked(miCafeteria.isComida());
                cXpress.setChecked(miCafeteria.isServicio_expres());
                cDogs.setChecked(miCafeteria.isPerros());
                rRating2.setRating(miCafeteria.getValoracion_global());
            }


            //        misCafeterias.add(new Cafeteria("Nomad","Passatge Sert, 12, 08003 Barcelona","Una de las mejores cafeterias de Barcelona",1,true,false,true,true,false,false,"17",true,4,foto));

            // carga de solo array list



        }
    }
    }
