package com.andersen.country_browser.controller;

import com.andersen.country_browser.config.auth.JwtProvider;
import com.andersen.country_browser.dto.request.SignInRequest;
import com.andersen.country_browser.dto.request.SignUpRequest;
import com.andersen.country_browser.dto.response.SignInResponse;
import com.andersen.country_browser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin("http://localhost:4200")

public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/signup")
    public ResponseEntity<Void> signup(@Valid @RequestBody SignUpRequest requestDto) {
        userService.signup(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping(value = "/login")
    public ResponseEntity<SignInResponse> login(@Valid @RequestBody SignInRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.email(), request.password()));
        String token = JwtProvider.generateToken(request.email());
        return ResponseEntity.ok(new SignInResponse(request.email(), token));
    }
}
