package com.example.cecilia.api_clima;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Cecilia on 20/11/2017.
 */

public class AltaActivity extends AppCompatActivity
{
    private EditText altaId;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta);

        Button Cargar = (Button) findViewById(R.id.botonCargar);
        Cargar.setOnClickListener(new  View.OnClickListener()
        {
            public void onClick(View v)
            {
                cargarCiudad();
            }
        });

    }

    public void cargarCiudad()
    {


        altaId = (EditText) findViewById(R.id.altaId);

        altaEnDB(Integer.parseInt(altaId.getText().toString()));
        Toast.makeText(AltaActivity.this, R.string.ciudad_agregada, Toast.LENGTH_SHORT).show();
        MostrarMainActivity();

    }

    public void altaEnDB(int id)
    {
        CiudadOpenHelper BaseDeDatos = new CiudadOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase bd = BaseDeDatos.getWritableDatabase();
        ContentValues registro = new ContentValues();
        registro.put("id", id);

        bd.insert("Ciudades", null, registro);
        bd.close();

    }


    public void MostrarMainActivity()
    {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

}
