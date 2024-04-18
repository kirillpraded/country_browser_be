package com.andersen.country_browser.service.impl;

import com.andersen.country_browser.dao.CityRepository;
import com.andersen.country_browser.dao.CountryRepository;
import com.andersen.country_browser.dto.response.CityResponse;
import com.andersen.country_browser.dto.response.CountryResponse;
import com.andersen.country_browser.model.entity.CityEntity;
import com.andersen.country_browser.service.CityService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private CountryRepository countryRepository;

    @Override
    public List<CityResponse> getAllCities(final Pageable request, final String name) {
        Page<CityEntity> cities = Strings.isBlank(name) ? cityRepository.findAll(request) : cityRepository.findCityEntitiesByName(name, request);

        return cities.get()
                .map(cityEntity -> new CityResponse(cityEntity.getName(), "", cityEntity.getId().toString(),
                        new CountryResponse(cityEntity.getCountry().getName(), cityEntity.getCountry().getId().toString())))
                .collect(Collectors.toList());
    }

    @Override
    public List<CityResponse> getCitiesByCountryName(final String countryName) {
        return mapToResponse(cityRepository.findCityEntitiesByCountry(countryRepository.findDistinctByName(countryName)).stream());
    }


    private List<CityResponse> mapToResponse(final Stream<CityEntity> stream) {
        return stream.map(cityEntity -> new CityResponse(cityEntity.getName(), "", cityEntity.getId().toString(),
                new CountryResponse(cityEntity.getCountry().getName(), cityEntity.getCountry().getId().toString())))
                .collect(Collectors.toList());
    }
}
