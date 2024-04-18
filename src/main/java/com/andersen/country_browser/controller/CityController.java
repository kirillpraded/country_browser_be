package com.andersen.country_browser.controller;

import com.andersen.country_browser.dto.response.CityResponse;
import com.andersen.country_browser.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cities")
public class CityController {

    @Autowired
    private CityService cityService;

    @GetMapping
    public ResponseEntity<List<CityResponse>> getCitiesWithPagination(@RequestParam(defaultValue = "0") final int page,
                                                                      @RequestParam(defaultValue = "10") final int size,
                                                                      @RequestParam(required = false) final String name) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(cityService.getAllCities(pageable, name));
    }
    @GetMapping("/country/{countryName}")
    public ResponseEntity<List<CityResponse>> getCitiesByCountryName(@PathVariable(name = "countryName") final String countryName) {
        return ResponseEntity.ok(cityService.getCitiesByCountryName(countryName));
    }
}
