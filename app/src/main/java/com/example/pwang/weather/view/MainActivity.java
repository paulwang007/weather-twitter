package com.example.pwang.weather.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.pwang.weather.R;
import com.example.pwang.weather.presenter.MainActivityPresenter;
import com.example.pwang.weather.presenter.WeatherSearchContract;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

public class MainActivity extends AppCompatActivity implements WeatherSearchContract.DisplayData {

    private final static String TEMPERATURE_STANDARD_DEVIATION_KEY = "temp_deviation";
    private MainActivityPresenter presenter;

    @Bind(R.id.progress_bar)
    ProgressBar progressBar;

    @Bind(R.id.city_name)
    TextView cityNameText;

    @Bind(R.id.temperature)
    TextView temperatureText;

    @Bind(R.id.wind_speed)
    TextView windSpeedText;

    @Bind(R.id.cloud)
    ImageView cloudImage;

    @Bind(R.id.temperature_deviation)
    TextView tempDeviationText;

    @OnClick(R.id.next_five_days)
    public void onNextFiveDaysClicked() {
        presenter.searchNextFiveDays();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        if (savedInstanceState != null) {
            tempDeviationText.setText(savedInstanceState.getString(TEMPERATURE_STANDARD_DEVIATION_KEY));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter = new MainActivityPresenter(this);
        presenter.searchWeather();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(TEMPERATURE_STANDARD_DEVIATION_KEY, tempDeviationText.getText().toString());
    }

    @Override
    public void displayCityName(String name) {
        cityNameText.setText(name);
    }

    @Override
    public void displayTemperature(String temperature) {
        temperatureText.setText(temperature);
    }

    @Override
    public void displayWindSpeed(float windSpeed) {
        windSpeedText.setText(getString(R.string.wind_speed, windSpeed));
    }

    @Override
    public void displayCloudImage() {
        cloudImage.setVisibility(VISIBLE);
    }

    @Override
    public void displayTemperatureDeviation(String deviation) {
        tempDeviationText.setText(deviation);
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(INVISIBLE);
    }


}
