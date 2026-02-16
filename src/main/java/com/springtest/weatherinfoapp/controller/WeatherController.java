package com.springtest.weatherinfoapp.controller;

import com.springtest.weatherinfoapp.dto.WeatherResponseDTO;
import com.springtest.weatherinfoapp.service.WeatherService;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping
    public ResponseEntity<WeatherResponseDTO> getWeather(
            @RequestParam String pincode,
            @RequestParam("for_date")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate date) {

        return ResponseEntity.ok(
                weatherService.getWeather(pincode, date));
    }
}
