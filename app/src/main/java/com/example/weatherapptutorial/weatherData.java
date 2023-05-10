package com.example.weatherapptutorial;


import org.json.JSONObject;



public class weatherData {

    private String mTemperature,micon;
    private int mCondition;
    private static String  WeatherType;

    public static weatherData fromJson(JSONObject jsonObject)
    {
//дiстаємо об'єкти Json
        try
        {
            weatherData weatherD=new weatherData(); //створення об'єкта классу weatherData
            weatherD.mCondition=jsonObject.getJSONArray("weather").getJSONObject(0).getInt("id"); //дiстає данi для condition
            weatherD.micon=updateWeatherIcon(weatherD.mCondition); //змiнює iконку
            double tempResult=jsonObject.getJSONObject("main").getDouble("temp")-273.15;
            int roundedValue=(int)Math.rint(tempResult); //повертає цiле число
            weatherD.mTemperature=Integer.toString(roundedValue); //дiстає температуру
            return weatherD;
        }


         catch (Exception e) {
            e.printStackTrace(); //вивiд execption якщо воноє
            return null;
        }


    }


    private static String updateWeatherIcon(int condition)
    {
        if(condition>=0 && condition<=300)
        {
            WeatherType = "Гроза з дощем";
            return "thunderstrom1";
        }
        else if(condition>=300 && condition<=500)
        {
            WeatherType = "Невеликий дощ";
            return "lightrain";
        }
        else if(condition>=500 && condition<=600)
        {
            WeatherType = "Ливень";
            return "shower";
        }
       else  if(condition>=600 && condition<=700)
        {
            WeatherType = "Сніг";
            return "snow2";
        }
        else if(condition>=701 && condition<=771)
        {
            WeatherType = "Туман";
            return "fog";
        }

        else if(condition>=772 && condition<=800)
        {
            WeatherType = "Похмурі хмари";
            return "overcast";
        }
       else if(condition==800)
        {
            WeatherType = "Сонячно";
            return "sunny";
        }
        else if(condition>=801 && condition<=804)
        {
            WeatherType = "Хмарно";
            return "cloudy";
        }
       else  if(condition>=900 && condition<=902)
        {
            WeatherType = "Гроза з дощем";
            return "thunderstrom1";
        }
        if(condition==903)
        {
            WeatherType = "Сніг";
            return "snow1";
        }
        if(condition==904)
        {
            WeatherType = "Сонячно";
            return "sunny";
        }
        if(condition>=905 && condition<=1000)
        {
            WeatherType = "Сильна гроза";
            return "thunderstrom2";
        }

        return "dunno";


    }

    public String getmTemperature() {
        return mTemperature+"°C";
    }

    public String getMicon() {
        return micon;
    }

    public String getmWeatherType() {
        return WeatherType;
    }
}
