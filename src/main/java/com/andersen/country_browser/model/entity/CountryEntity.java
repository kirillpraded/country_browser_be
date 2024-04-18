package com.andersen.country_browser.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "countries", schema = "countries_management")
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class CountryEntity extends AbstractEntity {

    @Column(name = "name", nullable = false)
    @Size(max = 100)
    private String name;
}
