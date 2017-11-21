package com.example.cecilia.api_clima;

/**
 * Created by Cecilia on 20/11/2017.
 */

public class Sys
{
    private int tipo;
    private float id;
    private float mensaje;
    private String pais;
    private float amanecer;
    private float atardecer;

public Sys ()
{

}

    public Sys(int tipo, float id, float mensaje, String pais, float amanecer, float atardecer)
    {
        this.tipo = tipo;
        this.id = id;
        this.mensaje = mensaje;
        this.pais = pais;
        this.amanecer = amanecer;
        this.atardecer = atardecer;
    }

    public int getTipo()
    {
        return tipo;
    }

    public void setTipo(int tipo)
    {
        this.tipo = tipo;
    }

    public float getId()
    {
        return id;
    }

    public void setId(float id)
    {
        this.id = id;
    }

    public float getMensaje()
    {
        return mensaje;
    }

    public void setMensaje(float mensaje)
    {
        this.mensaje = mensaje;
    }

    public String getPais()
    {
        return pais;
    }

    public void setPais(String pais)
    {
        this.pais = pais;
    }

    public float getAmanecer()
    {
        return amanecer;
    }

    public void setAmanecer(float amanecer)
    {
        this.amanecer = amanecer;
    }

    public float getAtardecer()
    {
        return atardecer;
    }

    public void setAtardecer(float atardecer)
    {
        this.atardecer = atardecer;
    }

}
