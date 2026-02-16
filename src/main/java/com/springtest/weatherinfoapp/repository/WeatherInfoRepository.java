package com.springtest.weatherinfoapp.repository;


import com.springtest.weatherinfoapp.entity.PincodeLocation;
import com.springtest.weatherinfoapp.entity.WeatherInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface WeatherInfoRepository
        extends JpaRepository<WeatherInfo, Long> {

    Optional<WeatherInfo> findByPincodeLocationAndWeatherDate(
            PincodeLocation location,
            LocalDate date
    );
}
