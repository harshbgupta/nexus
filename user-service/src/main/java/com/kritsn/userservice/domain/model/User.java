package com.kritsn.userservice.domain.model;

import java.time.Instant;
import java.util.UUID;

public class User {

    private final UUID id;
    private final String email;
    private final String passwordHash;
    private final String fullName;
    private final Instant createdAt;

    public User(UUID id, String email, String passwordHash, String fullName, Instant createdAt) {
        this.id = id;
        this.email = email;
        this.passwordHash = passwordHash;
        this.fullName = fullName;
        this.createdAt = createdAt;
    }

    public UUID getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public String getFullName() {
        return fullName;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
