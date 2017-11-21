package com.example.cecilia.api_clima;

/**
 * Created by Cecilia on 20/11/2017.
 */

public class Clima
{
    private float temperatura;
    private float presion;
    private float humedad;
    private float temp_min;
    private float temp_max;

    public Clima ()
    {

    }

    public Clima(float temperatura, float presion, float humedad,float temp_min, float temp_max)
    {
        this.temperatura = temperatura;
        this.temp_min = temp_min;
        this.temp_max = temp_max;
        this.presion = presion;
        this.humedad = humedad;
    }

    public float getTemperatura()
    {
        return temperatura;
    }

    public void setTemperatura(float temperatura)
    {
        this.temperatura = temperatura;
    }

    public float getPresion()
    {
        return presion;
    }

    public void setPresion(float presion)
    {
        this.presion = presion;
    }

    public float getHumedad()
    {
        return humedad;
    }

    public void setHumedad(float humedad)
    {
        this.humedad = humedad;
    }

    public float getTemp_min()
    {
        return temp_min;
    }

    public void setTemp_min(float temp_min)
    {
        this.temp_min = temp_min;
    }

    public float getTemp_max()
    {
        return temp_max;
    }

    public void setTemp_max(float temp_max)
    {
        this.temp_max = temp_max;
    }
}
