package com.example.cecilia.api_clima;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Cecilia on 20/11/2017.
 */

public interface WeatherService
{
    @GET("weather")
    Call<Ciudad> getCity(@Query("q") String ciudad, @Query("appid") String key);

    @GET("weather")
    Call<Ciudad> getCity(@Query("id") int idCiudad, @Query("appid") String key);

    @GET("weather")
    Call<Ciudad> getCity(@Query("id") int idCiudad, @Query("appid") String key, @Query("units") String value);

    @GET("weather")
    Call<Ciudad> getCity(@Query("id") int idCiudad, @Query("appid") String key, @Query("units") String value, @Query("lang") String lang);

}
