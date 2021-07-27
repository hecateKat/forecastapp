package com.kat.forecastapp.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ForecastDto {

    private String voivodeship;

    private List<DayDto> days;
}
