package com.example.eli.testtab;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Eli on 27/12/2017.
 */

    public class Events extends Fragment {
    final int USER_REQUEST = 1, NEW_EVE = 2;
    int mNum;
    ListView events;
    Timestamp timeStamp = new Timestamp(1);
    int idCafeteria;
    String resultat;
    GlobalState gs;
    ArrayList<Evento> eventos = new ArrayList<Evento>();
    TextView tNameCafe;
    RatingBar ratGlobal;
    FloatingActionButton add;
    ProgressDialog progreso;

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
        gs = (GlobalState) getActivity().getApplication();
        idCafeteria = gs.getId_cafeteria();
        events = (ListView) getView().findViewById(R.id.event_list);
        tNameCafe = (TextView) getView().findViewById(R.id.cafe);
        ratGlobal = (RatingBar) getView().findViewById(R.id.rating2);
        tNameCafe.setText(gs.getNom_cafeteria());
        ratGlobal.setRating(gs.getRating_cafeteria());
        add = (FloatingActionButton) getView().findViewById(R.id.addEvent);
        Descarga nuevaDescarga = new Descarga();
        nuevaDescarga.execute();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                if (gs.getId_usr()>0) {  // Si el usuario ya se ha identificado, no lo vuelve a pedir
                    intent = new Intent(getActivity(),
                            NewEventActivity.class);
                    startActivityForResult(intent, NEW_EVE);
                }else{
                    intent = new Intent(getActivity(),
                            UsuarioActivity.class);
                    startActivityForResult(intent, USER_REQUEST);
                }

            }
        });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == USER_REQUEST) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                //          String returnValue = data.getStringExtra("user");//no deberiamos pasar el usuario que la crea?
                Toast.makeText(getActivity(), "Hello " + gs.getNom_usr() + " your id is :" + gs.getId_usr()
                        , Toast.LENGTH_LONG).show();

                Intent intent = new Intent(getActivity(),
                        NewEventActivity.class);
                startActivityForResult(intent, NEW_EVE);

            }

        } else {
            if (requestCode == NEW_EVE) {

                getActivity().finish();
            }
        }
    }
    //---------------------------------------------------------------------------
    public class Descarga extends AsyncTask<String, Integer, String> {

        @Override
        protected void onPreExecute() {
            progreso = new ProgressDialog(getActivity());
            progreso.setTitle("Downloading...");
            progreso.setMessage("Please wait while downloading data for this Cafe");
            progreso.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progreso.setIndeterminate(false);
            progreso.setProgress(0);
            progreso.show();
        }

        @Override
        protected String doInBackground(String... urls) {

            try {
                GestionBBDD baseDatos = new GestionBBDD(); // conecta con servidor SQL
                eventos= baseDatos.verEventos(idCafeteria); // obtiene eventos de la cafetería
            } catch (SQLException se) {
                System.out.println("oops! No se puede conectar. Error: " + se.toString());

            } finally {
                return resultat;
            }
        }

        @Override
        protected void onPostExecute(String result) {
            progreso.dismiss();
            Bitmap foto = Bitmap.createBitmap(1,1, Bitmap.Config.ARGB_8888);
            if(eventos.size()==0) {
                eventos.add(new Evento( 0,0, "NO HAY EVENTOS CREADOS PARA ESTA CAFETERIA", "", "", "",""));
            }
                MyAdapter adapter = new MyAdapter(getActivity(), eventos, "event");
                events.setAdapter(adapter);
        }
    }
}
