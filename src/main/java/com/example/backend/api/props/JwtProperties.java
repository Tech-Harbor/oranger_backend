package com.example.backend.api.props;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {
    private String key;
    private long jwtAccessExpiration;
    private long jwtRefreshExpiration;
}