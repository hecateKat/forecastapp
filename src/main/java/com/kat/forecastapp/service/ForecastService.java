package com.kat.forecastapp.service;

import com.kat.forecastapp.dto.DayDto;
import com.kat.forecastapp.dto.ForecastDto;
import com.kat.forecastapp.model.location.LocationModel;

import java.io.IOException;
import java.util.List;

public interface ForecastService {

    LocationModel getLocation(String postcode) throws IOException;
    String getVoivodeship(String postcode) throws IOException;
    ForecastDto getForecast(String postcode) throws IOException;
    List<DayDto> getDailyWeather(String postcode) throws IOException;

}
