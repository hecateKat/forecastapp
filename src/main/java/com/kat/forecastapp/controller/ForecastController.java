package com.kat.forecastapp.controller;

import com.kat.forecastapp.dto.DayDto;
import com.kat.forecastapp.dto.ForecastDto;
import com.kat.forecastapp.service.ForecastService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class ForecastController {

    private ForecastService forecastService;

    public ForecastController(ForecastService forecastService) {
        this.forecastService = forecastService;
    }

    @GetMapping("/cokolwiek")
    @ResponseBody
    public String showPostCode(@RequestParam String postcode) throws IOException {
        return forecastService.getLocation(postcode).getKey();
    }

    @GetMapping("/cokolwiek2")
    @ResponseBody
    public String showVoivodeship(@RequestParam String postcode) throws IOException {
        return forecastService.getVoivodeship(postcode);
    }

    @GetMapping("/cokolwiek3")
    @ResponseBody
    public ForecastDto showForecast(@RequestParam String postcode) throws IOException {
        ForecastDto forecastDto = ForecastDto.builder()
                .voivodeship(forecastService.getVoivodeship(postcode))
                .build();

        return forecastDto;
    }

    @GetMapping("/cokolwiek4")
    @ResponseBody
    public List<DayDto> showDailyWeatcher(@RequestParam String postcode) throws IOException {
        return forecastService.getDailyWeather(postcode);
    }

}