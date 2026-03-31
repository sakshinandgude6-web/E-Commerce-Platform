package com.sakshi.ecommerce.service.impl;

import com.sakshi.ecommerce.dto.RegisterRequest;
import com.sakshi.ecommerce.entity.Role;
import com.sakshi.ecommerce.entity.User;
import com.sakshi.ecommerce.repository.RoleRepository;
import com.sakshi.ecommerce.repository.UserRepository;
import com.sakshi.ecommerce.security.JwtService;
import com.sakshi.ecommerce.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.sakshi.ecommerce.dto.AuthResponse;
import com.sakshi.ecommerce.dto.LoginRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    public void register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already in use");
        }

        Role userRole = roleRepository.findByNameIgnoreCase("USER")
                .orElseThrow(() -> new RuntimeException("USER role not found"));

        User user = User.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(userRole)
                .enabled(true)
                .build();

        System.out.println(roleRepository.findAll());

        userRepository.save(user);
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        String token = jwtService.generateToken(request.getEmail());

        return AuthResponse.builder().token(token).build();
    }
}