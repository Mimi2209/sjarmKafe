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
import android.widget.Toast;

import java.security.Timestamp;
import java.sql.SQLException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Eli on 27/12/2017.
 */

    public class Comments extends Fragment {
    GlobalState gs;
    int mNum;
    ListView comments;
    int idCafeteria;
    String resultat;

    ArrayList<Valoracion> comentarios = new ArrayList<Valoracion>();

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
        Descarga nuevaDescarga = new Descarga();
        nuevaDescarga.execute();

    }

    //---------------------------------------------------------------------------
    public class Descarga extends AsyncTask<String, Integer, String> {

        @Override
        protected void onPreExecute() {
            idCafeteria = gs.getId_cafeteria(); // recupero id cafeteria de la variable global
   //         tNameCafe.setText(gs.getNom_cafeteria());
   //         ratGlobal.setRating(idCafeteria);
        }

        @Override
        protected String doInBackground(String... urls) {

            try {
                GestionBBDD baseDatos = new GestionBBDD(); // conecta con servidor SQL
                comentarios= baseDatos.verComentarios(idCafeteria); // obtiene comentarios por cafetería
            } catch (SQLException se) {
                System.out.println("oops! No se puede conectar. Error: " + se.toString());

            } finally {
                return resultat;
            }
        }

        @Override
        protected void onPostExecute(String result) {


      //      Date data = new Date();
     //       comentarios.add(new Valoracion(1, id_cafeteria, 4, 4, 4, 4,
   //         4, 4, 4, 4, 4, "Me gustaria volver", "Esta cafetería es espectacular", data));
            if (comentarios==null){
                Toast.makeText(getContext(), " Array vacio ", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getContext(), " Array Lleno "+comentarios.size(), Toast.LENGTH_SHORT).show();
            }
            MyAdapter adapter = new MyAdapter(getActivity(), comentarios,"comment",0);
            comments.setAdapter(adapter);
            // carga de solo array list

        }
    }
}
