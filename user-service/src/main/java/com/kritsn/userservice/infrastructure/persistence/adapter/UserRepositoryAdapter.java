package com.kritsn.userservice.infrastructure.persistence.adapter;

import com.kritsn.userservice.domain.model.User;
import com.kritsn.userservice.domain.port.out.UserRepositoryPort;
import com.kritsn.userservice.infrastructure.persistence.entity.UserJpaEntity;
import com.kritsn.userservice.infrastructure.persistence.repository.UserJpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class UserRepositoryAdapter implements UserRepositoryPort {

    private final UserJpaRepository jpaRepository;

    public UserRepositoryAdapter(UserJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public User save(User user) {
        return toDomain(jpaRepository.save(toEntity(user)));
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return jpaRepository.findByEmail(email).map(this::toDomain);
    }

    @Override
    public Optional<User> findById(UUID id) {
        return jpaRepository.findById(id).map(this::toDomain);
    }

    @Override
    public boolean existsByEmail(String email) {
        return jpaRepository.existsByEmail(email);
    }

    private UserJpaEntity toEntity(User user) {
        return new UserJpaEntity(user.getId(), user.getEmail(), user.getPasswordHash(), user.getFullName(), user.getCreatedAt());
    }

    private User toDomain(UserJpaEntity entity) {
        return new User(entity.getId(), entity.getEmail(), entity.getPasswordHash(), entity.getFullName(), entity.getCreatedAt());
    }
}
