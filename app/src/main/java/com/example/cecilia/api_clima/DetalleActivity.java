package com.example.cecilia.api_clima;

import android.os.Bundle;
import android.support.v4.app.BundleCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Cecilia on 20/11/2017.
 */

public class DetalleActivity extends AppCompatActivity
{
    TextView tvTemperatura;
    TextView tvHumedad;
    TextView tvPresion;
    TextView tvLugar;
    TextView tvPais;
    TextView tvTempMin;
    TextView tvTempMax;


    protected void onCreate (Bundle savedInstanceState)
    {
      super.onCreate(savedInstanceState);
        setContentView(R.layout.detalle_main);

        Bundle parametros = getIntent().getExtras();  			// accede a todos los extras utilizados y declarados en el Intent
        int id = parametros.getInt("id");

        tvTemperatura = (TextView)findViewById(R.id.tvTemperatura);
        tvTempMin = (TextView)findViewById(R.id.tvTemperaturaMin);
        tvTempMax = (TextView)findViewById(R.id.tvTemperaturaMax);

        tvPresion = (TextView) findViewById(R.id.tvPresion);
        tvHumedad = (TextView) findViewById(R.id.tvHumedad);
        tvLugar = (TextView) findViewById(R.id.tvUbicacion);
        tvPais = (TextView) findViewById(R.id.tvPais);

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

                tvLugar.setText(String.valueOf(city.getNombre()));
                tvTemperatura.setText(String.valueOf(city.getClima().getTemperatura())+"º Centigrados");

                tvTempMin.setText(String.valueOf(city.getClima().getTemp_min())+"º Centigrados");
                tvTempMax.setText(String.valueOf(city.getClima().getTemp_max())+"º Centigrados");

                tvHumedad.setText(String.valueOf(city.getClima().getHumedad())+" %");
                tvPresion.setText(String.valueOf(city.getClima().getPresion())+" HPA");
                tvPais.setText(String.valueOf(city.getSys().getPais()));

            }

            @Override
            public void onFailure(Call<Ciudad> call, Throwable t)
            {
                Toast.makeText(DetalleActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
