package com.example.cecilia.api_clima;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity
{

    private ListView listaCiudades;
    List<Ciudad> ciudades = new ArrayList<>();
    private SwipeRefreshLayout sflLista;
    private Context context;
    private MensajeAdapter adapter;



    public void baja(long id)
    {
        CiudadOpenHelper BaseDeDatos = new CiudadOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase bd = BaseDeDatos.getWritableDatabase();
        bd.delete("Ciudades", "id=" + id, null);
        bd.close();
    }


    private void cargarLista()
    {

        insertaCiudadesFijas();
        cargarDesdeDB();
        Toast.makeText(MainActivity.this, R.string.ciudades_cargadas, Toast.LENGTH_LONG).show();

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


    void insertaCiudadesFijas()
    {
        altaEnDB(3835994); //santa rosa
        altaEnDB(524901);   //moscu
        altaEnDB(2643743);  //londres
        altaEnDB(6167865);  //toronto
        altaEnDB(3117735);  //madrid
        altaEnDB(2147714);  //sydney
        altaEnDB(5815135);  //washington DC


    }


    public void obtenerDatos(final int id)
    {
        final String BASE_URL = "http://api.openweathermap.org/data/2.5/";
        final String KEY_API = "4f49695cdc4ae71729166400046cd5ef";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WeatherService service = retrofit.create(WeatherService.class);

        Call<Ciudad> cityCall = service.getCity(id, KEY_API, "metric", "es");


        cityCall.enqueue(new Callback<Ciudad>()
        {
            @Override
            public void onResponse(Call<Ciudad> call, Response<Ciudad> response)
            {
                Ciudad city = response.body();

                Clima clima_actual = new Clima();
                Sys sysactual = new Sys();
                String ciudad2 = String.valueOf(city.getNombre());
                sysactual.setPais(String.valueOf(city.getSys().getPais()));
                clima_actual.setTemperatura((city.getClima().getTemperatura()));

                ciudades.add(new Ciudad(id, sysactual, ciudad2, clima_actual));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Ciudad> call, Throwable t)
            {
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void cargarDesdeDB()
    {
        CiudadOpenHelper BaseDeDatos = new CiudadOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase bd = BaseDeDatos.getReadableDatabase();

        Cursor cursor = bd.rawQuery("SELECT * FROM TCiudades", null);
        ciudades.clear();

        if (cursor.moveToFirst())
        {
            while (cursor.isAfterLast() == false)
            {
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                obtenerDatos(id);
                cursor.moveToNext();
            }
        }
        bd.close();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        setContentView(R.layout.activity_main);

        context = MainActivity.this;
        listaCiudades = (ListView) findViewById(R.id.lista_citys);

        adapter = new MensajeAdapter(ciudades); //ver
        listaCiudades.setAdapter(adapter);      //ver


        sflLista = (SwipeRefreshLayout) findViewById(R.id.sflLista);
        registerForContextMenu(listaCiudades);

        cargarLista();

        listaCiudades.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Ciudad item = (Ciudad) listaCiudades.getItemAtPosition(position);
                Intent i;
                i = new Intent(context, DetalleActivity.class);
                i.putExtra("id", item.getId());

                startActivity(i);
            }
        });

        sflLista.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
        {
            @Override
            public void onRefresh()
            {

                sflLista.setRefreshing(false);
            }
        });
    }


    public void MostrarAltaActivity()
    {
        Intent i = new Intent(this, AltaActivity.class);
        startActivity(i);
    }
}
