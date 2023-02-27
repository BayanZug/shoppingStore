package com.retailer.shop.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.retailer.shop.entity.User;
import com.retailer.shop.repo.RoleRepository;
import com.retailer.shop.repo.UserRepo;
import com.retailer.shop.service.UserService;

import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImp implements UserService {

    private final UserRepo userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    private static final String USER_ROLE = "ROLE_USER";

    @Autowired
    public UserServiceImp(UserRepo userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User saveUser(User user) {
        // Encode plaintext password
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        // Set Role to ROLE_USER
        user.setRoles(Set.of(roleRepository.findByName(USER_ROLE)));
        return userRepository.saveAndFlush(user);
    }
}
