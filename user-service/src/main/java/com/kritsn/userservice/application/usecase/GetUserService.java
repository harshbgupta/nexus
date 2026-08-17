package com.kritsn.userservice.application.usecase;

import com.kritsn.userservice.domain.model.User;
import com.kritsn.userservice.domain.port.in.GetUserUseCase;
import com.kritsn.userservice.domain.port.out.UserRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class GetUserService implements GetUserUseCase {

    private final UserRepositoryPort userRepository;

    public GetUserService(UserRepositoryPort userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User not found: " + id));
    }
}
