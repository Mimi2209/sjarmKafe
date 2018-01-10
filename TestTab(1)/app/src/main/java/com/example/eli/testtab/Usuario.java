package com.example.eli.testtab;

import android.graphics.Bitmap;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by Eli on 26/12/2017.
 */

public class Usuario {
    int id_usuario;
    String nombre;
    String pwd;
    Timestamp ultima_conexion;
    String e_mail;
    Bitmap foto;

    public Usuario(String nombre, String pwd, Timestamp ultima_conexion, String e_mail, Bitmap foto) {
        this.nombre = nombre;
        this.pwd = pwd;
        this.ultima_conexion = ultima_conexion;
        this.e_mail = e_mail;
        this.foto = foto;
    }

    public Usuario(int id_usuario, String nombre, String pwd, Timestamp ultima_conexion, String e_mail, Bitmap foto) {
        this.id_usuario = id_usuario;
        this.nombre = nombre;
        this.pwd = pwd;
        this.ultima_conexion = ultima_conexion;
        this.e_mail = e_mail;
        this.foto = foto;
    }

    public int getId() {
        return id_usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Timestamp getUltima_conexion() {
        return ultima_conexion;
    }

    public void setUltima_conexion(Timestamp ultima_conexion) {
        this.ultima_conexion = ultima_conexion;
    }

    public String getE_mail() {
        return e_mail;
    }

    public void setE_mail(String e_mail) {
        this.e_mail = e_mail;
    }

    public Bitmap getFoto() {
        return foto;
    }

    public void setFoto(Bitmap foto) {
        this.foto = foto;
    }
}