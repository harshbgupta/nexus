package com.kritsn.userservice.presentation.rest.controller;

import com.kritsn.userservice.domain.model.User;
import com.kritsn.userservice.domain.port.in.GetUserUseCase;
import com.kritsn.userservice.domain.port.in.LoginUseCase;
import com.kritsn.userservice.domain.port.in.RegisterUserUseCase;
import com.kritsn.userservice.presentation.rest.dto.LoginRequest;
import com.kritsn.userservice.presentation.rest.dto.LoginResponse;
import com.kritsn.userservice.presentation.rest.dto.RegisterRequest;
import com.kritsn.userservice.presentation.rest.dto.UserResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final RegisterUserUseCase registerUserUseCase;
    private final LoginUseCase loginUseCase;
    private final GetUserUseCase getUserUseCase;

    public UserController(RegisterUserUseCase registerUserUseCase, LoginUseCase loginUseCase, GetUserUseCase getUserUseCase) {
        this.registerUserUseCase = registerUserUseCase;
        this.loginUseCase = loginUseCase;
        this.getUserUseCase = getUserUseCase;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse register(@RequestBody RegisterRequest request) {
        User user = registerUserUseCase.register(request.email(), request.password(), request.fullName());
        return toResponse(user);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return new LoginResponse(loginUseCase.login(request.email(), request.password()));
    }

    @GetMapping("/{id}")
    public UserResponse getById(@PathVariable UUID id) {
        return toResponse(getUserUseCase.getById(id));
    }

    private UserResponse toResponse(User user) {
        return new UserResponse(user.getId(), user.getEmail(), user.getFullName(), user.getCreatedAt());
    }
}
