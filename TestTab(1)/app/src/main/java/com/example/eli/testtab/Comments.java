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

import java.security.Timestamp;
import java.sql.SQLException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Eli on 27/12/2017.
 */

public class Comments extends Fragment {

    final int USER_REQUEST = 1, NEW_RAT = 2;
    GlobalState gs;
    int mNum;
    ListView comments;
    int idCafeteria;
    String resultat;
    TextView tNameCafe;
    RatingBar ratGlobal;
    FloatingActionButton add;
    ArrayList<Valoracion> comentarios = new ArrayList<Valoracion>();
    ProgressDialog progreso;

    static Comments newInstance(int num) {
        Comments c = new Comments();

        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putInt("num", num);
        c.setArguments(args);

        return c;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNum = getArguments() != null ? getArguments().getInt("num") : 1;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        gs = (GlobalState) getActivity().getApplication();
        return inflater.inflate(R.layout.comments, container, false);
    }

    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);
        comments = (ListView) getView().findViewById(R.id.comment_list);
        tNameCafe = (TextView) getView().findViewById(R.id.cafe);
        ratGlobal = (RatingBar) getView().findViewById(R.id.rating2);
        add = (FloatingActionButton) getView().findViewById(R.id.addValoracion);
        idCafeteria = gs.getId_cafeteria();
        tNameCafe.setText(gs.getNom_cafeteria());
        ratGlobal.setRating(gs.getRating_cafeteria());

        Descarga nuevaDescarga = new Descarga();
        nuevaDescarga.execute();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent;
                if (gs.getId_usr()>0 ) {  // Si el usuario ya se ha identificado, no lo vuelve a pedir
                    intent = new Intent(getActivity(),
                            NewRatingActivity.class);
                    startActivityForResult(intent, NEW_RAT);
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
                //     String returnValue = data.getStringExtra("user");//no deberiamos pasar el usuario que la crea?
                Toast.makeText(getActivity(), "Hello " + gs.getNom_usr() + " your id is :" + gs.getId_usr(), Toast.LENGTH_LONG).show();

                Intent intent = new Intent(getActivity(),
                        NewRatingActivity.class);

                startActivityForResult(intent, NEW_RAT);

            }

        }
        else {
            if (requestCode == NEW_RAT) {
                Toast.makeText(getActivity().getApplicationContext(), " Comment inserted fine !!! ", Toast.LENGTH_SHORT).show();
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
                comentarios = baseDatos.verComentarios(idCafeteria); // obtiene comentarios por cafetería
            } catch (SQLException se) {
                System.out.println("oops! No se puede conectar. Error: " + se.toString());

            } finally {
                return resultat;
            }
        }

        @Override
        protected void onPostExecute(String result) {
            progreso.dismiss();

            if (comentarios.size() == 0) {
                comentarios.add(new Valoracion(0, "No hay Comentarios todavia para esta cafeteria", "", null, null, null));
            }
            MyAdapter adapter = new MyAdapter(getActivity(), comentarios, "comment", 0);
            comments.setAdapter(adapter);
            // carga de solo array list

        }
    }
}
