package com.example.eli.testtab;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eli on 27/12/2017.
 */

    public class Events extends Fragment {
    int mNum;
    ListView events;
    Timestamp timeStamp = new Timestamp(1);
    int id_cafeteria;
    String resultat;

    ArrayList<Evento> eventos = new ArrayList<Evento>();


    static Events newInstance(int num) {
        Events e = new Events();

        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putInt("num", num);
        e.setArguments(args);

        return e;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNum = getArguments() != null ? getArguments().getInt("num") : 1;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.events, container, false);

    }

    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);
        events = (ListView) getView().findViewById(R.id.event_list);
        Descarga nuevaDescarga = new Descarga();
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
                eventos= baseDatos.verEventos(id_cafeteria); // obtiene eventos de la cafetería
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
            Bitmap foto = Bitmap.createBitmap(1,1, Bitmap.Config.ARGB_8888);
            eventos.add(new Evento(1, "ejemplo", "ejemplo", "ejemplo", timeStamp, timeStamp));
            MyAdapter adapter = new MyAdapter(getActivity(), eventos,"event");
            events.setAdapter(adapter);
            // carga de solo array list

        }
    }
}
