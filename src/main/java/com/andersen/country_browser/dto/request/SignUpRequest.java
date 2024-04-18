package com.andersen.country_browser.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public record SignUpRequest (

        @Email(message = "Invalid email format")
        @NotBlank(message = "Email cannot be blank")
        @Size(max = 50, message = "Email must be 50 characters max length")
        String email,
        @NotBlank(message = "First name cannot be blank")
        @Size(min = 6, max = 50, message = "First name must be between 6 and 50 characters")
        String firstName,
        @NotBlank(message = "Last name cannot be blank")
        @Size(min = 6, max = 50, message = "Last name must be between 6 and 50 characters")
        String lastName,
        @NotBlank(message = "Password cannot be blank")
        @Size(min = 6, max = 20, message = "Password must be between 6 and 20 characters")
        String password) {

}