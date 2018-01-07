package com.example.eli.testtab;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Eli on 27/12/2017.
 */

public class Add extends Fragment {


    Cafeteria miCafeteria;
    ArrayList<Tipo_cafe> arTipCafe = new ArrayList<>();
    ArrayList<String> spinnerArray = null;
    String resultat, tipus;
    Boolean bTerrace, bTables, bWifi, bShop, bMeals, bXpress, bDogs;
    CheckBox cTerrace, cTables, cWifi, cShop, cMeals, cXpress, cDogs;
    String sNameCafe, sAddress, sHorario, sDescrip;
    EditText tNameCafe, tAddress, tHorario, tDescrip;
    Spinner sItems;
    ArrayAdapter<String> adapter;
    int tip_cafe;
    RatingBar rRating2;
    static final int USER_REQUEST = 1;
    Descarga nuevaDescarga;
    Descarga_cafe nuevaDescarga_cafe;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        nuevaDescarga_cafe = new Descarga_cafe();
        nuevaDescarga_cafe.execute();


        return inflater.inflate(R.layout.add, container, false);
    }


    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);

        tNameCafe = (EditText) getView().findViewById(R.id.cname);
        tAddress = (EditText) getView().findViewById(R.id.address);
        tHorario = (EditText) getView().findViewById(R.id.horario);
        tDescrip = (EditText) getView().findViewById(R.id.descript);
        cTerrace = (CheckBox) getView().findViewById(R.id.Terrace);
        cTables = (CheckBox) getView().findViewById(R.id.Tables);
        cWifi = (CheckBox) getView().findViewById(R.id.Wifi);
        cShop = (CheckBox) getView().findViewById(R.id.shop);
        cMeals = (CheckBox) getView().findViewById(R.id.Meals);
        cXpress = (CheckBox) getView().findViewById(R.id.Xpress);
        cDogs = (CheckBox) getView().findViewById(R.id.Dogs);
        sItems = (Spinner) getView().findViewById(R.id.tcafe);
        sItems.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parentView,
                                       View selectedItemView, int position, long id) {
                // Object item = parentView.getItemAtPosition(position);

                 tip_cafe = sItems.getSelectedItemPosition() + 1;

            }

            public void onNothingSelected(AdapterView<?> arg0) {
                tip_cafe =1;
            }

        });
        FloatingActionButton fab = (FloatingActionButton) getActivity().findViewById(R.id.addCafeteria);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(getActivity(),
                        UsuarioActivity.class);
                startActivityForResult(intent,USER_REQUEST);
                getActivity().startActivity(intent);

            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == USER_REQUEST) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                String returnValue = data.getStringExtra("user");//no deberiamos pasar el usuario que la crea?
                Toast.makeText(getActivity(), "Hello" +returnValue, Toast.LENGTH_LONG).show();
                nuevaDescarga = new Descarga();
                nuevaDescarga.execute();
                }

            }
        }


    //---------------------------------------------------------------------------
    public class Descarga extends AsyncTask<String, Integer, String> {

        @Override
        protected void onPreExecute() {


        }

        @Override
        protected String doInBackground(String... tipo) {

            try {
                GestionBBDD baseDatos = new GestionBBDD(); // conecta con servidor SQL
// DATOS QUE FALTAN !!!! //
                 // falta long/ltg
                // Falta captura foto
                    sNameCafe = tNameCafe.getText().toString();
                    sAddress = tAddress.getText().toString();
                    sDescrip = tDescrip.getText().toString();
                    bTables = cTables.isChecked();
                    bTerrace = cTerrace.isChecked();
                    bWifi = cWifi.isChecked();
                    bMeals = cMeals.isChecked();
                    bShop = cShop.isChecked();
                    bDogs = cDogs.isChecked();
                    sHorario = tHorario.getText().toString();
                    bXpress = cXpress.isChecked();
                    float latitut = 1;
                    float longitut = 1;
                    Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.tacita); // eliminar cuando activemos camara
                    miCafeteria = new Cafeteria(sNameCafe, sAddress, sDescrip, tip_cafe, longitut, latitut, bTables, bTerrace, bWifi, bMeals, bShop, bDogs, sHorario, bXpress, 0, bitmap);
                    baseDatos.insertCafeteria(miCafeteria); // obtiene cafeteria

            } catch (SQLException se) {
                System.out.println("oops! No se puede conectar. Error: " + se.toString());
            } finally {

                //         estatus=tNameCafe.getText().toString()+"/"+tAddress.getText().toString()+"/"+ tDescrip.getText().toString()+2+"/"+ cTables.isChecked()+"/"+ cTerrace.isChecked()+"/"+rRating2.getNumStars() ;
                return resultat;
            }
        }

        @Override
        protected void onPostExecute(String result) {

                Toast.makeText(getContext(), " CAFETERIA INSERTADA CORRECTAMENTE !! ", Toast.LENGTH_SHORT).show();
                tNameCafe.setText(null);
                tAddress.setText(null);
                tDescrip.setText(null);
                cTables.setChecked(false);
                cTerrace.setChecked(false);
                cWifi.setChecked(false);
                cMeals.setChecked(false);
                cShop.setChecked(false);
                cDogs.setChecked(false);
                tHorario.setText(null);
                cXpress.setChecked(false);
            }
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
                return resultat;
            }
        }

        @Override
        protected void onPostExecute(String result) {
                spinnerArray = new ArrayList<String>();
                for (int i = 0; i < arTipCafe.size(); i++) {
                    spinnerArray.add(arTipCafe.get(i).getNombre_cafe());
                }
                if (spinnerArray != null) { // cargo spinner array en spinner si array lleno
                    adapter = new ArrayAdapter<String> (getActivity().getApplicationContext(), android.R.layout.simple_spinner_item, spinnerArray);
                    adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                    sItems.setAdapter(adapter);
                }
        }
    }
}
