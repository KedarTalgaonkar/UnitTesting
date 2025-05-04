package com.example.unittesting;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static java.lang.System.*;

public class WeatherApp {
    private static final String API_KEY = "c71186aa193b1eac7746ecbf580a59f1"; // Replace with your OpenWeatherMap API key
    private static final String BASE_URL = "https://api.openweathermap.org/data/2.5/weather";

    public static void main(String[] args) {
        String city = "London"; // You can change this to any city you want
        String weatherData = getWeatherData(city);

        if (weatherData != null) {
            out.println("Weather data for " + city + ": " + weatherData);
        } else {
            out.println("Failed to fetch weather data.");
        }
    }

    public static String getWeatherData(String city) {
        StringBuilder response = new StringBuilder();
        try {
            // Construct the API URL
            String urlString = BASE_URL + "?q=" + city + "&appid=" + API_KEY + "&units=metric";
            out.println(urlString);
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // Check if the response code is 200 (HTTP_OK)
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;

                // Read the response line by line
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
            } else {
                out.println("GET request not worked: " + conn.getResponseCode());
            }
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return response.toString();
    }
}
