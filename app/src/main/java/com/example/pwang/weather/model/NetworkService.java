package com.example.pwang.weather.model;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface NetworkService {

    public final static String BASE_URL
            = "http://twitter-code-challenge.s3-website-us-east-1.amazonaws.com/";

    @GET("current.json")
    Call<WeatherData> getCurrentWeather();

    @GET("future_{day}.json")
    Call<WeatherData> getNextFiveDaysWeather(@Path("day") int day);
}
