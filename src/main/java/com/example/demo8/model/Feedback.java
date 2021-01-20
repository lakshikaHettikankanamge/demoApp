package com.example.demo8.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Feedback {
    @NotNull
    private String name;

    @NotNull
    @Email
    private String mail;

    @NotNull
    private String feedback;
}
