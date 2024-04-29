package com.andersen.country_browser.dao;

import com.andersen.country_browser.model.entity.CountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CountryRepository extends JpaRepository<CountryEntity, UUID> {

    CountryEntity findDistinctByName(String name);

    List<CountryEntity> findAllByNameContains(String name);
}
