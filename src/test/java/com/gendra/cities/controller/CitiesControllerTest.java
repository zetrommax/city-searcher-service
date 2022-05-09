package com.gendra.cities.controller;

import com.gendra.cities.service.CityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import com.gendra.cities.data.Data;


import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class CitiesControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;
    @MockBean
    CityService cityService;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).alwaysDo(print()).build();
    }

    @Test
    @DisplayName("Testing cotroller with empty response")
    void searchCities_empty() throws Exception {
        //GIVEN
        Mockito.when(cityService.search(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(Collections.emptyList());

        //WHEN
        mockMvc.perform(get("/suggestions")
                        .contentType(MediaType.APPLICATION_JSON))

        //THEN
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", is(Collections.emptyList())));
    }

    @Test
    void searchCities_with_results() throws Exception {
        //GIVEN
        Mockito.when(cityService.search(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(Data.resultsGeoname());

        //WHEN
        mockMvc.perform(get("/suggestions")
                        .param("q", "Amer")
                        .contentType(MediaType.APPLICATION_JSON))

                //THEN
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(8)));
    }
}