package com.kritsn.userservice.presentation.rest.dto;

public record RegisterRequest(String email, String password, String fullName) {
}
