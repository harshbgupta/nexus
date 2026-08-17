package com.kritsn.userservice.domain.port.in;

public interface LoginUseCase {
    String login(String email, String rawPassword);
}
