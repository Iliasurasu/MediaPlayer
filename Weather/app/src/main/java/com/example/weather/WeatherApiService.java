package com.example.weather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApiService {

    // Метод для получения текущей погоды по координатам
    @GET("weather")
    Call<CurrentWeatherResponse> getCurrentWeather(
            @Query("lat") double lat,
            @Query("lon") double lon,
            @Query("appid") String apiKey,
            @Query("units") String units
    );

    // Метод для получения прогноза погоды на несколько дней по координатам
    @GET("forecast")
    Call<WeatherForecastResponse> getWeatherForecast(
            @Query("lat") double lat,
            @Query("lon") double lon,
            @Query("appid") String apiKey,
            @Query("units") String units
    );

    // Метод для получения текущей погоды по названию города
    @GET("weather")
    Call<CurrentWeatherResponse> getCurrentWeatherByCity(
            @Query("q") String cityName,
            @Query("appid") String apiKey,
            @Query("units") String units
    );

    // Метод для получения прогноза погоды по названию города
    @GET("forecast")
    Call<WeatherForecastResponse> getWeatherForecastByCity(
            @Query("q") String cityName,
            @Query("appid") String apiKey,
            @Query("units") String units
    );
}
