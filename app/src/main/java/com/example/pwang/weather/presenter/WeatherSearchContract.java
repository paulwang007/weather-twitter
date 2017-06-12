package com.example.pwang.weather.presenter;

import com.example.pwang.weather.model.WeatherData;

public interface WeatherSearchContract {

    public interface DisplayData {
        void displayCityName(String cityName);
        void displayTemperature(String temperature);
        void displayWindSpeed(float windSpeed);
        void displayCloudImage();
        void displayTemperatureDeviation(String deviation);
        void showProgressBar();
        void hideProgressBar();
    }

    public interface UserAction {
        void searchWeather();
        void searchNextFiveDays();
    }

    public interface ReturnDataReceived {
        void onCurrentDataReceived(WeatherData data);
        void onNextFiveDaysDataReceived(float[] data);
    }

}
