package com.springtest.weatherinfoapp.service;

import com.springtest.weatherinfoapp.dto.WeatherResponseDTO;
import java.time.LocalDate;

public interface WeatherService {

    WeatherResponseDTO getWeather(String pincode, LocalDate date);
}
