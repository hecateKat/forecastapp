package com.kat.forecastapp.service.implementation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kat.forecastapp.dto.DayDto;
import com.kat.forecastapp.dto.ForecastDto;
import com.kat.forecastapp.model.location.LocationModel;
import com.kat.forecastapp.service.ForecastService;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class ForecastServiceImpl implements ForecastService {

    private static final String api_key = "G9pAprFJYPJtCR1623GUFZgbKVNysUBc";

    public LocationModel getLocation(String postcode) throws IOException {
        String stringResponse = getStringResponseFromLocation(postcode);
        JSONArray array = new JSONArray(stringResponse);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            LocationModel location = objectMapper.readValue(array.get(0).toString(), LocationModel.class);
            return location;
        } catch (IOException e) {
            e.getStackTrace();
        }
        return null;
    }

    public String getVoivodeship(String postcode) throws IOException {
        String stringResponse = getStringResponseFromLocation(postcode);
        JSONArray array = new JSONArray(stringResponse);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            LocationModel location = objectMapper.readValue(array.get(0).toString(), LocationModel.class);
            String voivodeship = location.getAdministrativeArea().getLocalizedName();
            return voivodeship;
        } catch (IOException e) {
            e.getStackTrace();
        }
        return null;
    }

    @Override
    public ForecastDto getForecast(String postcode) throws IOException {
        String locationKey = getLocation(postcode).getKey();
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url("http://dataservice.accuweather.com/forecasts/v1/daily/5day/" + locationKey + "?apikey=" + api_key)
                .method("GET", null)
                .build();
        Response response = client.newCall(request).execute();
        return null;
    }

    @Override
    public List<DayDto> getDailyWeather(String postcode) throws IOException {
        String locationKey = getLocation(postcode).getKey();
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url("http://dataservice.accuweather.com/forecasts/v1/daily/5day/" + locationKey + "?apikey=" + api_key)
                .method("GET", null)
                .build();
        Response response = client.newCall(request).execute();
        String stringResponse = response.body().toString();
        JSONArray array = new JSONArray(stringResponse);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            DayDto[] days = objectMapper.readValue(array.toString(), DayDto[].class);
            List<DayDto> listOfDays = Arrays.asList(days);
            return listOfDays;
            } catch (IOException e) {
            e.getStackTrace();
        }
        return null;
    }

    private String getStringResponseFromLocation(String postcode) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url("http://dataservice.accuweather.com/locations/v1/postalcodes/search?q=" + postcode + "&apikey=" + api_key)
                .method("GET", null)
                .build();
        Response response = client.newCall(request).execute();

        String stringResponse = response.body().string();
        return stringResponse;
    }
}