package com.gendra.cities.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GeonameDto {
    private String name;
    private String latitude;
    private String longitud;
    private String score;
}
