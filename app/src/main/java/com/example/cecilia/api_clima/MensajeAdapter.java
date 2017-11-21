package com.example.cecilia.api_clima;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Cecilia on 20/11/2017.
 */

public class MensajeAdapter extends BaseAdapter
{

    private List<Ciudad> ciudades;

    public MensajeAdapter(List<Ciudad> ciudades)
    {
        this.ciudades = ciudades;
    }


    @Override
    public int getCount()
    {
        return ciudades.size();
    }

    @Override
    public Object getItem(int position)
    {
        return ciudades.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return ciudades.get(position).getId();

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View view;
        String recortar_cadena;

        if (convertView == null) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_mensaje, parent, false);
        } else {
            view = convertView;
        }

        Ciudad city = (Ciudad) getItem(position);

        TextView pais = (TextView) view.findViewById(R.id.pais);
        TextView ciudad = (TextView) view.findViewById(R.id.ciudad);
        TextView temp = (TextView) view.findViewById(R.id.temp);


        int flagOffset = 0x1F1E6;
        int asciiOffset = 0x41;

        String country = city.getSys().getPais();

        int firstChar = Character.codePointAt(country, 0) - asciiOffset + flagOffset;
        int secondChar = Character.codePointAt(country, 1) - asciiOffset + flagOffset;

        String flag = new String(Character.toChars(firstChar))
                + new String(Character.toChars(secondChar));


        ciudad.setText(city.getNombre());

        pais.setText(flag);

        float f = (city.getClima().getTemperatura());
        String s = Float.toString(f);
        s = s + "º Cent.";


        temp.setText(s);

        return view;
    }
}
