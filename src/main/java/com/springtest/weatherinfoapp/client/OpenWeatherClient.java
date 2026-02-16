package com.springtest.weatherinfoapp.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class OpenWeatherClient {

    private final RestTemplate restTemplate;

    @Value("${openweather.api.key}")
    private String apiKey;

    public OpenWeatherClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Map<String, Object> fetchWeather(double lat, double lon) {

        String url = "https://api.openweathermap.org/data/2.5/weather"
                + "?lat=" + lat
                + "&lon=" + lon
                + "&appid=" + apiKey
                + "&units=metric";

        return restTemplate.getForObject(url, Map.class);
    }
}
