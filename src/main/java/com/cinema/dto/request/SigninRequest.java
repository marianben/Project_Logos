package com.cinema.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class SigninRequest {

    @NotNull
    @Size(min = 5, max = 120)
    private String email;

    @NotNull
    @Size(min = 3, max = 30)
    private String password;
}