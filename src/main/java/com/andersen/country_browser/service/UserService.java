package com.andersen.country_browser.service;

import com.andersen.country_browser.dto.request.SignUpRequest;

public interface UserService {

    void signup(SignUpRequest request);
}
