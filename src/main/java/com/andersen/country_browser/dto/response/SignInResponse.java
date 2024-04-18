package com.andersen.country_browser.dto.response;

public record SignInResponse(
        String email,
        String token
) {
}
