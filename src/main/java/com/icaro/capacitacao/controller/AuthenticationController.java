package com.icaro.capacitacao.controller;

import com.icaro.capacitacao.dto.login.TokenResponse;
import com.icaro.capacitacao.dto.login.UserLogin;
import com.icaro.capacitacao.model.UserEntity;
import com.icaro.capacitacao.security.TokenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.antlr.v4.runtime.Token;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid UserLogin userLogin){

        var authenticationToken = new UsernamePasswordAuthenticationToken(userLogin.username(), userLogin.password());
        var authentication = authenticationManager.authenticate(authenticationToken);

        var accessToken = tokenService.generateToken((UserEntity) authentication.getPrincipal());
        return ResponseEntity.ok(new TokenResponse(accessToken));
    }
}
