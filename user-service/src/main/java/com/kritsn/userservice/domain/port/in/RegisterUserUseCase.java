package com.kritsn.userservice.domain.port.in;

import com.kritsn.userservice.domain.model.User;

public interface RegisterUserUseCase {
    User register(String email, String rawPassword, String fullName);
}
