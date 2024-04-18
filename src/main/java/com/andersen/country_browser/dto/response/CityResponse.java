package com.andersen.country_browser.dto.response;

public record CityResponse(String name, String logoUrl, String id, CountryResponse country) {
}
