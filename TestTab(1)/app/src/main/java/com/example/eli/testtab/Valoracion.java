package com.example.eli.testtab;

import java.util.Date;

/**
 * Created by Eli on 26/12/2017.
 */

public class Valoracion {
    int id_valoracion;
    int id_val_usuario;
    int id_val_cafeteria;
    int valoracion_global;
    int limpieza;
    int rapidez_servicio;
    int trato;
    int ambiente;
    int precios;
    int disenyo;
    int accesibilidad;
    int facil_aparcar;
    String com_titulo;
    String com_text;
    Date data;

    // constructor para inserts
    public Valoracion(int id_val_usuario, int id_val_cafeteria, int valoracion_global, int limpieza, int rapidez_servicio, int trato,
                      int ambiente, int precios, int disenyo, int accesibilidad, int facil_aparcar, String com_titulo, String com_text, Date data) {
        this.id_val_usuario = id_val_usuario;
        this.id_val_cafeteria = id_val_cafeteria;
        this.valoracion_global = valoracion_global;
        this.limpieza = limpieza;
        this.rapidez_servicio = rapidez_servicio;
        this.trato = trato;
        this.ambiente = ambiente;
        this.precios = precios;
        this.disenyo = disenyo;
        this.accesibilidad = accesibilidad;
        this.facil_aparcar = facil_aparcar;
        this.com_titulo = com_titulo;
        this.com_text = com_text;
        this.data=data;

    }
    // constructor para consulta valoracion
    public Valoracion(int valoracion_global, int limpieza, int rapidez_servicio, int trato, int ambiente, int precios, int disenyo, int accesibilidad, int facil_aparcar) {
        this.valoracion_global = valoracion_global;
        this.limpieza = limpieza;
        this.rapidez_servicio = rapidez_servicio;
        this.trato = trato;
        this.ambiente = ambiente;
        this.precios = precios;
        this.disenyo = disenyo;
        this.accesibilidad = accesibilidad;
        this.facil_aparcar = facil_aparcar;
    }
    // constructor para consulta comentarios por cafeteria


    public Valoracion(int id_val_usuario, int valoracion_global, String com_titulo, String com_text, Date data) {
        this.id_val_usuario = id_val_usuario;
        this.valoracion_global = valoracion_global;
        this.com_titulo = com_titulo;
        this.com_text = com_text;
        this.data=data;
    }

    public int getId_valoracion() {
        return id_valoracion;
    }

    public int getId_val_usuario() {
        return id_val_usuario;
    }

    public void setId_val_usuario(int id_val_usuario) {
        this.id_val_usuario = id_val_usuario;
    }

    public int getId_val_cafeteria() {
        return id_val_cafeteria;
    }

    public void setId_val_cafeteria(int id_val_cafeteria) {
        this.id_val_cafeteria = id_val_cafeteria;
    }

    public int getValoracion_global() {
        return valoracion_global;
    }

    public void setValoracion_global(int valoracion_global) {
        this.valoracion_global = valoracion_global;
    }

    public int getLimpieza() {
        return limpieza;
    }

    public void setLimpieza(int limpieza) {
        this.limpieza = limpieza;
    }

    public int getRapidez_servicio() {
        return rapidez_servicio;
    }

    public void setRapidez_servicio(int rapidez_servicio) {
        this.rapidez_servicio = rapidez_servicio;
    }

    public int getTrato() {
        return trato;
    }

    public void setTrato(int trato) {
        this.trato = trato;
    }

    public int getAmbiente() {
        return ambiente;
    }

    public void setAmbiente(int ambiente) {
        this.ambiente = ambiente;
    }

    public int getPrecios() {
        return precios;
    }

    public void setPrecios(int precios) {
        this.precios = precios;
    }

    public int getDisenyo() {
        return disenyo;
    }

    public void setDisenyo(int disenyo) {
        this.disenyo = disenyo;
    }

    public int getAccesibilidad() {
        return accesibilidad;
    }

    public void setAccesibilidad(int accesibilidad) {
        this.accesibilidad = accesibilidad;
    }

    public int getFacil_aparcar() {
        return facil_aparcar;
    }

    public void setFacil_aparcar(int facil_aparcar) {
        this.facil_aparcar = facil_aparcar;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getCom_titulo() {
        return com_titulo;
    }

    public void setCom_titulo(String com_titulo) {
        this.com_titulo = com_titulo;
    }

    public String getCom_text() {
        return com_text;
    }

    public void setCom_text(String com_text) {
        this.com_text = com_text;
    }
}
