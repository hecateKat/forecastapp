package com.kat.forecastapp.service;

import com.kat.forecastapp.dto.ForecastDto;

import javax.naming.ServiceUnavailableException;
import java.io.IOException;

public interface ForecastService {

    ForecastDto createForecastDto(String postcode) throws IOException, ServiceUnavailableException;

}