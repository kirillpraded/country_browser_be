package com.andersen.country_browser.dao;

import com.andersen.country_browser.model.entity.CityEntity;
import com.andersen.country_browser.model.entity.CountryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CityRepository extends JpaRepository<CityEntity, UUID>, JpaSpecificationExecutor<CityEntity> {

    List<CityEntity> findCityEntitiesByCountryOrderById(CountryEntity country);
    Page<CityEntity> findCityEntitiesByNameOrderById(String name, Pageable pageable);

    Page<CityEntity> findAllByOrderById(Pageable pageable);

    Page<CityEntity> findAllByNameContainsOrderById(Pageable pageable, String name);
    Page<CityEntity> findAllByNameContainsAndCountryInOrderById(Pageable pageable, String name, List<CountryEntity> countries);
    Page<CityEntity> findAllByCountryInOrderById(Pageable pageable, List<CountryEntity> countries);


    Optional<CityEntity> findCityEntityById(UUID id);
}
