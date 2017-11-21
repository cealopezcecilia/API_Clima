package com.example.cecilia.api_clima;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Cecilia on 20/11/2017.
 */

public class Ciudad
{
    private int id;
    private String nombre;

    @SerializedName("sys")
    private Sys sys;

    @SerializedName("main")
    private Clima clima;

    public Ciudad(int id, Sys sys, String nombre, Clima clima)
    {
        this.id = id;
        this.sys = sys;
        this.nombre = nombre;
        this.clima = clima;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public Sys getSys()
    {
        return sys;
    }

    public void setSys(Sys sys)
    {
        this.sys = sys;
    }

    public static Sys parseJSON1(String response)
    {
        Gson gson = new GsonBuilder().create();
        Sys sys = gson.fromJson(response,Sys.class);
        return sys;
    }


    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public Clima getClima()
    {
        return clima;
    }

    public void setClima(Clima clima)
    {
        this.clima = clima;
    }

    public static Clima parseJSON(String response)
    {
        Gson gson = new GsonBuilder().create();
        Clima clima = gson.fromJson(response,Clima.class);
        return clima;
    }
}
