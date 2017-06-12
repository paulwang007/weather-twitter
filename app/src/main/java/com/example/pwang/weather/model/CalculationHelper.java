package com.example.pwang.weather.model;

public class CalculationHelper {

    public static float convertCelsiusFahrenheit(float celsius) {
        return celsius * 9 / 5 + 32;
    }

    public static float calculateStandardDeviation(float[] data) {
        int n = data.length;
        int total = 0;
        int diffSqrt = 0;

        for(float f : data) {
            total += f;
        }

        int average = total / n;

        for (float f : data) {
            diffSqrt += Math.pow((f - average), 2);
        }

        return (float) Math.sqrt(diffSqrt / (n - 1));

    }

}
