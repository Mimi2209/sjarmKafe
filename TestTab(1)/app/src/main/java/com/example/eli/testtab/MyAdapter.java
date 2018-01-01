package com.example.eli.testtab;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;


import java.util.List;

/**
 * Created by mpadilla1 on 26/12/2017.
 */

public class MyAdapter extends BaseAdapter {

        Evento [] eventos;
        Cafeteria[] cafeterias;
        Valoracion [] comments;
        Usuario [] users;
        Context c;
        String type;

        public MyAdapter(Context c, Cafeteria[] cafeterias, String type) {
            this.c = c;
            this.cafeterias = cafeterias;
            this.type=type;
        }

    public MyAdapter(Context c, Evento[] eventos, String type) {
        this.c = c;
        this.eventos = eventos;
        this.type=type;
    }

    public MyAdapter(Context c, Valoracion[] comments, String type, Usuario[] users) {
        this.c = c;
        this.comments = comments;
        this.type=type;
        this.users=users;
    }

        @Override
        public int getCount() {
            int count=1;
            switch (type) {
                case "cafe":
                    count= cafeterias.length;
                    break;
                case "event":
                    count= eventos.length;
                    break;
                case "comment":
                    count= comments.length;
                    break;
            }
            return count;

        }

        @Override
        public Object getItem(int position) {
            Object Item= new Object();
            switch (type) {

                case "cafe":
                    Item = cafeterias[position];
                    break;
                case "event":
                    Item = eventos[position];
                    break;
                case "comment":
                    Item = comments[position];
                    break;
            }
            return Item;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflador = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View elemento= new View(c);
            switch (type){
                case "cafe":
                    elemento = inflador.inflate(R.layout.cafeteria, parent, false);
                    TextView ncafe = (TextView) elemento.findViewById(R.id.cafe);
                    EditText hcafe = (EditText) elemento.findViewById(R.id.horario);
                    EditText dire = (EditText) elemento.findViewById(R.id.cafe_address);
                    RatingBar rating = (RatingBar) elemento.findViewById(R.id.rating);
                    final ImageView image = (ImageView) elemento.findViewById(R.id.cafe_image);
                    ncafe.setText(cafeterias[position].getNombre_cafeteria());
                    hcafe.setText(cafeterias[position].getHorario());
                    dire.setText(cafeterias[position].getAddress());
                    rating.setRating(cafeterias[position].getValoracion_global());
                    image.setImageBitmap(cafeterias[position].getImg());
                    break;
                case "event":
                    elemento = inflador.inflate(R.layout.event, parent, false);
                    TextView nevent = (TextView) elemento.findViewById(R.id.event);
                    EditText hevent = (EditText) elemento.findViewById(R.id.time_event);
                    TextView location = (TextView) elemento.findViewById(R.id.location);
                    EditText desevent = (EditText) elemento.findViewById(R.id.event_descript);
                    nevent.setText(eventos[position].getEvent_name());
                    hevent.setText((CharSequence) eventos[position].getEvent_inicio()+"-"+eventos[position].getEvent_fin());
                    location.setText(eventos[position].getEvent_location());
                    desevent.setText(eventos[position].getEvent_descrip());
                    break;
                case "comment":
                    elemento = inflador.inflate(R.layout.comentario, parent, false);
                    TextView ncomment = (TextView) elemento.findViewById(R.id.comment);
                    TextView nuser = (TextView) elemento.findViewById(R.id.user);
                    EditText hcomment = (EditText) elemento.findViewById(R.id.horario);
                    EditText descomment = (EditText) elemento.findViewById(R.id.comment_descript);
                    RatingBar crating = (RatingBar) elemento.findViewById(R.id.rating);
                    final ImageView uimage = (ImageView) elemento.findViewById(R.id.user_image);
                    ncomment.setText(comments[position].getCom_titulo());
                    int Userid = comments[position].id_val_usuario;
                    nuser.setText(users[Userid].getNombre());
                    hcomment.setText((CharSequence) comments[position].data);
                    descomment.setText(comments[position].getCom_text());
                    crating.setRating(comments[position].getValoracion_global());
                    uimage.setImageBitmap(users[Userid].getFoto());
                    break;
            }

            return elemento;
        }
    }
