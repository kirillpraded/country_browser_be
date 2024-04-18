package com.andersen.country_browser.dao;

import com.andersen.country_browser.model.entity.CityEntity;
import com.andersen.country_browser.model.entity.CountryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CityRepository extends JpaRepository<CityEntity, UUID>, JpaSpecificationExecutor<CityEntity> {

    List<CityEntity> findCityEntitiesByCountry(CountryEntity country);
    Page<CityEntity> findCityEntitiesByName(String name, Pageable pageable);
}
