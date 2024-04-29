package com.andersen.country_browser.service.impl;

import com.andersen.country_browser.dao.CityRepository;
import com.andersen.country_browser.dao.CountryRepository;
import com.andersen.country_browser.dto.request.UpdateCityRequest;
import com.andersen.country_browser.dto.response.CityResponse;
import com.andersen.country_browser.dto.response.CountryResponse;
import com.andersen.country_browser.dto.response.PagedCityResponse;
import com.andersen.country_browser.model.entity.CityEntity;
import com.andersen.country_browser.model.entity.CountryEntity;
import com.andersen.country_browser.service.CityService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private CountryRepository countryRepository;

    @Override
    public PagedCityResponse performSearch(final Pageable request, final String city, final String country) {
        List<CountryEntity> countryEntities = Strings.isBlank(country) ? Collections.emptyList() : countryRepository.findAllByNameContains(country);
        Page<CityEntity> citiesPage = Strings.isBlank(city) ?
                (countryEntities.isEmpty() ? cityRepository.findAllByOrderById(request) : cityRepository.findAllByCountryInOrderById(request, countryEntities)) :
                (countryEntities.isEmpty() ? cityRepository.findAllByNameContainsOrderById(request, city) : cityRepository.findAllByNameContainsAndCountryInOrderById(request, city, countryEntities));

        List<CityResponse> cities = citiesPage.get()
                .map(cityEntity -> new CityResponse(cityEntity.getName(), cityEntity.getImageUrl(), cityEntity.getId().toString(),
                        new CountryResponse(cityEntity.getCountry().getName(), cityEntity.getCountry().getId().toString())))
                .collect(Collectors.toList());

        return new PagedCityResponse(cities, citiesPage.getTotalPages());
    }

    @Override
    public List<CityResponse> getCitiesByCountryName(final String countryName) {
        return mapToResponse(cityRepository.findCityEntitiesByCountryOrderById(countryRepository.findDistinctByName(countryName)).stream());
    }

    @Override
    public void updateCity(final UpdateCityRequest request) {
        Optional<CityEntity> cityEntityById = cityRepository.findCityEntityById(UUID.fromString(request.id()));
        cityEntityById.ifPresent(cityEntity -> {
            cityEntity.setName(request.name());
            cityEntity.setImageUrl(request.imageUrl());
            cityRepository.save(cityEntity);
        });
    }

    private List<CityResponse> mapToResponse(final Stream<CityEntity> stream) {
        return stream.map(cityEntity -> new CityResponse(cityEntity.getName(), "", cityEntity.getId().toString(),
                new CountryResponse(cityEntity.getCountry().getName(), cityEntity.getCountry().getId().toString())))
                .collect(Collectors.toList());
    }
}
