package com.example.pwang.weather.model;


import com.google.gson.annotations.SerializedName;

public class WeatherData {

    public final static int MAX_CLOUDINESS_TO_DISPLAY = 50;

    @SerializedName("name")
    private String name;
    @SerializedName("clouds")
    private Clouds clouds;
    @SerializedName("rain")
    private Rain rain;
    @SerializedName("wind")
    private Wind wind;
    @SerializedName("weather")
    private Weather weather;
    @SerializedName("coord")
    private Coord coord;

    public String getName() {
        return name;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public Rain getRain() {
        return rain;
    }

    public Wind getWind() {
        return wind;
    }

    public Weather getWeather() {
        return weather;
    }

    public Coord getCoord() {
        return coord;
    }

    public class Clouds {
        private float cloudiness;

        public float getCloudiness() {
            return cloudiness;
        }

    }

    public class Rain {
        @SerializedName("3h")
        private float nextThreeHour;

        public float getNextThreeHour() {
            return nextThreeHour;
        }

    }

    public class Wind {
        private float speed;

        private float deg;

        public float getSpeed() {
            return speed;
        }

        public float getDeg() {
            return deg;
        }

    }

    public class Weather {
        private float humidity;

        private float pressure;

        private float temp;

        public float getHumidity() {
            return humidity;
        }

        public float getPressure() {
            return pressure;
        }

        public float getTemp() {
            return temp;
        }
    }

    public class Coord {
        private float lon;

        private float lat;

        public float getLon() {
            return lon;
        }

        public float getLat() {
            return lat;
        }
    }
}
