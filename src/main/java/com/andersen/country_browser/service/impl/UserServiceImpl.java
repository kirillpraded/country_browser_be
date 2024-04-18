package com.andersen.country_browser.service.impl;

import com.andersen.country_browser.dao.UserRepository;
import com.andersen.country_browser.dto.request.SignUpRequest;
import com.andersen.country_browser.exception.DuplicateException;
import com.andersen.country_browser.model.entity.UserEntity;
import com.andersen.country_browser.model.enums.Role;
import com.andersen.country_browser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void signup(final SignUpRequest request) {
        Optional<UserEntity> user = userRepository.findByEmail(request.email());
        if (user.isPresent()) {
            throw new DuplicateException(String.format("User with the email address '%s' already exists.", request.email()));
        }
        UserEntity userEntity = new UserEntity(request.firstName(), request.lastName(), request.email(),
                passwordEncoder.encode(request.password()), Role.DEFAULT);
        userRepository.save(userEntity);
    }
}
