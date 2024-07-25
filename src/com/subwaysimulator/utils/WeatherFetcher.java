package com.subwaysimulator.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherFetcher {

    private static final String API_KEY = "15d15f05ab9e16a64630ecf3ce6da823"; // Your OpenWeatherMap API key

    public static String fetchWeather(String cityCode) throws Exception {
        String urlString = "http://api.openweathermap.org/data/2.5/weather?id=" + cityCode + "&appid=" + API_KEY + "&units=metric";
        System.out.println("Fetching weather data from: " + urlString); // Debugging line
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        int responseCode = conn.getResponseCode();
        System.out.println("Response Code: " + responseCode); // Debugging line

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }

        in.close();
        conn.disconnect();

        System.out.println("Weather Data: " + content.toString()); // Debugging line
        return content.toString();
    }
}
