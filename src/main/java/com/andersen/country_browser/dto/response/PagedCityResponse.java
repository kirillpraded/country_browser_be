package com.andersen.country_browser.dto.response;

import java.util.List;

public record PagedCityResponse(
        List<CityResponse> cities,
        int totalPages
) {
}
