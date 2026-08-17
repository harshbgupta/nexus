package com.kritsn.userservice.domain.port.in;

import com.kritsn.userservice.domain.model.User;

import java.util.UUID;

public interface GetUserUseCase {
    User getById(UUID id);
}
