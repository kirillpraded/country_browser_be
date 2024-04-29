package com.andersen.country_browser.controller;

import com.andersen.country_browser.dto.request.UpdateCityRequest;
import com.andersen.country_browser.dto.response.CityResponse;
import com.andersen.country_browser.dto.response.PagedCityResponse;
import com.andersen.country_browser.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/cities")
@CrossOrigin("http://localhost:4200")
public class CityController {

    @Autowired
    private CityService cityService;

    @GetMapping
    public ResponseEntity<PagedCityResponse> getCitiesWithPagination(@RequestParam(defaultValue = "0") final int page,
                                                                     @RequestParam(defaultValue = "10") final int size,
                                                                     @RequestParam(required = false) final String city,
                                                                     @RequestParam(required = false) final String country) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(cityService.performSearch(pageable, city, country));
    }
    @GetMapping("/country/{countryName}")
    public ResponseEntity<List<CityResponse>> getCitiesByCountryName(@PathVariable(name = "countryName") final String countryName) {
        return ResponseEntity.ok(cityService.getCitiesByCountryName(countryName));
    }

    @PutMapping("/update")
    public ResponseEntity<Void> updateCity(@RequestBody @Valid final UpdateCityRequest request, BindingResult result) {
        if (!result.hasErrors()) {
            cityService.updateCity(request);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
