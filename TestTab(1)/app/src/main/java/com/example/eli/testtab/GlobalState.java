package com.example.eli.testtab;

import android.app.Application;

/**
 * Created by Eli on 03/01/2018.
 */

public class GlobalState extends Application
{
    private int id_cafeteria;
    private int id_user;
    private float latitut;
    private float longitut;

    public int getId_cafeteria() {
        return id_cafeteria;
    }

    public void setId_cafeteria(int id_cafeteria) {
        this.id_cafeteria = id_cafeteria;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
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
