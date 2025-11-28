package com.icaro.capacitacao.dto.login;

import jakarta.validation.constraints.NotBlank;

public record UserLogin (
        @NotBlank String username,
        @NotBlank String password
){
}
