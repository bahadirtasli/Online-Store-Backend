package com.example.onlinestorebackend.models;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;

public class WeatherWidget {
    public static void main(String[] args) {
        try {

            double latitude = 59.436962;
            double longitude = 24.753574;


            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("http://dataservice.accuweather.com/currentconditions/v1" + latitude +
                            "&lon=" + longitude + "&units=metric&appid=XyA1wh9gyHixmRaVfdBw7gMefxg39StV")
                    .build();
            Response response = client.newCall(request).execute();
            String json = response.body().string();


            JSONObject jsonObject = new JSONObject(json);
            double temperature = jsonObject.getJSONObject("main").getDouble("temp");
            double humidity = jsonObject.getJSONObject("main").getDouble("humidity");
            double windSpeed = jsonObject.getJSONObject("wind").getDouble("speed");


            System.out.println("Temperature: " + temperature + "°C");
            System.out.println("Humidity: " + humidity + "%");
            System.out.println("Wind Speed: " + windSpeed + " m/s");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}