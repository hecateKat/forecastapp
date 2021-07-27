package com.kat.forecastapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DayDto {

    private String date;

    private double minTemp;

    private double maxTemp;
}