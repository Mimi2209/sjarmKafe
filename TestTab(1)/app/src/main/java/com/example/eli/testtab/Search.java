package com.example.eli.testtab;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Eli on 27/12/2017.
 */

public class Search extends Fragment {
    CheckBox terraza, mesas, wifi, shop, comida, xPress, perros;
    RatingBar ratGlobal;
    SeekBar distancia;
    Cafeteria searchCafe;
    Button jumpTo;
    FloatingActionButton fab;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        return inflater.inflate(R.layout.search, container, false);
    }

    public void onActivityCreated(Bundle state) {

        //   View V = getView();
        super.onActivityCreated(state);

        terraza = (CheckBox) getView().findViewById(R.id.Terrace);
        mesas = (CheckBox) getView().findViewById(R.id.Tables);
        wifi = (CheckBox) getView().findViewById(R.id.Wifi);
        shop = (CheckBox) getView().findViewById(R.id.shop);
        comida = (CheckBox) getView().findViewById(R.id.Meals);
        xPress = (CheckBox) getView().findViewById(R.id.Xpress);
        perros = (CheckBox) getView().findViewById(R.id.Dogs);
        ratGlobal = (RatingBar) getView().findViewById(R.id.rating);
        distancia = (SeekBar) getView().findViewById(R.id.seekDistancia);
        jumpTo = (Button) getView().findViewById(R.id.prueba);


        fab = (FloatingActionButton) getActivity().findViewById(R.id.search_button);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchCafe = new Cafeteria(mesas.isChecked(), terraza.isChecked(), wifi.isChecked(), comida.isChecked(), shop.isChecked(), perros.isChecked(), xPress.isChecked(), ratGlobal.getNumStars(), distancia.getProgress());
// crear string select i passar-lo com a variable global.
                jumpTo.performClick();  //activo listing

            }
        });
           }

    //---------------------------------------------------------------------------

}







