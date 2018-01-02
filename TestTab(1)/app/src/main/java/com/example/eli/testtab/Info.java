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
import android.widget.ListView;
import android.widget.TextView;

import java.sql.SQLException;

/**
 * Created by Eli on 27/12/2017.
 */

    public class Info extends Fragment {
        String idCafeteria="Cafeteria 1";
        Cafeteria miCafeteria;
        String resultat;
        TextView test_info;
        @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // no peta pq lo he controlado pero de Error, no recupera la info
        idCafeteria = getArguments() != null ? getArguments().getString("id_Cafeteria") : "Error";
    }
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            // peta por nullPointer exception-----------------------------
            // Bundle b1= getArguments();
            // idCafeteria =  b1.getString("id_Cafeteria");
            //-------------------------------------------------

            //here is your list array
                        return inflater.inflate(R.layout.info, container, false);
        }

    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);

        test_info = (TextView) getView().findViewById(R.id.test_info);


        Info.Descarga nuevaDescarga = new Info.Descarga();
        nuevaDescarga.execute();

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
            test_info.setText(idCafeteria);
            //        misCafeterias.add(new Cafeteria("Nomad","Passatge Sert, 12, 08003 Barcelona","Una de las mejores cafeterias de Barcelona",1,true,false,true,true,false,false,"17",true,4,foto));

            // carga de solo array list



        }
    }
    }
