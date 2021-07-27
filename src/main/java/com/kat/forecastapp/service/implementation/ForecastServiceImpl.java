package com.kat.forecastapp.service.implementation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kat.forecastapp.dto.DayDto;
import com.kat.forecastapp.dto.ForecastDto;
import com.kat.forecastapp.model.forecast.DailyForecast;
import com.kat.forecastapp.model.location.LocationModel;
import com.kat.forecastapp.service.ForecastService;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.naming.ServiceUnavailableException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ForecastServiceImpl implements ForecastService {

    private static final String api_key = "ebXYLf8vwKFy5DWebf2wOxQWRGvAoozH&metric=true";

    private static final String urlPostcode = "http://dataservice.accuweather.com/locations/v1/postalcodes/search";
    private static final String urlLocationKey = "http://dataservice.accuweather.com/forecasts/v1/daily/5day/";

    private static int count;

    @Override
    public ForecastDto createForecastDto(String postcode) throws IOException, ServiceUnavailableException {
        LocationModel locationModel = getLocation(postcode);
        List<DailyForecast> dailyWeather = getDailyWeather(locationModel.getKey());
        return ForecastDto.builder()
                .voivodeship(locationModel.getAdministrativeArea().getLocalizedName())
                .days(createDayDto(dailyWeather))
                .apiCallCounter(count)
                .build();
    }

    private LocationModel getLocation(String postcode) throws IOException, ServiceUnavailableException {
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

    private List<DailyForecast> getDailyWeather(String locationKey) throws IOException, ServiceUnavailableException {
        OkHttpClient client = getOkHttpClient();
        Request request = getRequestUsingLocationKey(locationKey);
        Response response = client.newCall(request).execute();
        if (response.code() == 503){
            throw new ServiceUnavailableException() ;
        }
        String stringResponse = response.body().string();
        JSONObject jsonObject = new JSONObject(stringResponse);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            DailyForecast[] days = objectMapper.readValue(jsonObject.get("DailyForecasts").toString(), DailyForecast[].class);

            List<DailyForecast> listOfDays = Arrays.asList(days);
            return listOfDays;
        } catch (IOException e) {
            e.getStackTrace();
        }
        return null;
    }

    private List<DayDto> createDayDto(List<DailyForecast> dailyForecast) {
        List<DayDto> results = new ArrayList();
        for (DailyForecast listOfDay : dailyForecast) {
            DayDto dayDto = new DayDto();
            dayDto.setDate(listOfDay.getDate());
            dayDto.setMinTemp(listOfDay.getTemperature().getMinimum().getValue());
            dayDto.setMaxTemp(listOfDay.getTemperature().getMaximum().getValue());
            results.add(dayDto);
        }
        return results;
    }

    private String getStringResponseFromLocation(String postcode) throws IOException, ServiceUnavailableException {
        OkHttpClient client = getOkHttpClient();
        Request request = getRequestUsingPostcode(postcode);
        Response response = client.newCall(request).execute();
        if (response.code() == 503){
            throw new ServiceUnavailableException() ;
        }
        return response.body().string();
    }

    private Request getRequestUsingLocationKey(String locationKey) {
        count++;
        return new Request.Builder()
                .url(urlLocationKey + locationKey + "?apikey=" + api_key)
                .method("GET", null)
                .build();
    }

    private Request getRequestUsingPostcode(String postcode) {
        count++;
        return new Request.Builder()
                    .url(urlPostcode + "?q=" + postcode + "&apikey=" + api_key)
                    .method("GET", null)
                    .build();
    }

    private OkHttpClient getOkHttpClient() {
        return new OkHttpClient().newBuilder()
                .build();
    }
}