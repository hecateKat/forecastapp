package com.kat.forecastapp.controller;

import com.kat.forecastapp.dto.DayDto;
import com.kat.forecastapp.dto.ForecastDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.naming.ServiceUnavailableException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ForecastControllerTest {

    @Autowired
    ForecastController forecastController;

    @MockBean
    DayDto dayDto;

    @Test
    void shouldGetDtoFromLublin() throws IOException, ServiceUnavailableException {
        List<DayDto> days = new ArrayList<>();
        days.add(dayDto);
        ForecastDto forecastDto1 = ForecastDto.builder()
                .voivodeship("Lublin")
                .days(days)
                .build();

        ForecastDto forecastDto2 = forecastController.showForecast("20-570");

        assertThat(forecastDto1.getVoivodeship()).isEqualTo(forecastDto2.getVoivodeship());
    }

    @Test
    void shouldGetDtoFromOpole() throws IOException, ServiceUnavailableException {
        List<DayDto> days = new ArrayList<>();
        days.add(dayDto);
        ForecastDto forecastDto1 = ForecastDto.builder()
                .voivodeship("Opole")
                .days(days)
                .build();

        ForecastDto forecastDto2 = forecastController.showForecast("45-020");

        assertThat(forecastDto1.getVoivodeship()).isEqualTo(forecastDto2.getVoivodeship());
    }
}