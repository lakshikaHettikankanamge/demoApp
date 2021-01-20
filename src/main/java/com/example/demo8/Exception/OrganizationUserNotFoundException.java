package com.example.demo8.Exception;

public class OrganizationUserNotFoundException extends RuntimeException {
    public OrganizationUserNotFoundException(String message) {
        super(message);
    }
}
