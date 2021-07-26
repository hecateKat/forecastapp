package com.kat.forecastapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kat.forecastapp.model.location.LocationModel;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class ForecastController {

    private static final String api_key = "G9pAprFJYPJtCR1623GUFZgbKVNysUBc";

    @GetMapping("/cokolwiek")
    @ResponseBody
    public String showPostCode(@RequestParam String postcode) throws IOException {
        LocationModel location = getLocation(postcode);
//        String voivodeship =
        return getLocation(postcode).getKey();
    }

    public LocationModel getLocation(String postcode) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url("http://dataservice.accuweather.com/locations/v1/postalcodes/search?q=" + postcode + "&apikey=" + api_key)
                .method("GET", null)
                .build();
        Response response = client.newCall(request).execute();

        JSONArray array = new JSONArray(response.body().string());

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            LocationModel location = objectMapper.readValue(array.get(0).toString(), LocationModel.class);
            return location;
        } catch (IOException e) {
            e.getStackTrace();
        }
        return null;
    }


}