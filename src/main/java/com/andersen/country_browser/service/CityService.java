package com.andersen.country_browser.service;

import com.andersen.country_browser.dto.response.CityResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CityService {

    List<CityResponse> getAllCities(Pageable request, String name);
    List<CityResponse> getCitiesByCountryName(String countryName);
}
