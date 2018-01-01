package com.example.eli.testtab;

/**
 * Created by Eli on 26/12/2017.
 */

public class Tipo_cafe {
    int id_cafe;
    String nombre_cafe;
    String caracteristicas;

    public Tipo_cafe(String nombre_cafe, String caracteristicas) {
        this.nombre_cafe = nombre_cafe;
        this.caracteristicas = caracteristicas;
    }

    public int getId_cafe() {
        return id_cafe;
    }

    public String getNombre_cafe() {
        return nombre_cafe;
    }

    public void setNombre_cafe(String nombre_cafe) {
        this.nombre_cafe = nombre_cafe;
    }

    public String getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(String caracteristicas) {
        this.caracteristicas = caracteristicas;
    }
}
