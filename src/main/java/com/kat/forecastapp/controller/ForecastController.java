package com.kat.forecastapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kat.forecastapp.model.location.LocationModel;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import java.io.DataInput;
import java.io.IOException;

@RestController
public class ForecastController {

    @GetMapping("/cokolwiek")
    @ResponseBody
    public String showPostCode(@RequestParam String postcode) throws IOException {
//        return getLocation(postcode);
        return getLocation(postcode).getKey();
    }

    public LocationModel getLocation(String postcode) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url("http://dataservice.accuweather.com/locations/v1/postalcodes/search?q=20-570&apikey=G9pAprFJYPJtCR1623GUFZgbKVNysUBc")
                .method("GET", null)
                .build();
        Response response = client.newCall(request).execute();

//
//        String jsonStr = response.body().string();
////
////        JSONArray array = new JSONArray(response.body().string());
//
//////
        LocationModel location = null;
//        try {
//            ObjectMapper objectMapper = new ObjectMapper();
//        okhttp3.ResponseBody responseBody = client.newCall(request).execute().body();
//        LocationModel location = objectMapper.readValue(responseBody.string(), LocationModel.class);
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
        return location;
    }
}