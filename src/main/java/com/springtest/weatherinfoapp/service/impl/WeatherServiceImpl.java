package com.springtest.weatherinfoapp.service.impl;

import com.springtest.weatherinfoapp.client.OpenWeatherClient;
import com.springtest.weatherinfoapp.dto.WeatherResponseDTO;
import com.springtest.weatherinfoapp.entity.PincodeLocation;
import com.springtest.weatherinfoapp.entity.WeatherInfo;
import com.springtest.weatherinfoapp.repository.PincodeLocationRepository;
import com.springtest.weatherinfoapp.repository.WeatherInfoRepository;
import com.springtest.weatherinfoapp.service.WeatherService;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Map;

@Service
public class WeatherServiceImpl implements WeatherService {

    private final PincodeLocationRepository locationRepository;
    private final WeatherInfoRepository weatherRepository;
    private final OpenWeatherClient weatherClient;

    public WeatherServiceImpl(
            PincodeLocationRepository locationRepository,
            WeatherInfoRepository weatherRepository,
            OpenWeatherClient weatherClient) {

        this.locationRepository = locationRepository;
        this.weatherRepository = weatherRepository;
        this.weatherClient = weatherClient;
    }

    @Override
    public WeatherResponseDTO getWeather(String pincode, LocalDate date) {

        // 1️⃣ Check if pincode exists
        PincodeLocation location = locationRepository.findByPincode(pincode)
                .orElseGet(() -> {
                    // Hardcoded lat/long for demo (should call geocoding API)
                    PincodeLocation newLoc = new PincodeLocation();
                    newLoc.setPincode(pincode);
                    newLoc.setLatitude(18.5204);
                    newLoc.setLongitude(73.8567);
                    return locationRepository.save(newLoc);
                });

        // 2️⃣ Check if weather already exists
        return weatherRepository
                .findByPincodeLocationAndWeatherDate(location, date)
                .map(w -> new WeatherResponseDTO(
                        w.getTemperature(),
                        w.getHumidity(),
                        w.getDescription()))
                .orElseGet(() -> {

                    Map<String, Object> response =
                            weatherClient.fetchWeather(
                                    location.getLatitude(),
                                    location.getLongitude());

                    Map<String, Object> main =
                            (Map<String, Object>) response.get("main");

                    Double temp =
                            Double.valueOf(main.get("temp").toString());

                    Integer humidity =
                            Integer.valueOf(main.get("humidity").toString());

                    String description =
                            ((Map<String, Object>) ((java.util.List<?>) response.get("weather")).get(0))
                                    .get("description").toString();

                    WeatherInfo weather = new WeatherInfo();
                    weather.setPincodeLocation(location);
                    weather.setWeatherDate(date);
                    weather.setTemperature(temp);
                    weather.setHumidity(humidity);
                    weather.setDescription(description);

                    weatherRepository.save(weather);

                    return new WeatherResponseDTO(temp, humidity, description);
                });
    }
}
