package com.example.pwang.weather.presenter;

import com.example.pwang.weather.R;
import com.example.pwang.weather.model.CalculationHelper;
import com.example.pwang.weather.model.WeatherData;
import com.example.pwang.weather.network.FetchWeatherData;
import com.example.pwang.weather.view.MainActivity;

import java.lang.ref.WeakReference;

import static com.example.pwang.weather.model.WeatherData.MAX_CLOUDINESS_TO_DISPLAY;

public class MainActivityPresenter implements WeatherSearchContract.UserAction, WeatherSearchContract.ReturnDataReceived {

    private final WeakReference<MainActivity> weakReference;
    private final FetchWeatherData fetchWeatherData;

    public MainActivityPresenter(MainActivity activity) {
        weakReference = new WeakReference<>(activity);
        fetchWeatherData = new FetchWeatherData(this);
    }

    @Override
    public void searchWeather() {
        fetchWeatherData.fetchCurrentData();
        MainActivity activity = weakReference.get();
        if (activity != null) {
            activity.showProgressBar();
        }
    }

    @Override
    public void searchNextFiveDays() {
        fetchWeatherData.fetchNextFiveDaysData();
        MainActivity activity = weakReference.get();
        if (activity != null) {
            activity.showProgressBar();
        }
    }

    @Override
    public void onCurrentDataReceived(WeatherData data) {
        MainActivity activity = weakReference.get();
        if (activity != null) {
            float celsius = data.getWeather().getTemp();
            float fahrenheit = CalculationHelper.convertCelsiusFahrenheit(celsius);

            activity.displayTemperature(activity.getString(R.string.temperature, celsius, fahrenheit));
            activity.displayCityName(data.getName());
            activity.displayWindSpeed(data.getWind().getSpeed());
            if (data.getClouds().getCloudiness() > MAX_CLOUDINESS_TO_DISPLAY) {
                activity.displayCloudImage();
            }

            activity.hideProgressBar();
        }
    }

    @Override
    public void onNextFiveDaysDataReceived(float[] data) {
        float standardDeviation = CalculationHelper.calculateStandardDeviation(data);
        MainActivity activity = weakReference.get();
        if (activity != null) {
            activity.displayTemperatureDeviation(activity.getString(R.string.standard_deviation, standardDeviation));
            activity.hideProgressBar();
        }

    }

}
