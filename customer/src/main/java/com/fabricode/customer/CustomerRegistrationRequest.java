package com.fabricode.customer;

public record CustomerRegistrationRequest(
        String firstName,
        String lastName,
        String email
) {
}
