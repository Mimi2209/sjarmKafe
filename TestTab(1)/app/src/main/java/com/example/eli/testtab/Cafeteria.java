package com.example.eli.testtab;

import android.graphics.Bitmap;

import java.sql.Time;

/**
 * Created by Eli on 26/12/2017.
 */

public class Cafeteria {
    int id_cafeteria;
    String nombre_cafeteria;
    String address;
    String descripcion;
    int tip_cafe;
    float longitut;
    float latitut;
    boolean mesas;
    boolean terraza;
    boolean wifi;
    boolean comida;
    boolean tienda;
    boolean perros;
    String horario;
    boolean servicio_expres;
    int valoracion_global;
    Bitmap img;
    double distancia;

    // constructor para insert
    public Cafeteria(String nombre_cafeteria, String address, String descripcion, int tip_cafe, float longitut, float latitut, boolean mesas, boolean terraza, boolean wifi, boolean comida,
                     boolean tienda, boolean perros, String horario, boolean servicio_expres, int valoracion_global, Bitmap img) {
        this.nombre_cafeteria = nombre_cafeteria;
        this.address = address;
        this.descripcion = descripcion;
        this.tip_cafe = tip_cafe;
        this.longitut = longitut;
        this.latitut = latitut;
        this.mesas = mesas;
        this.terraza = terraza;
        this.wifi = wifi;
        this.comida = comida;
        this.tienda = tienda;
        this.perros = perros;
        this.horario = horario;
        this.servicio_expres = servicio_expres;
        this.valoracion_global = valoracion_global;
        this.img = img;
    }
    // class para info
    public Cafeteria(int id_cafeteria, String nombre_cafeteria, String address, String descripcion, int tip_cafe, boolean mesas, boolean terraza, boolean wifi, boolean comida,
                     boolean tienda, boolean perros, String horario, boolean servicio_expres, int valoracion_global, Bitmap img) {
        this.id_cafeteria=id_cafeteria;
        this.nombre_cafeteria = nombre_cafeteria;
        this.address = address;
        this.descripcion = descripcion;
        this.tip_cafe = tip_cafe;
        this.mesas = mesas;
        this.terraza = terraza;
        this.wifi = wifi;
        this.comida = comida;
        this.tienda = tienda;
        this.perros = perros;
        this.horario = horario;
        this.servicio_expres = servicio_expres;
        this.valoracion_global = valoracion_global;
        this.img = img;
    }
    // class para ubicacion mapa y calculo distancias
    public Cafeteria(String nombre_cafeteria, String address, String descripcion, float longitut, float latitut, String horario,int valoracion_global, double distancia,  Bitmap img) {
        this.nombre_cafeteria = nombre_cafeteria;
        this.address = address;
        this.descripcion = descripcion;
        this.longitut = longitut;
        this.latitut = latitut;
        this.horario = horario;
        this.valoracion_global = valoracion_global;
        this.distancia = distancia;
        this.img = img;
    }

    public int getId_cafeteria() {
        return id_cafeteria;
    }

    public void setId_cafeteria(int id_cafeteria) {
        this.id_cafeteria = id_cafeteria;
    }

    public String getNombre_cafeteria() {
        return nombre_cafeteria;
    }

    public void setNombre_cafeteria(String nombre_cafeteria) {
        this.nombre_cafeteria = nombre_cafeteria;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getTip_cafe() {
        return tip_cafe;
    }

    public void setTip_cafe(int tip_cafe) {
        this.tip_cafe = tip_cafe;
    }

    public float getLongitut() {
        return longitut;
    }

    public void setLongitut(float longitut) {
        this.longitut = longitut;
    }

    public float getLatitut() {
        return latitut;
    }

    public void setLatitut(float latitut) {
        this.latitut = latitut;
    }



    public boolean isMesas() {
        return mesas;
    }

    public void setMesas(boolean mesas) {
        this.mesas = mesas;
    }

    public boolean isTerraza() {
        return terraza;
    }

    public void setTerraza(boolean terraza) {
        this.terraza = terraza;
    }

    public boolean isWifi() {
        return wifi;
    }

    public void setWifi(boolean wifi) {
        this.wifi = wifi;
    }

    public boolean isComida() {
        return comida;
    }

    public void setComida(boolean comida) {
        this.comida = comida;
    }

    public boolean isTienda() {
        return tienda;
    }

    public void setTienda(boolean tienda) {
        this.tienda = tienda;
    }

    public boolean isPerros() {
        return perros;
    }

    public void setPerros(boolean perros) {
        this.perros = perros;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public boolean isServicio_expres() {
        return servicio_expres;
    }

    public void setServicio_expres(boolean servicio_expres) {
        this.servicio_expres = servicio_expres;
    }

    public int getValoracion_global() {
        return valoracion_global;
    }

    public void setValoracion_global(int valoracion_global) {
        this.valoracion_global = valoracion_global;
    }

    public Bitmap getImg() {
        return img;
    }

    public void setImg(Bitmap img) {
        this.img = img;
    }

    public double getDistancia() {
        return distancia;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }
}

