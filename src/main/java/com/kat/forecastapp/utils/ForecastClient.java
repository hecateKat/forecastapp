package com.kat.forecastapp.utils;

import com.kat.forecastapp.dto.ForecastDto;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

public interface ForecastClient {

    @RequestLine("GET /{postCode}")
    ForecastResource findByPostCode(@Param("postCode")String postCode);

    @RequestLine("POST")
    @Headers("Content-Type: application/json")
    void create (ForecastDto forecastDto);
}
