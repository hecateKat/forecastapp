package com.kat.forecastapp.controller;

import com.kat.forecastapp.dto.ForecastDto;
import com.kat.forecastapp.service.ForecastService;
import org.springframework.web.bind.annotation.*;

import javax.naming.ServiceUnavailableException;
import java.io.IOException;

@RestController
public class ForecastController {

    private ForecastService forecastService;

    public ForecastController(ForecastService forecastService) {
        this.forecastService = forecastService;
    }

    @GetMapping("/forecast")
    @ResponseBody
    @ExceptionHandler(ServiceUnavailableException.class)
    public ForecastDto showForecast(@RequestParam String postcode) throws IOException, ServiceUnavailableException {
        return forecastService.createForecastDto(postcode);
    }
}