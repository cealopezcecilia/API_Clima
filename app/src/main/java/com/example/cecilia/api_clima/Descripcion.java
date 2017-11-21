package com.example.cecilia.api_clima;

/**
 * Created by Cecilia on 20/11/2017.
 */

public class Descripcion
{
    private String main;
    private String descripcion;
    private String icon;


    public Descripcion(String main, String descripcion, String icon)
    {
        this.main = main;
        this.descripcion = descripcion;
        this.icon = icon;
    }

    public String getMain()
    {
        return main;
    }

    public void setMain(String main)
    {
        this.main = main;
    }

    public String getDescripcion()
    {
        return descripcion;
    }

    public void setDescripcion(String descripcion)
    {
        this.descripcion = descripcion;
    }

    public String getIcon()
    {
        return icon;
    }

    public void setIcon(String icon)
    {
        this.icon = icon;
    }
}
