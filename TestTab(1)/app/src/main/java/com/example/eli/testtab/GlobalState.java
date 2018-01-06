package com.example.eli.testtab;

import android.app.Application;
import android.graphics.Bitmap;

/**
 * Created by Eli on 03/01/2018.
 */

public class GlobalState extends Application
{
    private int id_cafeteria;
    private String nom_cafeteria;
    private int rating_cafeteria;
    private Bitmap pict_cafeteria;
    private int id_usr;
    private String nom_usr;
    private Bitmap pict_usr;
    private float latitut;
    private float longitut;

    public int getId_cafeteria() {
        return id_cafeteria;
    }

    public void setId_cafeteria(int id_cafeteria) {
        this.id_cafeteria = id_cafeteria;
    }

    public String getNom_cafeteria() {
        return nom_cafeteria;
    }

    public void setNom_cafeteria(String nom_cafeteria) {
        this.nom_cafeteria = nom_cafeteria;
    }

    public int getRating_cafeteria() {
        return rating_cafeteria;
    }

    public void setRating_cafeteria(int rating_cafeteria) {
        this.rating_cafeteria = rating_cafeteria;
    }

    public Bitmap getPict_cafeteria() {
        return pict_cafeteria;
    }

    public void setPict_cafeteria(Bitmap pict_cafeteria) {
        this.pict_cafeteria = pict_cafeteria;
    }

    public int getId_usr() {
        return id_usr;
    }

    public void setId_usr(int id_usr) {
        this.id_usr = id_usr;
    }

    public String getNom_usr() {
        return nom_usr;
    }

    public void setNom_usr(String nom_usr) {
        this.nom_usr = nom_usr;
    }

    public Bitmap getPict_usr() {
        return pict_usr;
    }

    public void setPict_usr(Bitmap pict_usr) {
        this.pict_usr = pict_usr;
    }

    public float getLatitut() {
        return latitut;
    }

    public void setLatitut(float latitut) {
        this.latitut = latitut;
    }

    public float getLongitut() {
        return longitut;
    }

    public void setLongitut(float longitut) {
        this.longitut = longitut;
    }
}
