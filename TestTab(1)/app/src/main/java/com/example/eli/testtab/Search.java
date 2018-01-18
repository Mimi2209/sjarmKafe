package com.example.eli.testtab;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Eli on 27/12/2017.
 */

public class Search extends Fragment {
    final int LISTING = 1;
    CheckBox terraza, mesas, wifi, shop, comida, xPress, perros;
    RatingBar ratGlobal;
    SeekBar distancia;
    TextView valDist;
    Spinner sItems;
    ArrayList<String> spinnerArray = null;
    ArrayList<Tipo_cafe> arTipCafe = new ArrayList<>();
    int tip_cafe;
    FloatingActionButton fab;
    GlobalState gs;
    ArrayAdapter<String> adapter;
    Descarga_cafe nuevaDescarga_cafe;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        nuevaDescarga_cafe = new Descarga_cafe();
        nuevaDescarga_cafe.execute();
        return inflater.inflate(R.layout.search, container, false);
    }

    public void onActivityCreated(Bundle state) {

        //   View V = getView();
        super.onActivityCreated(state);
        gs = (GlobalState) getActivity().getApplication();
        terraza = (CheckBox) getView().findViewById(R.id.Terrace);
        mesas = (CheckBox) getView().findViewById(R.id.Tables);
        wifi = (CheckBox) getView().findViewById(R.id.Wifi);
        shop = (CheckBox) getView().findViewById(R.id.shop);
        comida = (CheckBox) getView().findViewById(R.id.Meals);
        xPress = (CheckBox) getView().findViewById(R.id.Xpress);
        perros = (CheckBox) getView().findViewById(R.id.Dogs);
        ratGlobal = (RatingBar) getView().findViewById(R.id.rating);
        distancia = (SeekBar) getView().findViewById(R.id.seekDistancia);
        distancia.setProgress(20);
        valDist = (TextView) getView().findViewById(R.id.valDistancia);
//        sItems = (Spinner) getView().findViewById(R.id.tcafe);  posar el nom del spinner en el search
        sItems.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parentView,
                                       View selectedItemView, int position, long id) {
                // Object item = parentView.getItemAtPosition(position);

                tip_cafe = sItems.getSelectedItemPosition() + 1;

            }

            public void onNothingSelected(AdapterView<?> arg0) {
                tip_cafe = 0;
            }

        });


        fab = (FloatingActionButton) getActivity().findViewById(R.id.search_button);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                gs.setSql_search(compone_sql());
                ratGlobal.setRating(0);
                Intent i = new Intent(getActivity(), ListingActivity.class);
                startActivity(i);

            }
        });
        distancia.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }


            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                // TODO Auto-generated method stub
                valDist.setText(String.valueOf(progress) + " km");
            }
        });
    }


    //---------------------------------------------------------------------------
    public String compone_sql() {
        String sql_string = "SELECT * FROM u125322db1.cafeteria ";
        int chk_criterios = 0;
        if (mesas.isChecked()) {
            if (chk_criterios == 0) {
                sql_string += " WHERE ";
            }
            sql_string += "mesas = true ";
            chk_criterios++;
        }
        if (terraza.isChecked()) {
            if (chk_criterios == 0) {
                sql_string += " WHERE ";
            } else {
                sql_string += " and ";
            }
            sql_string += "terraza = true ";
            chk_criterios++;
        }
        if (wifi.isChecked()) {
            if (chk_criterios == 0) {
                sql_string += " WHERE ";
            } else {
                sql_string += " and ";
            }
            sql_string += "wifi = true ";
            chk_criterios++;
        }
        if (comida.isChecked()) {
            if (chk_criterios == 0) {
                sql_string += " WHERE ";
            } else {
                sql_string += " and ";
            }
            sql_string += "comida = true ";
            chk_criterios++;
        }
        if (shop.isChecked()) {
            if (chk_criterios == 0) {
                sql_string += " WHERE ";
            } else {
                sql_string += " and ";
            }
            sql_string += "tienda = true ";
            chk_criterios++;
        }
        if (perros.isChecked()) {
            if (chk_criterios == 0) {
                sql_string += " WHERE ";
            } else {
                sql_string += " and ";
            }
            sql_string += "perros = true ";
            chk_criterios++;
        }
        if (xPress.isChecked()) {
            if (chk_criterios == 0) {
                sql_string += " WHERE ";
            } else {
                sql_string += " and ";
            }
            sql_string += "servicio_express = true ";
            chk_criterios++;
        }
        if (tip_cafe > 0) {
            if (chk_criterios == 0) {
                sql_string += " WHERE ";
            } else {
                sql_string += " and ";
            }
            sql_string += "tip_cafe= " + tip_cafe;
            chk_criterios++;
        }
        //  aplicar distancia 5 km max.
        if (distancia.getProgress() > 0) {
            gs.setDistancia_search(distancia.getProgress());
        }

        sql_string += " ;";
        return sql_string;

    }

    //---------------------------------------------------------------------------
    public class Descarga_cafe extends AsyncTask<String, Integer, String> {

        @Override
        protected void onPreExecute() {


        }

        @Override
        protected String doInBackground(String... tipo) {

            try {
                GestionBBDD baseDatos = new GestionBBDD(); // conecta con servidor SQL
// DATOS QUE FALTAN !!!! //
                // falta tipo de cafe
                // falta long/ltg
                // Falta captura foto


                arTipCafe = baseDatos.verTipCafe();

            } catch (SQLException se) {
                System.out.println("oops! No se puede conectar. Error: " + se.toString());
            } finally {

                //         estatus=tNameCafe.getText().toString()+"/"+tAddress.getText().toString()+"/"+ tDescrip.getText().toString()+2+"/"+ cTables.isChecked()+"/"+ cTerrace.isChecked()+"/"+rRating2.getNumStars() ;
                return "";
            }
        }

        @Override
        protected void onPostExecute(String result) {
            spinnerArray = new ArrayList<String>();
            for (int i = 0; i < arTipCafe.size(); i++) {
                spinnerArray.add(arTipCafe.get(i).getNombre_cafe());
            }
            if (spinnerArray != null) { // cargo spinner array en spinner si array lleno
                adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_spinner_item, spinnerArray);
                adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                sItems.setAdapter(adapter);
            }
        }
    }

}

