package com.icaro.capacitacao.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO {

    @NotBlank(message = "The username field is required.")
    private String username;

    @NotBlank(message = "The email field is required.")
    @Email(message = "Invalid format email.")
    private String email;

    @NotBlank(message = "The password field is required.")
    private String password;
}
