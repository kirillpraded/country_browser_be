package com.andersen.country_browser.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public record UpdateCityRequest(
        @NotBlank(message = "ID cannot be blank")
        String id,
        @Size(min = 6, max = 256, message = "imageUrl must be between 6 and 256 characters")
        String imageUrl,
        @Size(min = 6, max = 50, message = "name must be between 6 and 50 characters")
        @NotBlank(message = "name cannot be blank")
        String name
) {
}
