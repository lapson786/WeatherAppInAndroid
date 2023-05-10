package com.example.weatherapptutorial;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {


    final String APP_ID = "dab3af44de7d24ae7ff86549334e45bd";
    final String WEATHER_URL = "https://api.openweathermap.org/data/2.5/weather";

    TextView NameofCity, weatherState, Temperature;
    ImageView mweatherIcon;

    RelativeLayout mCityFinder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); //створення cуперкласу
        setContentView(R.layout.activity_main);//пiдгрузка актiвiтi
// Зв'язування елементів у макеті з кодом Java
        weatherState = findViewById(R.id.weatherCondition);
        Temperature = findViewById(R.id.temperature);
        mweatherIcon = findViewById(R.id.weatherIcon);
        mCityFinder = findViewById(R.id.cityFinder);
        NameofCity = findViewById(R.id.cityName);


        mCityFinder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, cityFinder.class);//перехiд мiж актiвiтi
                startActivity(intent);
            }
        }); //перехiд на новий екран при клiку

    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent mIntent=getIntent();
        String city= mIntent.getStringExtra("City");
        //отримання назви мiста з cityFinder через iнтент та перевiрка на порожнiсть вводу
        if(city!=null)
        {
            getWeatherForNewCity(city);
        }

    }


    private void getWeatherForNewCity(String city)
    {
        RequestParams params=new RequestParams();
        params.put("q",city);
        params.put("appid",APP_ID);
        letsdoSomeNetworking(params);

    }

    private  void letsdoSomeNetworking(RequestParams params)
    {
        AsyncHttpClient client = new AsyncHttpClient(); //створення асинхронного http клиента
                client.get(WEATHER_URL,params,new JsonHttpResponseHandler()//вхiд на сайт та отримання даних
        {
            @Override //якщо данi отриманi успiшно
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                Toast.makeText(MainActivity.this,"Data Get Success",Toast.LENGTH_SHORT).show();//вивiд користувачу

                weatherData weatherD=weatherData.fromJson(response); //обровка вiдповiдi вiд iншого активiтi
                updateUI(weatherD);
            }


            @Override //якщо данi не отриманi
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });
    }

    private  void updateUI(weatherData weather){
        //вивiд даних користувачу
        Intent mIntent=getIntent();
        String city= mIntent.getStringExtra("City");
        Temperature.setText(weather.getmTemperature());
        NameofCity.setText(city);
        weatherState.setText(weather.getmWeatherType());
        int resourceID=getResources().getIdentifier(weather.getMicon(),"drawable",getPackageName());
        mweatherIcon.setImageResource(resourceID);

    }
}
