package com.andersen.country_browser.service;

import com.andersen.country_browser.dto.request.UpdateCityRequest;
import com.andersen.country_browser.dto.response.CityResponse;
import com.andersen.country_browser.dto.response.PagedCityResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CityService {

    PagedCityResponse performSearch(Pageable request, String name, String country);
    List<CityResponse> getCitiesByCountryName(String countryName);

    void updateCity(UpdateCityRequest request);
}
