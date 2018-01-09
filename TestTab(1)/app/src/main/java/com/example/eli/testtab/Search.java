package com.example.eli.testtab;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RatingBar;
import android.widget.SeekBar;

/**
 * Created by Eli on 27/12/2017.
 */

public class Search extends Fragment {
    CheckBox terraza, mesas, wifi, shop, comida, xPress, perros;
    RatingBar ratGlobal;
    SeekBar distancia;
    Button jumpTo;
    FloatingActionButton fab;
    GlobalState gs;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


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
        jumpTo = (Button) getView().findViewById(R.id.lanza);


        fab = (FloatingActionButton) getActivity().findViewById(R.id.search_button);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //searchCafe = new Cafeteria(mesas.isChecked(), terraza.isChecked(), wifi.isChecked(), comida.isChecked(), shop.isChecked(), perros.isChecked(), xPress.isChecked(), ratGlobal.getRating(), distancia.getProgress());
// crear string select i passar-lo com a variable global.

                //  Toast.makeText(getActivity(), sql_select, Toast.LENGTH_SHORT).show();
                gs.setSql_search(compone_sql());
                ratGlobal.setRating(0);
                jumpTo.performClick();  //activo listin


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
        if (ratGlobal.getRating() > 0) {
            if (chk_criterios == 0) {
                sql_string += " WHERE ";
            } else {
                sql_string += " and ";
            }
            sql_string += "valoracion >= " + ratGlobal.getRating();
            chk_criterios++;
        }
// falta aplicar distancia
        //if (distancia.getProgress()>0){
        //    if (chk_criterios==0){
        //        sql_string+=" Where ";
        //    }else{
        //        sql_string+=" and ";
        //    }
        //    sql_string+="valoracion >= "+ratGlobal.getNumStars();
        //    chk_criterios++;
        //}
        sql_string += " ;";
        return sql_string;

    }

}

