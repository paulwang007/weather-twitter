package com.example.pwang.weather.network;



import com.example.pwang.weather.MyApplication;
import com.example.pwang.weather.model.NetworkService;
import com.example.pwang.weather.model.WeatherData;
import com.example.pwang.weather.presenter.MainActivityPresenter;

import java.io.File;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.pwang.weather.model.NetworkService.BASE_URL;

public class FetchWeatherData {

    private final MainActivityPresenter presenter;
    private final NetworkService service;

    public FetchWeatherData(MainActivityPresenter presenter) {

        int cacheSize = 10 * 1024 * 1024; // 10 MB
        Cache cache = new Cache(new File(MyApplication.getAppContext().getCacheDir(), "http"), cacheSize);


        this.presenter = presenter;

        OkHttpClient client = new OkHttpClient.Builder()
                .cache(cache)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(NetworkService.class);
    }

    public void fetchCurrentData() {
        service.getCurrentWeather().enqueue(new Callback<WeatherData>() {
            @Override
            public void onResponse(Call<WeatherData> call, Response<WeatherData> response) {
                presenter.onCurrentDataReceived(response.body());
            }

            @Override
            public void onFailure(Call<WeatherData> call, Throwable t) {

            }
        });
    }

    public void fetchNextFiveDaysData() {
        NextFiveDaysCallBack nextFiveDays = new NextFiveDaysCallBack(presenter);
        service.getNextFiveDaysWeather(1).enqueue(nextFiveDays);
        service.getNextFiveDaysWeather(2).enqueue(nextFiveDays);
        service.getNextFiveDaysWeather(3).enqueue(nextFiveDays);
        service.getNextFiveDaysWeather(4).enqueue(nextFiveDays);
        service.getNextFiveDaysWeather(5).enqueue(nextFiveDays);
    }

    static class NextFiveDaysCallBack implements Callback<WeatherData> {
        MainActivityPresenter presenter;
        float[] temperatureData = new float[5];
        int index = 0;

        NextFiveDaysCallBack(MainActivityPresenter presenter) {
            this.presenter = presenter;
        }

        @Override
        public void onResponse(Call<WeatherData> call, Response<WeatherData> response) {
            temperatureData[index++] = response.body().getWeather().getTemp();

            if (index == 5) {
                presenter.onNextFiveDaysDataReceived(temperatureData);
            }
        }

        @Override
        public void onFailure(Call<WeatherData> call, Throwable t) {

        }
    }

}
