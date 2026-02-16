package com.springtest.weatherinfoapp.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "weather_info",
        uniqueConstraints = @UniqueConstraint(columnNames = {"pincode_id", "weatherDate"}))
public class WeatherInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pincode_id", nullable = false)
    private PincodeLocation pincodeLocation;

    private LocalDate weatherDate;

    private Double temperature;

    private Integer humidity;

    private String description;

    // Getters and Setters
    public Long getId() { return id; }

    public PincodeLocation getPincodeLocation() { return pincodeLocation; }

    public void setPincodeLocation(PincodeLocation pincodeLocation) {
        this.pincodeLocation = pincodeLocation;
    }

    public LocalDate getWeatherDate() { return weatherDate; }

    public void setWeatherDate(LocalDate weatherDate) {
        this.weatherDate = weatherDate;
    }

    public Double getTemperature() { return temperature; }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Integer getHumidity() { return humidity; }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    public String getDescription() { return description; }

    public void setDescription(String description) {
        this.description = description;
    }
}
