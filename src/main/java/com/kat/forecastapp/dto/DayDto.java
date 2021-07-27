package com.kat.forecastapp.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DayDto {

    private String date;

    private double minTemp;

    private double maxTemp;
}