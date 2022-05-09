package com.gendra.cities.controller;

import com.gendra.cities.model.GeonameDto;
import com.gendra.cities.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

@Controller
@Validated
public class CitiesController {
    @Autowired
    CityService cityService;

    @GetMapping(path = "/suggestions")
    @ResponseBody
    public ResponseEntity<List<GeonameDto>> search(@RequestParam(name = "q") Optional<String> name,
                                                   @RequestParam(name = "latitude", required = false)
                                                         Optional<Double> latitude,
                                                   @RequestParam(name = "longitude") Optional<Double> longitud) throws Exception {
        return ResponseEntity.ok(cityService.search(name, latitude, longitud));
    }

    @RequestMapping(value = {"/{[path:[^\\.]*}", "/"})
    public String redirect() {
        return "redirect:/suggestions";
    }
}
