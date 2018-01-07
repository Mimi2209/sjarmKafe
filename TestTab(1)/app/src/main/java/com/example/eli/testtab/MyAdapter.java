package com.example.eli.testtab;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.app.Application;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by mpadilla1 on 26/12/2017.
 */

public class MyAdapter extends BaseAdapter {

        ArrayList<Evento> eventos;
        ArrayList <Cafeteria> cafeterias;
        ArrayList<Valoracion> comments;
        ArrayList<Usuario> users;
        Context c;
        String type;
        GlobalState gs;

        public MyAdapter(Context c, ArrayList<Cafeteria> cafeterias, String type, String junk) {
            this.c = c;
            this.cafeterias = cafeterias;
            this.type=type;
        }
    public MyAdapter(Context c, ArrayList<Evento> eventos, String type) {
        this.c = c;
        this.eventos = eventos;
        this.type=type;
    }

    public MyAdapter(Context c, ArrayList<Valoracion> comments, String type, int vacio) {
        this.c = c;
        this.comments = comments;
        this.type=type;
        }

        @Override
        public int getCount() {
            int count=1;
            switch (type) {
                case "cafe":
                    count= cafeterias.size();
                    break;
                case "event":
                    count= eventos.size();
                    break;
                case "comment":
                    count= comments.size();
                    break;
            }
            return count;

        }

        @Override
        public Object getItem(int position) {
            Object Item= new Object();
            switch (type) {

                case "cafe":
                    Item = cafeterias.get(position);
                    break;
                case "event":
                    Item = eventos.get(position);
                    break;
                case "comment":
                    Item = comments.get(position);
                    break;
            }
            return Item;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            gs = (GlobalState) c.getApplicationContext();

            LayoutInflater inflador = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View elemento= new View(c);
            switch (type){
                case "cafe":
                    elemento = inflador.inflate(R.layout.cafeteria, parent, false);

                    final TextView ncafe = (TextView) elemento.findViewById(R.id.cafe);
                    TextView hcafe = (TextView) elemento.findViewById(R.id.horario);
                    TextView dire = (TextView) elemento.findViewById(R.id.cafe_address);
                    RatingBar rating = (RatingBar) elemento.findViewById(R.id.rating);
                    ImageView image = (ImageView) elemento.findViewById(R.id.cafe_image);
                    LinearLayout lNom = (LinearLayout) elemento.findViewById(R.id.linea_nom);
                    lNom.setBackgroundColor(Color.LTGRAY);
                    ncafe.setText(cafeterias.get(position).getNombre_cafeteria());
                    hcafe.setText(cafeterias.get(position).getHorario());
                    dire.setText(cafeterias.get(position).getAddress());
                    rating.setRating(cafeterias.get(position).getValoracion_global());
                    image.setImageBitmap(cafeterias.get(position).getImg());
                    lNom.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int id =cafeterias.get(position).getId_cafeteria();
                            gs.setPict_cafeteria(cafeterias.get(position).getImg());
                            gs.setId_cafeteria(cafeterias.get(position).getId_cafeteria());
                            gs.setNom_cafeteria(cafeterias.get(position).getNombre_cafeteria());
                            gs.setRating_cafeteria(cafeterias.get(position).getValoracion_global());
                            Intent intent = new Intent(c,
                            CafeteriaActivity.class);
                            c.startActivity(intent);
                        }
                    });
                    break;
                case "event":
                    elemento = inflador.inflate(R.layout.event, parent, false);
                    TextView nevent = (TextView) elemento.findViewById(R.id.event);
                    TextView hevent = (TextView) elemento.findViewById(R.id.time_event);
                    TextView location = (TextView) elemento.findViewById(R.id.location);
                    TextView desevent = (TextView) elemento.findViewById(R.id.event_descript);
                    nevent.setText(eventos.get(position).getEvent_name());
                    Date inicio=eventos.get(position).getEvent_inicio();
                    Date fin=eventos.get(position).getEvent_fin();
                    Calendar cal=Calendar.getInstance();
                    cal.setTime(inicio);
                    Calendar cal1=Calendar.getInstance();
                    cal1.setTime(fin);
                    String showTime=String.format("%1$tI:%1$tM:%1$tS %1$Tp",cal)+"-"+String.format("%1$tI:%1$tM:%1$tS %1$Tp",cal1);
                    hevent.setText(showTime);
                    location.setText(eventos.get(position).getEvent_location());
                    desevent.setText(eventos.get(position).getEvent_descrip());
                    break;
                case "comment":
                    elemento = inflador.inflate(R.layout.comentario, parent, false);
                    TextView ncomment = (TextView) elemento.findViewById(R.id.comment);
                    TextView hcomment = (TextView) elemento.findViewById(R.id.horario);
                    TextView descomment = (TextView) elemento.findViewById(R.id.comment_descript);
                    TextView nom_user = (TextView) elemento.findViewById(R.id.user);
                    ImageView uimage = (ImageView) elemento.findViewById(R.id.user_image);

                    //int Userid = comments.get(position).id_val_usuario;
                    nom_user.setText(comments.get(position).getNom_usr());
                    uimage.setImageBitmap(comments.get(position).getImg_usr());
                    // comentario
                    ncomment.setText(comments.get(position).getCom_titulo());
                    Date data=comments.get(position).data;
                    Calendar cal2=Calendar.getInstance();
                    cal2.setTime(data);
                    String showTimeC=String.format("%1$tI:%1$tM:%1$tS %1$Tp",cal2);
                    hcomment.setText(showTimeC);
                    descomment.setText(comments.get(position).getCom_text());
                  //  try {
                  //      crating.setRating(comments.get(position).getValoracion_global());
                  //  }catch (NullPointerException e){

                 //   }

            }

            return elemento;
        }

}
