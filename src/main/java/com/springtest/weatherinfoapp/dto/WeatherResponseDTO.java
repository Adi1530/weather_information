package com.springtest.weatherinfoapp.dto;

public class WeatherResponseDTO {

    private Double temperature;
    private Integer humidity;
    private String description;

    public WeatherResponseDTO(Double temperature, Integer humidity, String description) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.description = description;
    }

    public Double getTemperature() { return temperature; }
    public Integer getHumidity() { return humidity; }
    public String getDescription() { return description; }
}
