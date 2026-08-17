package com.kritsn.userservice.application.usecase;

import com.kritsn.userservice.domain.model.User;
import com.kritsn.userservice.domain.port.in.RegisterUserUseCase;
import com.kritsn.userservice.domain.port.out.PasswordHasherPort;
import com.kritsn.userservice.domain.port.out.UserRepositoryPort;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class RegisterUserService implements RegisterUserUseCase {

    private final UserRepositoryPort userRepository;
    private final PasswordHasherPort passwordHasher;

    public RegisterUserService(UserRepositoryPort userRepository, PasswordHasherPort passwordHasher) {
        this.userRepository = userRepository;
        this.passwordHasher = passwordHasher;
    }

    @Override
    public User register(String email, String rawPassword, String fullName) {
        if (userRepository.existsByEmail(email)) {
            throw new IllegalStateException("Email already registered: " + email);
        }
        User user = new User(UUID.randomUUID(), email, passwordHasher.hash(rawPassword), fullName, Instant.now());
        return userRepository.save(user);
    }
}
