package com.example.backend.security.service;

import com.example.backend.security.service.details.MyUserDetails;
import io.jsonwebtoken.Claims;
import org.springframework.security.core.Authentication;

import java.util.function.Function;

public interface JwtService {
    String extractUserEmail(String token);
    <T> T extractClaim(String token, Function<Claims, T> claimsResolver);
    String generateAccessToken(Authentication authentication);
    String generateRefreshToken(Authentication authentication);
    String generateNewPasswordToken(String email);
    boolean isTokenValid(String token,  MyUserDetails userDetails);
}