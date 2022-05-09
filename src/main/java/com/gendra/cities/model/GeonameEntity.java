package com.gendra.cities.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GeonameEntity {
    private Integer id;
    private String name;
    private String asciiname;
    private String altName;
    private String latitude;
    private String longitud;
    private String featClass;
    private String featCode;
    private String country;
    private String cc2;
    private String admin1;
    private String admin2;
    private String admin3;
    private String admin4;
    private String population;
    private String elevation;
    private String dem;
    private String tz;
    private String modifiedAt;
}
