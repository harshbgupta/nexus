package com.kritsn.userservice.application.usecase;

import com.kritsn.userservice.domain.model.User;
import com.kritsn.userservice.domain.port.in.LoginUseCase;
import com.kritsn.userservice.domain.port.out.PasswordHasherPort;
import com.kritsn.userservice.domain.port.out.TokenIssuerPort;
import com.kritsn.userservice.domain.port.out.UserRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements LoginUseCase {

    private final UserRepositoryPort userRepository;
    private final PasswordHasherPort passwordHasher;
    private final TokenIssuerPort tokenIssuer;

    public LoginService(UserRepositoryPort userRepository, PasswordHasherPort passwordHasher, TokenIssuerPort tokenIssuer) {
        this.userRepository = userRepository;
        this.passwordHasher = passwordHasher;
        this.tokenIssuer = tokenIssuer;
    }

    @Override
    public String login(String email, String rawPassword) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
        if (!passwordHasher.matches(rawPassword, user.getPasswordHash())) {
            throw new IllegalArgumentException("Invalid email or password");
        }
        return tokenIssuer.issue(user);
    }
}
