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


import java.util.ArrayList;
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

    public MyAdapter(Context c, ArrayList<Valoracion> comments, String type, ArrayList<Usuario> users) {
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
                            Intent intent = new Intent(c,
                                    CafeteriaActivity.class);

                            // set
                            intent.putExtra("idCafeteria", cafeterias.get(position).getId_cafeteria());
                            c.startActivity(intent);
                        }
                    });
                    break;
                case "event":
                    elemento = inflador.inflate(R.layout.event, parent, false);
                    TextView nevent = (TextView) elemento.findViewById(R.id.event);
                    EditText hevent = (EditText) elemento.findViewById(R.id.time_event);
                    TextView location = (TextView) elemento.findViewById(R.id.location);
                    EditText desevent = (EditText) elemento.findViewById(R.id.event_descript);
                    nevent.setText(eventos.get(position).getEvent_name());
                    hevent.setText((CharSequence) eventos.get(position).getEvent_inicio()+"-"+eventos.get(position).getEvent_fin());
                    location.setText(eventos.get(position).getEvent_location());
                    desevent.setText(eventos.get(position).getEvent_descrip());
                    break;
                case "comment":
                    elemento = inflador.inflate(R.layout.comentario, parent, false);
                    TextView ncomment = (TextView) elemento.findViewById(R.id.comment);
                    TextView nuser = (TextView) elemento.findViewById(R.id.user);
                    EditText hcomment = (EditText) elemento.findViewById(R.id.horario);
                    EditText descomment = (EditText) elemento.findViewById(R.id.comment_descript);
                    RatingBar crating = (RatingBar) elemento.findViewById(R.id.rating);
                    final ImageView uimage = (ImageView) elemento.findViewById(R.id.user_image);
                    ncomment.setText(comments.get(position).getCom_titulo());
                    int Userid = comments.get(position).id_val_usuario;
                    nuser.setText(users.get(Userid).getNombre());
                    hcomment.setText((CharSequence) comments.get(position).data);
                    descomment.setText(comments.get(position).getCom_text());
                    crating.setRating(comments.get(position).getValoracion_global());
                    uimage.setImageBitmap(users.get(Userid).getFoto());
                    break;
            }

            return elemento;
        }
}
