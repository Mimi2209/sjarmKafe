package com.example.eli.testtab;

import java.sql.Timestamp;

/**
 * Created by mpadilla1 on 29/12/2017.
 */

public class Evento {

    int id_evento;
    int id_evento_cafeteria;
    int id_evento_usuario;
    String event_name;
    String event_descrip;
    String event_location;
    String event_inicio;
    String event_fin;
// Para Insert
    public Evento(int id_evento_cafeteria, int id_evento_usuario, String event_name, String event_descrip, String event_location, String event_inicio, String event_fin) {
        this.id_evento_cafeteria = id_evento_cafeteria;
        this.id_evento_usuario = id_evento_usuario;
        this.event_name = event_name;
        this.event_descrip = event_descrip;
        this.event_location = event_location;
        this.event_inicio = event_inicio;
        this.event_fin = event_fin;
    }
// para Ver Evenros
    public Evento(int id_evento_usuario, String event_name, String event_descrip, String event_location, String event_inicio, String event_fin) {
        this.id_evento_usuario = id_evento_usuario;
        this.event_name = event_name;
        this.event_descrip = event_descrip;
        this.event_location = event_location;
        this.event_inicio = event_inicio;
        this.event_fin = event_fin;
    }

    public int getId_evento() {
        return id_evento;
    }

    public void setId_evento(int id_evento) {
        this.id_evento = id_evento;
    }

    public int getId_evento_cafeteria() {
        return id_evento_cafeteria;
    }

    public void setId_evento_cafeteria(int id_evento_cafeteria) {
        this.id_evento_cafeteria = id_evento_cafeteria;
    }

    public int getId_evento_usuario() {
        return id_evento_usuario;
    }

    public void setId_evento_usuario(int id_evento_usuario) {
        this.id_evento_usuario = id_evento_usuario;
    }

    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public String getEvent_location() {
        return event_location;
    }

    public void setEvent_location(String event_location) {
        this.event_location = event_location;
    }


    public String getEvent_descrip() {
        return event_descrip;
    }

    public void setEvent_descrip(String event_descrip) {
        this.event_descrip = event_descrip;
    }

    public String getEvent_inicio() {
        return event_inicio;
    }

    public void setEvent_inicio(String event_inicio) {
        this.event_inicio = event_inicio;
    }

    public String getEvent_fin() {
        return event_fin;
    }

    public void setEvent_fin(String event_fin) {
        this.event_fin = event_fin;
    }
}

